package vn.care4u.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.care4u.entity.Appointment;
import vn.care4u.entity.Measurement;
import vn.care4u.entity.MedicalRecord;
import vn.care4u.entity.Prescription;
import vn.care4u.enumeration.EStatus;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.dto.AppointmentDTO;
import vn.care4u.model.dto.DoctorProfileDTO;
import vn.care4u.model.dto.MedicalRecordDTO;
import vn.care4u.model.request.CreateAppointmentRequest;
import vn.care4u.model.request.CreateMedicalRecordRequest;
import vn.care4u.model.request.CreatePrescriptionRequest;
import vn.care4u.model.request.UpdateAppointmentRequest;
import vn.care4u.repository.*;
import vn.care4u.service.CurrentUserService;
import vn.care4u.service.DoctorUsecaseService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorUsecaseServiceImpl implements DoctorUsecaseService {

    private final CurrentUserService currentUser;
    private final AppointmentRepository appointmentRepo;
    private final MedicalRecordRepository recordRepo;
    private final PrescriptionRepository prescriptionRepo;
    private final PrescriptionItemRepository prescriptionItemRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;
//    private final DrugRepository drugRepo;

    private final MeasurementRepository measurementRepo;

    @Override
    public MedicalRecordDTO toDTO(MedicalRecord r) {
        var dto = new MedicalRecordDTO();

        dto.setId(r.getId());
        dto.setCreatedAt(r.getCreatedAt());

        dto.setSymptoms(r.getSymptoms());
        dto.setPhysicalExam(r.getPhysicalExam());
        dto.setDiagnosis(r.getDiagnosis());
        dto.setConclusion(r.getConclusion());
        dto.setTreatment(r.getTreatment());
        dto.setAdvice(r.getAdvice());
        dto.setNotes(r.getNotes());

        // Doctor
        if (r.getDoctor() != null) {
            dto.setDoctorId(r.getDoctor().getId());
            dto.setDoctorName(r.getDoctor().getFirstname() + " " + r.getDoctor().getLastname());
        }

        // Patient
        if (r.getPatient() != null) {
            dto.setPatientId(r.getPatient().getId());
            dto.setPatientName(r.getPatient().getFirstname() + " " + r.getPatient().getLastname());
        }

        return dto;
    }


    @Override
    public List<AppointmentDTO> listAppointments(String q) {
        Long doctorId = currentUser.currentDoctorId();
        var list = (q == null || q.isBlank())
                ? appointmentRepo.findByDoctorIdOrderByTimeAsc(doctorId)
                : appointmentRepo.search(doctorId, q.trim());

        return list.stream().map(a -> AppointmentDTO.builder()
                .id(a.getId())
                .patientId(a.getPatient().getId())
                .patientName(a.getPatient().getFirstname() + " " + a.getPatient().getLastname())
                .time(a.getDateTime())
                .status(a.getStatus().name())
                .reason(a.getNotes())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void manageAppointment(Long apptId, String action) {

        Long doctorId = currentUser.currentDoctorId();
        var appt = appointmentRepo.findByIdAndDoctorId(apptId, doctorId)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND));

        EStatus newStatus = switch (action.toUpperCase()) {
            case "APPROVE" -> EStatus.APPROVED;
            case "REJECT" -> EStatus.CANCELED;
            case "DONE" -> EStatus.COMPLETED;
            default -> throw new GeneralException(ErrorCode.INVALID_INFORMATION);
        };
        appt.setStatus(newStatus);
        appointmentRepo.save(appt);

    }

    @Override
    public AppointmentDTO createAppointment(CreateAppointmentRequest req) {
        Long doctorId = currentUser.currentDoctorId();
        var doctor = doctorRepo.findById(doctorId).orElseThrow();
        var patient = patientRepo.findById(req.getPatientId())
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND));

        var a = new Appointment();
        a.setDoctor(doctor);
        a.setPatient(patient);
        a.setDate(req.getDate());
        a.setTime(req.getTime());
        a.setPlace(req.getPlace());
        a.setNotes(req.getNotes());
        a.setStatus(EStatus.PENDING);
        a = appointmentRepo.save(a);

        return AppointmentDTO.builder()
                .id(a.getId())
                .patientId(patient.getId())
                .patientName(patient.getFirstname()+" "+patient.getLastname())
                .time(a.getDateTime())
                .place(a.getPlace())
                .status(a.getStatus().name())
                .reason(a.getNotes())
                .build();
    }

    @Override
    public void updateAppointmentStatus(Long appointmentId, String status) {

        Long doctorId = currentUser.currentDoctorId();

        var appt = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (appt.getDoctor() == null || !appt.getDoctor().getId().equals(doctorId)) {
            throw new RuntimeException("Forbidden");
        }

        appt.setStatus(vn.care4u.enumeration.EStatus.valueOf(status));
        appointmentRepo.save(appt);

    }

    @Override
    public void deleteAppointment(Long id) {
        var appt = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Long doctorId = currentUser.currentDoctorId();

        if (!appt.getDoctor().getId().equals(doctorId)) {
            throw new RuntimeException("Bạn không có quyền xoá lịch này");
        }

        appointmentRepo.delete(appt);
    }

    @Override
    public void updateAppointment(Long id, UpdateAppointmentRequest req) {
        var appt = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Long doctorId = currentUser.currentDoctorId();

        if (!appt.getDoctor().getId().equals(doctorId)) {
            throw new RuntimeException("Bạn không có quyền cập nhật lịch này");
        }

        if (req.getDate() != null)
            appt.setDate(LocalDate.parse(req.getDate())); // nếu date là dạng "2025-12-06"

        if (req.getTime() != null)
            appt.setTime(LocalTime.parse(req.getTime())); // dạng "15:05"

        if (req.getPlace() != null)
            appt.setPlace(req.getPlace());

        if (req.getNotes() != null)
            appt.setNotes(req.getNotes());

        appointmentRepo.save(appt);
    }



    @Override
    public MedicalRecord createMedicalRecord(CreateMedicalRecordRequest req) {
        Long doctorId = currentUser.currentDoctorId();

        var doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        var patient = patientRepo.findById(req.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // 1. TẠO & LƯU PHIẾU KHÁM (MEDICAL RECORD)
        var r = new MedicalRecord();
        r.setDoctor(doctor);
        r.setPatient(patient);

        // Map các trường Text
        r.setSymptoms(req.getSymptoms());
        r.setPhysicalExam(req.getPhysicalExam()); // Mới
        r.setDiagnosis(req.getDiagnosis());
        r.setConclusion(req.getConclusion());     // Mới
        r.setTreatment(req.getTreatment());
        r.setAdvice(req.getAdvice());             // Mới
        r.setNotes(req.getNotes());

        // Lưu xuống DB trước để có ID
        r = recordRepo.save(r);

        // 2. TẠO & LƯU SINH HIỆU (MEASUREMENT) - NẾU CÓ DỮ LIỆU
        // Kiểm tra sơ bộ xem có nhập sinh hiệu không để tránh lưu rác
        if (hasMeasurementData(req)) {
            var m = new Measurement();
            m.setPatient(patient);
            m.setMedicalRecord(r); // Link vào phiếu khám vừa tạo

            m.setSystolicBloodPressure(req.getSystolicBP());
            m.setDiastolicBloodPressure(req.getDiastolicBP());
            m.setTemperature(req.getTemperature());
            m.setHeartRate(req.getHeartRate());
            m.setRespiratoryRate(req.getRespiratoryRate());
            m.setSpo2(req.getSpo2());
            m.setHeight(req.getHeight());
            m.setWeight(req.getWeight());
            m.setBmi(req.getBmi());

            measurementRepo.save(m);
        }

        return r;
    }

    private boolean hasMeasurementData(CreateMedicalRecordRequest req) {
        return req.getHeight() != null || req.getWeight() != null
                || req.getTemperature() != null || req.getSystolicBP() != null;
    }

    @Override
    public Prescription createPrescription(CreatePrescriptionRequest req) {
        Long doctorId = currentUser.currentDoctorId();

        var doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        var patient = patientRepo.findById(req.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        var p = new Prescription();
        p.setDoctor(doctor);
        p.setPatient(patient);
        // nếu entity có createdAt:
        try { p.getClass().getMethod("setCreatedAt", LocalDateTime.class); p.setCreatedAt(LocalDateTime.now()); } catch (Exception ignore) {}
        p = prescriptionRepo.save(p);

        if (req.getItems() != null) {
            for (var it : req.getItems()) {
                var item = new vn.care4u.entity.PrescriptionItem();
                item.setPrescription(p);
                item.setName(it.getName());
                item.setDose(it.getDose());
                item.setQuantity(it.getQuantity());
                item.setNote(it.getNote());


                // if (it.getDrugId() != null) {
                //     drugRepo.findById(it.getDrugId()).ifPresent(item::setDrug);
                // }

                prescriptionItemRepo.save(item);
            }
        }
        return p;
    }

    @Override
    public List<MedicalRecord> patientRecords(Long patientId) {
        return recordRepo.findByPatientIdOrderByCreatedAtDesc(patientId);
    }

    @Override
    public List<Prescription> patientPrescriptions(Long patientId) {
        return List.of();
    }

    @Override
    public DoctorProfileDTO getMyProfile() {
        return null;
    }

    @Override
    public DoctorProfileDTO updateMyProfile(DoctorProfileDTO dto) {
        return null;
    }
}
