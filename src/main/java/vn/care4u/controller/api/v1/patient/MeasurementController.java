package vn.care4u.controller.api.v1.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.care4u.entity.Measurement;
import vn.care4u.model.dto.MeasurementUpdateDTO;
import vn.care4u.service.MeasurementService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/patient")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @PostMapping("/update-measure")
    public ResponseEntity<Map<String, Object>> updateMeasurement(
            @RequestParam String email,
            @RequestBody MeasurementUpdateDTO dto
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            Measurement measurement = measurementService.updateMeasurement(email, dto);
            response.put("success", true);
            response.put("message", "Cập nhật chỉ số sức khỏe thành công");
            response.put("data", measurement);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(400).body(response);
        }
    }

    @GetMapping("/measure")
    public ResponseEntity<Map<String, Object>> getLatestMeasurement(@RequestParam String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            Measurement measurement = measurementService.getLatestMeasurement(email);
            response.put("success", true);
            response.put("data", measurement);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("data", null);
            response.put("message", e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }
}
