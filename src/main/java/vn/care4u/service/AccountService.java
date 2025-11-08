package vn.care4u.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.care4u.entity.Account;
import vn.care4u.enumeration.ERole;
import vn.care4u.model.dto.AccountDTO;
import vn.care4u.model.response.AuthResponse;

public interface AccountService {

	boolean existsById(String id);

	Optional<Account> findById(String id);

	void setActive(String id);

	long count();

	Page<Account> findAll(Pageable pageable);

	List<AccountDTO> findActiveAccounts();

	long countActiveAccounts();

	List<AccountDTO> findRecentAccounts();


}
