package vn.care4u.enumeration;

import lombok.Getter;

@Getter

public enum ETestStatus {

	PENDING ("Đang chờ..."), 
	COMPLETED ("Đã hoàn thành"), 
	IN_PROGRESS ("Đang tiến hành");
	
	private final String value;
	
	private ETestStatus(String value) {
		this.value = value;
	}
}
