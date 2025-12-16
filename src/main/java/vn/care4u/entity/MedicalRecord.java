package vn.care4u.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "medicalrecord")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Patient patient;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	// --- CÁC TRƯỜNG DỮ LIỆU ---
	@Column(columnDefinition = "nvarchar(MAX)")
	private String symptoms;     // Triệu chứng

	@Column(name = "physical_exam", columnDefinition = "nvarchar(MAX)")
	private String physicalExam; // Khám thực thể (Mới)

	@Column(columnDefinition = "nvarchar(MAX)")
	private String diagnosis;    // Chẩn đoán

	@Column(name = "conclusion", columnDefinition = "nvarchar(MAX)")
	private String conclusion;   // Kết luận lâm sàng (Mới)

	@Column(columnDefinition = "nvarchar(MAX)")
	private String treatment;    // Phác đồ điều trị

	@Column(columnDefinition = "nvarchar(MAX)")
	private String advice;       // Lời dặn (Mới)

	@Column(columnDefinition = "nvarchar(MAX)")
	private String notes;        // Ghi chú

	@Column(name = "systolic_bp")
	private Integer systolicBP;

	@Column(name = "diastolic_bp")
	private Integer diastolicBP;

	@Column(name = "temperature")
	private Double temperature;

	@Column(name = "heart_rate")
	private Integer heartRate;

	@Column(name = "respiratory_rate")
	private Integer respiratoryRate;

	@Column(name = "spo2")
	private Double spo2;

	@Column(name = "height")
	private Double height;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "bmi")
	private Double bmi;


	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
}