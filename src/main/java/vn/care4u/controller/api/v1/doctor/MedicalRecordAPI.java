package vn.care4u.controller.api.v1.doctor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.care4u.model.request.CreateMedicalRecordRequest; // Đảm bảo bạn đã tạo class Request này
import vn.care4u.service.DoctorUsecaseService;

@RestController
@RequestMapping("/api/v1/doctor/medical-records")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('DOCTOR')")
public class MedicalRecordAPI {

    private final DoctorUsecaseService svc;

    // API Tạo phiếu khám
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateMedicalRecordRequest req) {
        log.info("POST /doctor/medical-records body={}", req);

        // Bạn cần đảm bảo hàm createMedicalRecord đã có trong Service nhé
        var createdRecord = svc.createMedicalRecord(req);

        return ResponseEntity.ok(createdRecord);
    }

    // API Lấy danh sách phiếu khám (Nếu cần)
    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) String patientId) {
        // Logic lấy danh sách...
        return ResponseEntity.ok("Danh sách phiếu khám (TODO)");
    }
}