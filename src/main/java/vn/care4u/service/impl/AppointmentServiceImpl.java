package vn.care4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.care4u.entity.Appointment;
import vn.care4u.entity.Doctor;
import vn.care4u.entity.Patient;
import vn.care4u.enumeration.EStatus;
import vn.care4u.model.dto.AppointmentRequestDTO;
import vn.care4u.model.dto.AppointmentResponseDTO;
import vn.care4u.repository.AppointmentRepository;
import vn.care4u.repository.DoctorRepository;
import vn.care4u.service.AppointmentService;
import vn.care4u.service.PatientService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientService patientService;

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public AppointmentResponseDTO createAppointmentByEmail(
            String patientEmail,
            AppointmentRequestDTO req
    ) {

        Patient patient = patientService.getPatientById(patientEmail);

        Doctor doctor = doctorRepo.findById(req.getDoctorId())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy bác sĩ")
                );

        LocalDate date = LocalDate.parse(req.getDate(), DATE_FORMATTER);
        LocalTime time = LocalTime.parse(req.getTime(), TIME_FORMATTER);

        Appointment exist =
                repo.findByDoctorAndDateAndTime(doctor, date, time);

        if (exist != null) {
            throw new RuntimeException("Bác sĩ đã có lịch tại giờ này");
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setPlace(req.getPlace());
        appointment.setNotes(req.getNotes());
        appointment.setStatus(EStatus.PENDING);

        return mapToResponseDTO(repo.save(appointment));
    }

    @Override
    public AppointmentResponseDTO createAppointment(
            AppointmentRequestDTO request
    ) {
        throw new UnsupportedOperationException(
                "Sử dụng createAppointmentByEmail"
        );
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByPatientId(
            Long patientId
    ) {
        return repo.findByPatientId(patientId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public void cancelAppointment(
            Long appointmentId,
            String patientEmail
    ) {

        Patient patient = patientService.getPatientById(patientEmail);

        Appointment appointment = repo
                .findByIdAndPatientId(appointmentId, patient.getId())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy lịch hẹn")
                );

        if (appointment.getStatus() != EStatus.PENDING) {
            throw new RuntimeException(
                    "Chỉ được hủy lịch khi đang chờ xác nhận"
            );
        }

        repo.delete(appointment);
    }

    private AppointmentResponseDTO mapToResponseDTO(Appointment a) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setId(a.getId());
        dto.setDoctorId(a.getDoctor().getId());
        dto.setDoctorName(
                a.getDoctor().getFirstname() + " " +
                a.getDoctor().getLastname()
        );
        dto.setPatientId(a.getPatient().getId());
        dto.setDate(a.getDate().format(DATE_FORMATTER));
        dto.setTime(a.getTime().format(TIME_FORMATTER));
        dto.setPlace(a.getPlace());
        dto.setNotes(a.getNotes());
        dto.setStatus(a.getStatus());
        return dto;
    }
}
