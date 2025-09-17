package vn.care4u.enumeration;

import lombok.Getter;

@Getter
public enum EPostType {

	INFORMATION("Thông tin"),
	WARNING("Cảnh báo"),
	ADMIN_NOTICE("Thông báo từ Admin"),
	SHARING("Chia sẻ"),
	FAQ("Câu hỏi thường gặp");
	
	private final String value;
	
	EPostType(String value) {
		this.value = value;
	}
	
}
