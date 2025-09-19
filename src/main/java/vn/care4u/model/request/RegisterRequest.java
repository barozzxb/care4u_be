package vn.care4u.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.care4u.enumeration.ERole;

@Data
@AllArgsConstructor
public class RegisterRequest {

	private String email;
	private String password;
	private ERole role;
}
