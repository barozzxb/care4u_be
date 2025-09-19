package vn.care4u.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@MappedSuperclass
public abstract class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "firstname", columnDefinition = "nvarchar(50)")
	protected String firstname;
	
	@Column(name = "lastname", columnDefinition = "nvarchar(50)")
	protected String lastname;
	
	@Column(name = "gender", columnDefinition = "nvarchar(10)")
	protected String gender;
	
	@Column(name = "dob", columnDefinition = "date")
	protected LocalDate dob;
	
	@Column(name = "address", columnDefinition = "nvarchar(50)")
	protected String address;
	
	@Column(name = "phonenum", columnDefinition = "nvarchar(15)")
	protected String phonenum;
	
	@Column(name = "avatar", columnDefinition = "nvarchar(255)")	
	protected String avatar;
	
}
