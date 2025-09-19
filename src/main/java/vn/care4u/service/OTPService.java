package vn.care4u.service;

public interface OTPService {

	boolean verifyOTP(String email, String otp);

	void sendOTP(String email);

}
