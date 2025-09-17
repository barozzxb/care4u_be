package vn.care4u.enumeration;

import lombok.Getter;

@Getter
public enum EStatus {

	PENDING("Đang xử lí"), 
	APPROVED("Đã chấp nhận"), 
	REJECTED("Đã từ chối"), 
	COMPLETED("Đã hoàn thành"), 
	CANCELED ("Đã hủy"),
	IN_PROGRESS("Đang tiến hành");
	
	private String value;
	
	private EStatus(String value) {
		this.value = value;
	}
	
}
