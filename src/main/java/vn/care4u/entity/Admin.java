package vn.care4u.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "admins")
public class Admin extends User{

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "account_email")
	private Account account;
}
