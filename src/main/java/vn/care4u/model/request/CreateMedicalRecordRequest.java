package vn.care4u.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateMedicalRecordRequest {
    private Long patientId;

    // --- 1. NHÓM SINH HIỆU (MEASUREMENT) ---
    private Integer systolicBP;      // Huyết áp tâm thu
    private Integer diastolicBP;     // Huyết áp tâm trương
    private Double temperature;      // Nhiệt độ
    private Integer heartRate;       // Nhịp tim
    private Integer respiratoryRate; // Nhịp thở
    private Double spo2;             // SpO2
    private Double height;           // Chiều cao
    private Double weight;           // Cân nặng
    private Double bmi;              // BMI (FE gửi lên thì hứng, ko thì thôi)

    // --- 2. NHÓM KHÁM & CHẨN ĐOÁN (MEDICAL RECORD) ---
    private String symptoms;         // Triệu chứng
    private String physicalExam;     // Khám thực thể (Mới)
    private String diagnosis;        // Chẩn đoán
    private String conclusion;       // Kết luận lâm sàng (Mới)
    private String treatment;        // Phác đồ điều trị
    private String advice;           // Lời dặn (Mới)
    private String notes;            // Ghi chú thêm
}