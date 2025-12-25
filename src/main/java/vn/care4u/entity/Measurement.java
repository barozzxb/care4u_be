package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "measurement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medical_record_id")
	private MedicalRecord medicalRecord;

	private LocalDateTime time;

	private Integer systolicBloodPressure;  // HA tâm thu
	private Integer diastolicBloodPressure; // HA tâm trương
	private Double temperature;
	private Integer heartRate;
	private Integer respiratoryRate;
	private Double spo2;
	private Double height;
	private Double weight;
	private Double bmi;

	@PrePersist
	public void prePersist() {
		this.time = LocalDateTime.now();
	}
}
