package vn.care4u.service;

import java.util.Optional;

import vn.care4u.entity.Account;
import vn.care4u.enumeration.ERole;
import vn.care4u.model.response.AuthResponse;

public interface AccountService {

	boolean existsById(String id);

	Optional<Account> findById(String id);

	/**
	 * Input email and password to login
	 */
	AuthResponse login(String email, String password);

	String register(String email, String password, ERole role);


}
