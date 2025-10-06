package vn.care4u.controller.api.v1.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.request.LoginRequest;
import vn.care4u.model.request.RegisterRequest;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.model.response.AuthResponse;
import vn.care4u.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Authentication API")
public class AuthAPI {
	
	@Autowired
	private AuthService authServ;

	@Operation(summary = "Login", description = "User login with email and password")
	@PostMapping("/login")
	public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {
		AuthResponse authResponse = authServ.login(request);		
		return ApiResponse.<AuthResponse>builder()
				.status(200)
				.message("Đăng nhập thành công")
				.body(authResponse)
				.build();
	}
	
	@Operation(summary = "Logout", description = "User logout")
	@PostMapping("/logout")
	public ApiResponse<String> logout(@RequestBody String email) {
		authServ.logout(email);
		return ApiResponse.<String>builder()
				.status(200)
				.message("Đăng xuất thành công")
				.build();
	}
	
	@Operation(summary = "Register", description = "User register with email, password and role")
	@PostMapping("/register")
	public ApiResponse<String> register(@RequestBody RegisterRequest request) {
		String registerResponse = authServ.register(request.getEmail(), request.getPassword(), request.getRole());
		return ApiResponse.<String>builder()
				.status(200)
				.message("Đăng ký thành công")
				.build();
	}
	
	@Operation(summary = "Refresh Token", description = "Refresh access token using refresh token")
	@PostMapping("/refresh-token")
	public ApiResponse<String> refreshToken(@RequestBody String refreshToken) {
		String newAccessToken = authServ.refreshToken(refreshToken);
		return ApiResponse.<String>builder()
				.status(200)
				.message("Làm mới token thành công")
				.body(newAccessToken)
				.build();
	}
	
}
