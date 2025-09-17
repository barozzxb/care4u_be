package vn.care4u.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.care4u.enumeration.ETestStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data

@MappedSuperclass
public abstract class MedicalTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "date", columnDefinition = "TIMESTAMP")
	protected Timestamp date;
	
	@Column(name = "evaluate", columnDefinition = "TEXT")
	protected String evaluate;
	
	@Enumerated
	@Column(name = "status", columnDefinition = "NVARCHAR(50)")
	protected ETestStatus status;
	
	@Column(name = "notes", columnDefinition = "TEXT")
	protected String notes;
}
