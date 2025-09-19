package vn.care4u.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OTPData {

	private String otp;
	private Long expirationTime; // in milliseconds
	private int attemptsLeft;
	
	public void decrementAttempts() {
		if (attemptsLeft > 0) {
			attemptsLeft--;
		}
	}
}
