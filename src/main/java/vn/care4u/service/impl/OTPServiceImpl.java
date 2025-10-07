package vn.care4u.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.care4u.entity.Account;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.dto.OTPData;
import vn.care4u.repository.AccountRepository;
import vn.care4u.service.EmailService;
import vn.care4u.service.OTPService;

@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService{
	
	@Autowired
	AccountRepository accRepo;
	
	private final int OTP_LENGTH = 6;
	private final int OTP_EXPIRATION = (int) 1.5 * 60 * 1000; // 1.5 minutes in milliseconds
	private final int MAX_ATTEMPTS = 3;
	
	private final Map<String, OTPData> otpStore = new ConcurrentHashMap<>();
	
	private final EmailService emailService;
	
	private String generateOTP() {
		StringBuilder otp = new StringBuilder();
		for (int i = 0; i < OTP_LENGTH; i++) {
			int digit = (int) (Math.random() * 10);
			otp.append(digit);
		}
		return otp.toString();
	}
	
	@Override
	public void sendOTP(String email) {
		String otp = generateOTP();
		OTPData otpData = new OTPData(otp.toString(), System.currentTimeMillis() + OTP_EXPIRATION, MAX_ATTEMPTS);
		otpStore.put(email, otpData);
		// Send OTP via email
		emailService.sendOTPEmail(email, otp);
	}
	
	@Override
	public boolean verifyOTP(String email, String otp) {
		OTPData storedOtp = otpStore.get(email);
		if (storedOtp == null)
			throw new GeneralException(ErrorCode.OTP_EXPIRED);
		if (storedOtp.getOtp().equals(otp)) {
			Account acc = accRepo.findById(email).orElseThrow(() -> new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND));
			acc.setStatus(true);
			accRepo.save(acc);
			otpStore.remove(email);
			return true;
		} else {
			storedOtp.decrementAttempts();
			if (storedOtp.getAttemptsLeft() <= 0) {
				otpStore.remove(email);
				throw new GeneralException(ErrorCode.MAX_OTP_ATTEMPTS_EXCEEDED);
			} else {
				otpStore.put(email, storedOtp);
			}
		}
		return false;
	}
}
