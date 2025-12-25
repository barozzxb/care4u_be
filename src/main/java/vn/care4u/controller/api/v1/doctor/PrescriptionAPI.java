package vn.care4u.controller.api.v1.doctor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.care4u.model.dto.PrescriptionDetailDTO;
import vn.care4u.model.dto.PrescriptionItemDTO;
import vn.care4u.model.dto.PrescriptionListDTO;
import vn.care4u.model.request.CreatePrescriptionRequest;
import vn.care4u.repository.PrescriptionItemRepository;
import vn.care4u.repository.PrescriptionRepository;
import vn.care4u.service.CurrentUserService;
import vn.care4u.service.DoctorUsecaseService;

@RestController
@RequestMapping("/api/v1/doctor/prescriptions")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
public class PrescriptionAPI {

    private final DoctorUsecaseService svc;
    private final PrescriptionRepository prescriptionRepo;
    private final PrescriptionItemRepository prescriptionItemRepository;
    private final CurrentUserService currentUser;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePrescriptionRequest req) {
        return ResponseEntity.ok(svc.createPrescription(req));
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) Long patientId) {
        Long doctorId = currentUser.currentDoctorId();

        var list = (patientId == null)
                ? prescriptionRepo.findByDoctorIdOrderByCreatedAtDesc(doctorId)
                : prescriptionRepo.findByDoctorIdAndPatientIdOrderByCreatedAtDesc(doctorId, patientId);

        var result = list.stream().map(p -> {
            var items = prescriptionItemRepository.findByPrescriptionId(p.getId());

            var preview = items.stream().limit(3).map(it ->
                    new PrescriptionItemDTO(
                            it.getId(),
                            it.getDrug() != null ? it.getDrug().getId() : null,
                            it.getName(),
                            it.getDose(),
                            it.getQuantity(),
                            it.getNote()
                    )
            ).toList();

            String patientName = (p.getPatient().getLastname() + " " + p.getPatient().getFirstname()).trim();

            return new PrescriptionListDTO(
                    p.getId(),
                    p.getCreatedAt(),
                    p.getPatient().getId(),
                    patientName,
                    items.size(),
                    preview
            );
        }).toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Long doctorId = currentUser.currentDoctorId();

        var p = prescriptionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        if (!p.getDoctor().getId().equals(doctorId)) {
            return ResponseEntity.status(403).build();
        }

        var items = prescriptionItemRepository.findByPrescriptionId(p.getId());

        var itemDtos = items.stream().map(it ->
                new PrescriptionItemDTO(
                        it.getId(),
                        it.getDrug() != null ? it.getDrug().getId() : null,
                        it.getName(),
                        it.getDose(),
                        it.getQuantity(),
                        it.getNote()
                )
        ).toList();

        String patientName =
                (p.getPatient().getLastname() + " " + p.getPatient().getFirstname()).trim();

        return ResponseEntity.ok(
                new PrescriptionDetailDTO(
                        p.getId(),
                        p.getCreatedAt(),
                        p.getPatient().getId(),
                        patientName,
                        itemDtos
                )
        );
    }

}
