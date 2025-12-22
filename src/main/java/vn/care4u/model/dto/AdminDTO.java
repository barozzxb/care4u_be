package vn.care4u.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminDTO {
	protected Long id;

	protected String firstname;

	protected String lastname;
	
	protected LocalDate dob;

	protected String gender;

	protected String address;

	protected String phonenum;

	protected String avatar;
	
	protected String email;
}
