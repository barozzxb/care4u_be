package vn.care4u.controller.api.v1.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.care4u.model.request.UpdateDoctorProfileRequest;
import vn.care4u.service.DoctorService;

@RestController
@RequestMapping("/api/v1/doctor/profile")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
public class DoctorProfileAPI {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok(doctorService.getMyProfile());
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(
            @RequestBody UpdateDoctorProfileRequest req
    ) {
        return ResponseEntity.ok(doctorService.updateMyProfile(req));
    }
}

