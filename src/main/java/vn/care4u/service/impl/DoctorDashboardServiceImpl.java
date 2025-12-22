package vn.care4u.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.care4u.enumeration.EStatus;
import vn.care4u.enumeration.MedicalRecordStatus;
import vn.care4u.model.dto.DoctorDashboardStatsDTO;
import vn.care4u.model.dto.PendingMedicalRecordDTO;
import vn.care4u.model.dto.TodayAppointmentDTO;
import vn.care4u.repository.AppointmentRepository;
import vn.care4u.repository.MedicalRecordRepository;
import vn.care4u.repository.NotificationRepository;
import vn.care4u.service.CurrentUserService;
import vn.care4u.service.DoctorDashboardService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorDashboardServiceImpl implements DoctorDashboardService {

    private final CurrentUserService currentUserService;
    private final AppointmentRepository appointmentRepo;
    private final MedicalRecordRepository medicalRecordRepo;
    private final NotificationRepository notificationRepo;

    @Override
    public DoctorDashboardStatsDTO getStats() {
        Long doctorId = currentUserService.currentDoctorId();
        String doctorEmail = currentUserService.currentEmail();
        LocalDate today = LocalDate.now();

        return DoctorDashboardStatsDTO.builder()
                .upcomingAppointments(
                        appointmentRepo.countUpcomingByDoctor(
                                doctorId,
                                today,
                                EStatus.APPROVED
                        )
                )
                .pendingRecords(
                        medicalRecordRepo.countPendingByDoctor(doctorId)
                )
                .newNotifications(
                        notificationRepo.countByReceiver_EmailAndIsReadFalse(
                                doctorEmail
                        )
                )
                .build();
    }

    @Override
    public List<TodayAppointmentDTO> getTodayAppointments() {
        Long doctorId = currentUserService.currentDoctorId();
        LocalDate today = LocalDate.now();
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

        return appointmentRepo
                .findTodayAppointments(
                        doctorId,
                        today,
                        EStatus.APPROVED
                )
                .stream()
                .map(a -> TodayAppointmentDTO.builder()
                        .id(a.getId())
                        .time(a.getDateTime().toLocalTime().format(timeFmt))
                        .patientName(
                                a.getPatient().getLastname() + " " +
                                        a.getPatient().getFirstname()
                        )
                        .status(a.getStatus().name())
                        .build()
                )
                .toList();
    }

    @Override
    public List<PendingMedicalRecordDTO> getPendingMedicalRecords() {
        Long doctorId = currentUserService.currentDoctorId();

        return medicalRecordRepo
                .findTop5ByDoctorIdAndStatusOrderByCreatedAtDesc(
                        doctorId,
                        MedicalRecordStatus.PENDING
                )
                .stream()
                .map(m -> PendingMedicalRecordDTO.builder()
                        .id(m.getId())
                        .patientName(
                                m.getPatient().getLastname() + " " +
                                        m.getPatient().getFirstname()
                        )
                        .createdAt(m.getCreatedAt())
                        .status(m.getStatus().name())
                        .build()
                )
                .toList();
    }
}
