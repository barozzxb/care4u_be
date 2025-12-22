package vn.care4u.entity;

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
	private Patient patient;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(columnDefinition = "nvarchar(3000)")
	private String symptoms;     // Triệu chứng

	@Column(name = "physical_exam", columnDefinition = "nvarchar(3000)")
	private String physicalExam; // Khám thực thể (Mới)

	@Column(columnDefinition = "nvarchar(3000)")
	private String diagnosis;    // Chẩn đoán

	@Column(name = "conclusion", columnDefinition = "nvarchar(3000)")
	private String conclusion;   // Kết luận lâm sàng (Mới)

	@Column(columnDefinition = "nvarchar(3000)")
	private String treatment;    // Phác đồ điều trị

	@Column(columnDefinition = "nvarchar(3000)")
	private String advice;       // Lời dặn (Mới)

	@Column(columnDefinition = "nvarchar(3000)")
	private String notes;        // Ghi chú

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
}