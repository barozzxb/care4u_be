package vn.care4u.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.care4u.entity.MedicalRecord;
import vn.care4u.model.dto.MedicalHistoryDTO;
import vn.care4u.repository.MedicalRecordRepository;
import vn.care4u.repository.AppointmentRepository;
import vn.care4u.service.MedicalRecordService;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public List<MedicalHistoryDTO> getAllMedicalRecords() {
        return medicalRecordRepository.findAll().stream()
            .map(r -> {
                var appt = appointmentRepository
                        .findFirstByPatientAndDoctorOrderByDateDesc(r.getPatient(), r.getDoctor())
                        .orElse(null);

                return new MedicalHistoryDTO(
                    r.getId(),
                    r.getCreatedAt(),
                    r.getDiagnosis(),
                    r.getTreatment(),
                    r.getNotes(),
                    r.getDoctor() != null
                        ? r.getDoctor().getLastname() + " " + r.getDoctor().getFirstname()
                        : "Không xác định",
                    appt != null ? appt.getDate() : null,
                    appt != null ? appt.getTime() : null
                );
            }).toList();
    }

    @Override
    public MedicalHistoryDTO getMedicalRecordById(Long id) {
        MedicalRecord r = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ bệnh án"));

        var appt = appointmentRepository
                .findFirstByPatientAndDoctorOrderByDateDesc(r.getPatient(), r.getDoctor())
                .orElse(null);

        return new MedicalHistoryDTO(
            r.getId(),
            r.getCreatedAt(),
            r.getDiagnosis(),
            r.getTreatment(),
            r.getNotes(),
            r.getDoctor() != null
                ? r.getDoctor().getLastname() + " " + r.getDoctor().getFirstname()
                : "Không xác định",
            appt != null ? appt.getDate() : null,
            appt != null ? appt.getTime() : null
        );
    }

    @Override
    public MedicalHistoryDTO createMedicalRecord(MedicalHistoryDTO dto) {
        MedicalRecord record = new MedicalRecord();
        record.setDiagnosis(dto.getDiagnosis());
        record.setTreatment(dto.getTreatment());
        record.setNotes(dto.getNotes());
        // Nếu FE gửi doctorId, map doctor trước khi lưu
        // record.setDoctor(doctorRepository.findById(dto.getDoctorId()).orElse(null));

        MedicalRecord saved = medicalRecordRepository.save(record);

        var appt = appointmentRepository
                .findFirstByPatientAndDoctorOrderByDateDesc(saved.getPatient(), saved.getDoctor())
                .orElse(null);

        return new MedicalHistoryDTO(
            saved.getId(),
            saved.getCreatedAt(),
            saved.getDiagnosis(),
            saved.getTreatment(),
            saved.getNotes(),
            saved.getDoctor() != null
                ? saved.getDoctor().getLastname() + " " + saved.getDoctor().getFirstname()
                : "Không xác định",
            appt != null ? appt.getDate() : null,
            appt != null ? appt.getTime() : null
        );
    }
}
