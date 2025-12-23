package vn.care4u.controller.api.v1.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.care4u.model.dto.DoctorDTO;
import vn.care4u.service.DoctorService;
import vn.care4u.repository.DoctorRepository;
import vn.care4u.entity.Doctor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorRepository doctorRepo; 
    
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return ResponseEntity.ok(doctorService.mapToDTO(doctor));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsByDepartment(@PathVariable Long departmentId) {
        List<Doctor> doctors = doctorRepo.findAll().stream()
                .filter(d -> d.getDepartment() != null && d.getDepartment().getId().equals(departmentId))
                .toList();
        return ResponseEntity.ok(doctorService.mapToDTOList(doctors));
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<Doctor> doctors = doctorRepo.findAll();
        return ResponseEntity.ok(doctorService.mapToDTOList(doctors));
    }
}
