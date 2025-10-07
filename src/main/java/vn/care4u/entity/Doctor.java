package vn.care4u.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "doctors")
public class Doctor extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="bio", columnDefinition = "TEXT")
	private String bio;
	
	@Column(name="certification", columnDefinition = "nvarchar(255)")
	private String certification;
	
	@Column(name="education", columnDefinition = "nvarchar(255)")
	private String education;
	
	@Column(name="experience", columnDefinition = "nvarchar(5000)")
	private String experience;
	
	@Column(name="workinghour", columnDefinition = "nvarchar(255)")
	private String workinghour;
	
	@OneToOne
	@JoinColumn(name = "account_email")
	private Account account;
}
