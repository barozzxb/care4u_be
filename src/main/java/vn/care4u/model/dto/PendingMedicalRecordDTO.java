package vn.care4u.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PendingMedicalRecordDTO {
    private Long id;
    private String patientName;
    private LocalDateTime createdAt;
    private String status;
}
