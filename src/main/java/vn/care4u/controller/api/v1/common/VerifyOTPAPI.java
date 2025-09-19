package vn.care4u.controller.api.v1.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.OTPService;

@RestController
@RequestMapping("/api/v1/common/verify-otp")
@Tag(name = "Verify OTP", description = "Verify OTP API")
public class VerifyOTPAPI {

	@Autowired
	OTPService otpService;
	
	@PostMapping("/send-otp")
	public ApiResponse<String> sendOTP(@RequestBody String email) {
		otpService.sendOTP(email);
		return ApiResponse.<String>builder()
				.status(200)
				.message("Send OTP successfully")
				.build();
	}
}
