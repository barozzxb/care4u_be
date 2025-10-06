package vn.care4u.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "staffs")
public class Staff extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "speciality", columnDefinition = "nvarchar(100)")
	private String speciality;
	
	@OneToOne
	@JoinColumn(name = "account_email")
	private Account account;
}
