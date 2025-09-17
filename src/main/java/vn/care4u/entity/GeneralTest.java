package vn.care4u.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "general_tests")
public class GeneralTest extends MedicalTest{

	@Column(name = "height", columnDefinition = "DOUBLE")
	private double height;
	
	@Column(name = "weight", columnDefinition = "DOUBLE")
	private double weight;
	
	@Column(name = "bmi", columnDefinition = "DOUBLE")
	private double bmi;
	
	@Column(name = "eyesight", columnDefinition = "INT")
	private int eyesight;
	
	@Column(name = "blood_pressure", columnDefinition = "int")
	private int bloodPressure;
}
