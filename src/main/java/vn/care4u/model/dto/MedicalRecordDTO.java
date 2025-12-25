package vn.care4u.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MedicalRecordDTO {
    private Long id;
    private LocalDateTime createdAt;

    private String symptoms;
    private String physicalExam;
    private String diagnosis;
    private String conclusion;
    private String treatment;
    private String advice;
    private String notes;

    private Long doctorId;
    private String doctorName;

    private Long patientId;
    private String patientName;
}
