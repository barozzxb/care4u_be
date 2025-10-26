package vn.care4u.controller.api.v1.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.request.OTPVerifyRequest;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.OTPService;

@RestController
@RequestMapping("/api/v1/common/otp")
@Tag(name = "Verify OTP", description = "Verify OTP API")
public class VerifyOTPAPI {

	@Autowired
	OTPService otpService;
	
	@Operation(summary = "Send OTP", description = "Send OTP to email")
	@PostMapping("/send")
	public ApiResponse<String> sendOTP(@RequestBody String email) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonNode = mapper.readTree(email);
			email = jsonNode.get("email").asText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		otpService.sendOTP(email);
		return ApiResponse.<String>builder()
				.status(200)
				.message("Đã gửi mã OTP đến email")
				.build();
	}
	
	@Operation(summary = "Verify OTP", description = "Verify OTP using email and otp")
	@PostMapping("/verify")
	public ApiResponse<String> verifyOTP(@RequestBody OTPVerifyRequest request) {
		boolean result = otpService.verifyOTP(request.getEmail(), request.getOtp());
		return result ? ApiResponse.<String>builder()
				.status(200)
				.message("Xác thực OTP thành công")
				.build() 
				: ApiResponse.<String>builder()
				.status(400)
				.message("Sai mã OTP hoặc mã OTP đã hết hiệu lực")
				.build();
	}
}
