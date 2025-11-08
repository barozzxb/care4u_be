package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "medicalrecord")
public class MedicalRecord implements Serializable{

	/**
	 * 
	 */
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
	
	@Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "diagnosis", columnDefinition = "nvarchar(255)")
	private String diagnosis;

	@Column(name = "symptoms", columnDefinition = "nvarchar(255)")
	private String symptoms;
	
	@Column(name = "treatment", columnDefinition = "nvarchar(255)")
	private String treatment;
	
	@Column(name = "notes", columnDefinition = "nvarchar(255)")
	private String notes;


}
