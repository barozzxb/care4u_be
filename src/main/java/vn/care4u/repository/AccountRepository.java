package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.care4u.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	
}
