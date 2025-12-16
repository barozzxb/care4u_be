package vn.care4u.utils;

public class EmailTemplate {

	public static String sendOTPEmail(String to, String otp) {
		return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                  <meta charset="UTF-8">
                  <style>
                    body { font-family: Arial, sans-serif; background: #f9f9f9; padding: 20px; }
                    .container {
                      max-width: 500px; margin: auto; background: #ffffff;
                      padding: 20px; border-radius: 10px; box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                    }
                    .header { text-align: center; padding-bottom: 20px; }
                    .otp {
                      font-size: 24px; font-weight: bold; color: #2c7be5;
                      padding: 10px; background: #f1f5ff; border-radius: 5px; text-align: center;
                      letter-spacing: 4px;
                    }
                    .footer { font-size: 12px; color: #888; text-align: center; margin-top: 20px; }
                  </style>
                </head>
                <body>
                  <div class="container">
                    <div class="header">
                      <h2>🔐 OTP Verification</h2>
                    </div>
                    <p>Hello <b>%s</b>,</p>
                    <p>Here is your One-Time Password (OTP):</p>
                    <div class="otp">%s</div>
                    <p>This code will expire in <b>%d minutes</b>.<br>
                    <p>If you did not request this code, please ignore this email.</p>
                    <div class="footer">
                      &copy; 2025 Care4U. All rights reserved.
                    </div>
                  </div>
                </body>
                </html>
                """.formatted(to, otp, 1);
	}
}
