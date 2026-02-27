package vn.care4u.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalRecordDetailDTO {
    private Long id;
    private LocalDateTime createdAt;

    private String symptoms;
    private String physicalExam;
    private String diagnosis;
    private String conclusion;
    private String treatment;
    private String advice;
    private String notes;

    private Long patientId;
    private String patientFirstname;
    private String patientLastname;
    private String patientAvatar;

    private Integer systolicBP;
    private Integer diastolicBP;
    private Double temperature;
    private Integer heartRate;
    private Integer respiratoryRate;
    private Double spo2;
    private Double height;
    private Double weight;
    private Double bmi;
}
