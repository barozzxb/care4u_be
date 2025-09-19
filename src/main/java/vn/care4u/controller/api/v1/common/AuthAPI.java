package vn.care4u.controller.api.v1.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import vn.care4u.service.AccountService;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Authentication API")
public class AuthAPI {
	
	@Autowired
	private AccountService accServ;

	@Operation(summary = "Login", description = "User login with email and password")
	@PostMapping("/login")
	public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {
		AuthResponse authResponse = accServ.login(request.getEmail(), request.getPassword());
		return ApiResponse.<AuthResponse>builder()
				.status(HttpStatus.OK.value())
				.message("Login successfully")
				.data(authResponse)
				.build();
	}
	
	@Operation(summary = "Register", description = "User register with email, password and role")
	@PostMapping("/register")
	public ApiResponse<String> register(@RequestBody RegisterRequest request) {
		String registerResponse = accServ.register(request.getEmail(), request.getPassword(), request.getRole());
		return ApiResponse.<String>builder()
				.status(HttpStatus.OK.value())
				.message("Register successfully")
				.build();
	}
	
}
