package vn.care4u.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.care4u.entity.Account;
import vn.care4u.enumeration.ERole;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, String>{
	
	@Query("SELECT COUNT(a) FROM Account a WHERE a.status = true")
	long countActiveAccounts();
	
	@Query("SELECT a FROM Account a WHERE a.status = true")
	List<Account> findActiveAccounts();
	
	Page<Account> findByRole(ERole role, Pageable pageable);

}
