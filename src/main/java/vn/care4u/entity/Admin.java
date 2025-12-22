package vn.care4u.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data

@Entity
@Table(name = "admins")
public class Admin extends User{

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "account_email", referencedColumnName = "email")
	private Account account;
}
