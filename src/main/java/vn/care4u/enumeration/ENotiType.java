package vn.care4u.enumeration;

import lombok.Getter;

@Getter

public enum ENotiType {

	INFO("Thông tin"), 
	WARNING ("Cảnh báo"), 
	SYSTEM ("Hệ thống");
	
	private final String value;
	
	private ENotiType(String value) {
		this.value = value;
	}
}
