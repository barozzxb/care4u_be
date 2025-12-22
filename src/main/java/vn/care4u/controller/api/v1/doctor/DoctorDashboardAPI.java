package vn.care4u.controller.api.v1.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.care4u.service.DoctorDashboardService;

@RestController
@RequestMapping("/api/v1/doctor/dashboard")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
public class DoctorDashboardAPI {

    private final DoctorDashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<?> stats() {
        return ResponseEntity.ok(dashboardService.getStats());
    }

    @GetMapping("/appointments/today")
    public ResponseEntity<?> todayAppointments() {
        return ResponseEntity.ok(dashboardService.getTodayAppointments());
    }

    @GetMapping("/medical-records/pending")
    public ResponseEntity<?> pendingRecords() {
        return ResponseEntity.ok(dashboardService.getPendingMedicalRecords());
    }
}
