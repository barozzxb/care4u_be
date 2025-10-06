package vn.care4u.service;

import jakarta.transaction.Transactional;
import vn.care4u.enumeration.ERole;
import vn.care4u.model.request.LoginRequest;
import vn.care4u.model.response.AuthResponse;

public interface AuthService {

	String register(String email, String password, ERole role);

	/**
	 * Input email and password to login
	 */
	AuthResponse login(LoginRequest request);

	String refreshToken(String refreshToken);

	void logout(String email);

}
