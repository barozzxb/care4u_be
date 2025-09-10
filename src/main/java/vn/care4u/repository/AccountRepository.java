package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.care4u.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	
}
