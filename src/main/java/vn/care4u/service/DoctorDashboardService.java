package vn.care4u.service;

import vn.care4u.model.dto.DoctorDashboardStatsDTO;
import vn.care4u.model.dto.PendingMedicalRecordDTO;
import vn.care4u.model.dto.TodayAppointmentDTO;

import java.util.List;

public interface DoctorDashboardService {
    DoctorDashboardStatsDTO getStats();
    List<TodayAppointmentDTO> getTodayAppointments();
    List<PendingMedicalRecordDTO> getPendingMedicalRecords();
}
