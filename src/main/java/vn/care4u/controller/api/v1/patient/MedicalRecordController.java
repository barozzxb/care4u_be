package vn.care4u.controller.api.v1.patient;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import vn.care4u.model.dto.MedicalHistoryDTO;
import vn.care4u.service.MedicalRecordService;

@RestController
@RequestMapping("/api/v1/medicalhistory")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @GetMapping
    public ResponseEntity<List<MedicalHistoryDTO>> getMedicalHistory() {
        List<MedicalHistoryDTO> history = medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistoryDTO> getMedicalRecordDetail(@PathVariable Long id) {
        MedicalHistoryDTO dto = medicalRecordService.getMedicalRecordById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MedicalHistoryDTO> createMedicalRecord(@RequestBody MedicalHistoryDTO record) {
        MedicalHistoryDTO dto = medicalRecordService.createMedicalRecord(record);
        return ResponseEntity.ok(dto);
    }
}
