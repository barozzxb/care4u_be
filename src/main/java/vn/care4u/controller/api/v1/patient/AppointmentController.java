package vn.care4u.controller.api.v1.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.care4u.model.dto.AppointmentRequestDTO;
import vn.care4u.service.AppointmentService;
import vn.care4u.service.PatientService;
import vn.care4u.entity.Patient;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody AppointmentRequestDTO request,
            Authentication authentication
    ) {

        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Not authenticated"));
        }

        return ResponseEntity.ok(
                appointmentService.createAppointmentByEmail(
                        authentication.getName(), request
                )
        );
    }

    @GetMapping("/my-appointments")
    public ResponseEntity<?> getMyAppointments(
            Authentication authentication
    ) {

        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Not authenticated"));
        }

        String email = authentication.getName();
        Patient patient = patientService.getPatientById(email);

        return ResponseEntity.ok(
                appointmentService.getAppointmentsByPatientId(
                        patient.getId()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelAppointment(
            @PathVariable Long id,
            Authentication authentication
    ) {

        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Not authenticated"));
        }

        appointmentService.cancelAppointment(
                id,
                authentication.getName()
        );

        return ResponseEntity.ok(
                Map.of("message", "Hủy lịch hẹn thành công")
        );
    }
}
