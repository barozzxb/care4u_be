package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "measurements")
public class Measurement implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="height", columnDefinition = "double")
	private double height;
	
	@Column(name="weight", columnDefinition = "double")
	private double weight;
	
	@Column(name="bmi", columnDefinition = "double")
	private double bmi;
	
	@Column(name="healthStatus", columnDefinition = "nvarchar(255)")
	private String healthStatus;
	
	@Column(name="timestamp", columnDefinition = "timestamp")
	private Timestamp timestamp;
	
}
