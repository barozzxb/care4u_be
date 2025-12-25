package vn.care4u.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.care4u.enumeration.MedicalRecordStatus;

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

	@Column(columnDefinition = "nvarchar(3000)")
	private String symptoms;     // Triệu chứng

	@Column(name = "physical_exam", columnDefinition = "nvarchar(3000)")
	private String physicalExam; // Khám thực thể 

	@Column(columnDefinition = "nvarchar(3000)")
	private String diagnosis;    // Chẩn đoán

	@Column(name = "conclusion", columnDefinition = "nvarchar(3000)")
	private String conclusion;   // Kết luận lâm sàng 

	@Column(columnDefinition = "nvarchar(3000)")
	private String treatment;    // Phác đồ điều trị

	@Column(columnDefinition = "nvarchar(3000)")
	private String advice;       // Lời dặn (Mới)

	@Column(columnDefinition = "nvarchar(3000)")
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

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 20)
	private MedicalRecordStatus status;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.status = MedicalRecordStatus.PENDING;
	}
}