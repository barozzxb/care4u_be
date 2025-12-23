package vn.care4u.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // ===== AUTH / ACCOUNT =====
    ACCOUNT_NOT_FOUND("Tài khoản không tồn tại"),
    ACCOUNT_EXISTED("Tài khoản đã tồn tại"),
    ACCOUNT_DISABLED("Tài khoản đã bị vô hiệu hóa"),
    INVALID_CREDENTIALS("Thông tin đăng nhập không hợp lệ"),
    INVALID_INFORMATION("Thông tin không chính xác"),
    UNAUTHORIZED("Yêu cầu trái phép"),
    PERMISSION_DENIED("Không có quyền truy cập"),

    // ===== TOKEN =====
    TOKEN_EXPIRED("Phiên đăng nhập đã hết hạn"),
    TOKEN_INVALID("Token không hợp lệ"),

    // ===== OTP =====
    OTP_EXPIRED("Mã OTP đã hết hạn"),
    MAX_OTP_ATTEMPTS_EXCEEDED("Vượt quá số lần thử mã OTP"),

    // ===== DOMAIN =====
    PATIENT_NOT_FOUND("Thông tin bệnh nhân không hợp lệ"),
    DEPARTMENT_NOT_FOUND("Chuyên khoa không tồn tại"),
    DEPARTMENT_ALREADY_EXISTS("Chuyên khoa đã tồn tại"),
    NOTIFICATION_NOT_FOUND("Thông báo không tồn tại"),
    POST_NOT_FOUND("Không tìm thấy bài viết"),

    // ===== ADMIN =====
    ADMIN_NOT_FOUND("Admin not found"),

    // ===== FILE / UPLOAD =====
    INVALID_IMAGE("Invalid image"),
    FILE_TOO_LARGE("File too large"),

    // ===== COMMON =====
    NOT_FOUND("Không tìm thấy"),
    NULL_INFORMATION("Không có thông tin"),
    UNKNOWN_ERROR("Lỗi không xác định"),
    UNEXPECTED_ERROR("Lỗi không mong đợi");

    private final String message;
}
