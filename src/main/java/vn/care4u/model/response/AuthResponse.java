package vn.care4u.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import vn.care4u.model.dto.UserDetailDTO;

@AllArgsConstructor
@Data
@Builder
public class AuthResponse {

	private String token;
	private String refreshToken;
	private boolean status;
	private String role;
	private UserDetailDTO user;
}
