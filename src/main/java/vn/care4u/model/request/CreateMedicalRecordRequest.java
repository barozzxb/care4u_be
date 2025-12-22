package vn.care4u.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateMedicalRecordRequest {
    private Long patientId;

    // sinh hieu
    private Integer systolicBP;      // Huyết áp tâm thu
    private Integer diastolicBP;     // Huyết áp tâm trương
    private Double temperature;      // Nhiệt độ
    private Integer heartRate;       // Nhịp tim
    private Integer respiratoryRate; // Nhịp thở
    private Double spo2;             // SpO2
    private Double height;           // Chiều cao
    private Double weight;           // Cân nặng
    private Double bmi;              // BMI

    // chan doan
    private String symptoms;         // Triệu chứng
    private String physicalExam;     // Khám thực thể
    private String diagnosis;        // Chẩn đoán
    private String conclusion;       // Kết luận lâm sàng
    private String treatment;        // Phác đồ điều trị
    private String advice;           // Lời dặn
    private String notes;            // Ghi chú thêm
}