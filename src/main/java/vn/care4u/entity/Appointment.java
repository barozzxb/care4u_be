package vn.care4u.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
<<<<<<< Updated upstream

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.care4u.enumeration.EStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="appointments")
public class Appointment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="date", columnDefinition = "date")
	private LocalDate date;
	
	@Column(name="time", columnDefinition = "time")
	private LocalTime time;
	
	@Column(name="place", columnDefinition = "nvarchar(255)")
	private String place;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", columnDefinition = "nvarchar(20)")
	private EStatus status;
	
	@Column(name="notes", columnDefinition = "nvarchar(255)")
	private String notes;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@Transient
	public LocalDateTime getDateTime() {
		return LocalDateTime.of(date, time);
	}

}
=======
import vn.care4u.enumeration.EStatus;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @Column(columnDefinition = "date")
    private LocalDate date;
    
    @Column(columnDefinition = "time")
    private LocalTime time;
    
    @Column(columnDefinition = "nvarchar(255)")
    private String place;
    
    @Enumerated(EnumType.STRING)
    private EStatus status;
    
    @Column(columnDefinition = "nvarchar(255)")
    private String notes;
}
>>>>>>> Stashed changes
