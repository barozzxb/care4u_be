package vn.care4u.service.impl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import vn.care4u.service.EmailService;
import vn.care4u.utils.EmailTemplate;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

	private final JavaMailSender sender;
	
	@Override
	public void sendOTPEmail(String to, String otp) {
		try {
			MimeMessage message = sender.createMimeMessage();
			
			String htmlContent = EmailTemplate.sendOTPEmail(to, otp);
			
			message.setSubject("Care4U - Mã OTP xác thực email");
			message.setRecipients(MimeMessage.RecipientType.TO, to.trim());
			message.setText(htmlContent, "utf-8", "html");
			sender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
