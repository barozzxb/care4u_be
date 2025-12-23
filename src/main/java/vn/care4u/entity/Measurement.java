package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "measurements")
public class Measurement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "double")
    private double height;

    @Column(columnDefinition = "double")
    private double weight;

    @Column(columnDefinition = "double")
    private double bmi;

    @Column(columnDefinition = "nvarchar(255)")
    private String healthStatus;

    @Column
    private int heartRate;

    @Column(columnDefinition = "nvarchar(255)")
    private String bloodPressure;

    @Column
    private Double temperature;

    @Column
    private LocalDateTime time;
    
    private Integer systolicBloodPressure;
	private Integer diastolicBloodPressure;
	private Integer respiratoryRate;
	private Double spo2;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medical_record_id")
	private MedicalRecord medicalRecord;
	
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}