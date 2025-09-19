package vn.care4u.enumeration;

import lombok.Getter;

@Getter

public enum ERole {

	PATIENT ("Người bệnh"),
	DOCTOR ("Bác sĩ"),
	ADMIN ("Quản trị viên"),
	STAFF ("Nhân viên");
	
	private final String description;
	
	private ERole(String description) {
		this.description = description;
	}
}
