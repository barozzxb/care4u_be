package vn.care4u.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OTPSendRequest {
	
	private String email;
}
