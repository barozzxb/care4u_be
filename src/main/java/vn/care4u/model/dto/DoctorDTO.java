package vn.care4u.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoctorDTO {

	protected Long id;
	
	protected String firstname;
	
	protected String lastname;
	
	protected String gender;

	protected String address;

	protected String phonenum;

	protected String avatar;
	
	private String bio;

	private String certification;

	private String education;

	private String experience;
	
	private String workinghour;
	
}
