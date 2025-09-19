package vn.care4u.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Entity
@Table(name = "admins")
public class Admin extends User{

	private static final long serialVersionUID = 1L;
	
}
