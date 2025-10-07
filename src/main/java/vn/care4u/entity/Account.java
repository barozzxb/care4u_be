package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	
	@CreationTimestamp
	@Column(name = "createdAt", columnDefinition = "timestamp")
	private Timestamp createdAt;
	
	@Column(name = "status", columnDefinition = "boolean")
	private Boolean status;
	
	@OneToOne(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
	private Admin admin;
	
	@OneToOne(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
	private Doctor doctor;
	
	@OneToOne(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
	private Patient patient;
	
	@OneToOne(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
	private Staff staff;
}
