package vn.care4u.controller.api.v1.doctor;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.care4u.model.request.CreateAppointmentRequest;
import vn.care4u.model.request.UpdateAppointmentStatusRequest;
import vn.care4u.service.DoctorUsecaseService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/doctor/appointments")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('DOCTOR')")
@Slf4j
public class AppointmentAPI {

    private final DoctorUsecaseService svc;

    //Doctor xem va thao tac lich cua minh
    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) String q) {
        return ResponseEntity.ok(svc.listAppointments(q));
    }

    //tao lich
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateAppointmentRequest req) {
        log.info("POST /doctor/appointments body={}", req);
        var created = svc.createAppointment(req);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/{id}/action")
    public ResponseEntity<?> action(@PathVariable Long id, @RequestBody UpdateAppointmentStatusRequest req) {
        log.info("POST /doctor/appointments/{}/action body={}", id, req);
        svc.manageAppointment(id, req.getAction());
        return ResponseEntity.ok(Map.of("message","Cập nhật thành công"));
    }

}
