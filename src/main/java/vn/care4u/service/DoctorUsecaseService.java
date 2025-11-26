package vn.care4u.service;

import vn.care4u.entity.MedicalRecord;
import vn.care4u.entity.Prescription;
import vn.care4u.model.dto.AppointmentDTO;
import vn.care4u.model.dto.DoctorProfileDTO;
import vn.care4u.model.request.CreateAppointmentRequest;
import vn.care4u.model.request.CreateMedicalRecordRequest;
import vn.care4u.model.request.CreatePrescriptionRequest;

import java.util.List;

public interface DoctorUsecaseService {

    //list cho bac si
    List<AppointmentDTO> listAppointments(String q);

    //action (approve/reject/done)
    void manageAppointment(Long apptId, String action);

    //Create (bat ki actor nao co quyen)
    AppointmentDTO createAppointment(CreateAppointmentRequest req);

    void updateAppointmentStatus(Long appointmentId, String status);

    MedicalRecord createMedicalRecord(CreateMedicalRecordRequest req);
    Prescription createPrescription(CreatePrescriptionRequest req);

    List<MedicalRecord> patientRecords(Long patientId);
    List<Prescription> patientPrescriptions(Long patientId);

    DoctorProfileDTO getMyProfile();
    DoctorProfileDTO updateMyProfile(DoctorProfileDTO dto);
}
