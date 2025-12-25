package vn.care4u.service;

import vn.care4u.model.dto.AppointmentRequestDTO;
import vn.care4u.model.dto.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentResponseDTO createAppointment(AppointmentRequestDTO request);

    AppointmentResponseDTO createAppointmentByEmail(
            String patientEmail,
            AppointmentRequestDTO request
    );

    List<AppointmentResponseDTO> getAppointmentsByPatientId(Long patientId);

    void cancelAppointment(Long appointmentId, String patientEmail);
}
