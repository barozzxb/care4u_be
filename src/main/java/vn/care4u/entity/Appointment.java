package vn.care4u.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	@Column(name="status", columnDefinition = "String")
	private EStatus status;
	
	@Column(name="notes", columnDefinition = "nvarchar(255)")
	private String notes;

}
