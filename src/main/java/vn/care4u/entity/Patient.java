package vn.care4u.entity;

import jakarta.persistence.Column;

public class Patient extends User {

	private static final long serialVersionUID = 1L;

	@Column(name="insurance", columnDefinition = "nvarchar(255)")
	String insurance;
	
	@Column(name="ralativePhone", columnDefinition = "nvarchar(255)")
	String ralativePhone;

}
