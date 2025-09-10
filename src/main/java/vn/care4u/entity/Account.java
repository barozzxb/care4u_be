package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.care4u.enumeration.ERole;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "account")

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "email", columnDefinition = "nvarchar(100)")
	private String email;
	
	@Column(name = "password", columnDefinition = "nvarchar(100)")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", columnDefinition = "nvarchar(20)")
	private ERole role;
	
	@Column(name = "createdAt", columnDefinition = "timestamp")
	private Timestamp createdAt;
	
	@Column(name = "status", columnDefinition = "boolean")
	private Boolean status;
	
}
