package vn.care4u.enumeration;

import lombok.Getter;

@Getter
public enum ErrorCode {

	ACCOUNT_NOT_FOUND("Tài khoản không tồn tại"), INVALID_CREDENTIALS("Thông tin đăng nhập không hợp lệ"),
	INVALID_INFORMATION("Thông tin không chính xác"), ACCOUNT_DISABLED("Tài khoản đã bị vô hiệu hóa"),
	TOKEN_EXPIRED("Phiên đăng nhập đã hết hạn"), TOKEN_INVALID("Token không hợp lệ"),
	PERMISSION_DENIED("Không có quyền truy cập"), UNKNOWN_ERROR("Lỗi không xác định"),
	ACCOUNT_EXISTED("Tài khoản đã tồn tại"), UNEXPECTED_ERROR("Lỗi không mong đợi"), UNAUTHORIZED("Yêu cầu trái phép"),
	NOT_FOUND("Không tìm thấy"),

	OTP_EXPIRED("Mã OTP đã hết hạn"), MAX_OTP_ATTEMPTS_EXCEEDED("Vượt quá số lần thử mã OTP"),

	NOTIFICATION_NOT_FOUND("Thông báo không tồn tại"),

	INVALID_TOKEN("Token không hợp lệ"),

	PATIENT_NOT_FOUND("Thông tin bệnh nhân không hợp lệ"),

	DEPARTMENT_NOT_FOUND("Chuyên khoa không tồn tại"),

	DEPARTMENT_ALREADY_EXISTS("Chuyên khoa đã tồn tại"),

	ADMIN_NOT_FOUND("Admin not found"), INVALID_IMAGE("Invalid Image"), FILE_TOO_LARGE("File too large"),

	NULL_INFORMATION("Không có thông tin");

	private final String message;

	ErrorCode(String message) {
		this.message = message;
	}
}
