package vn.care4u.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OTPVerifyRequest {
	
	private String email;
	private String otp;
}
