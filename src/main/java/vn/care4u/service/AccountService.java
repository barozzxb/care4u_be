package vn.care4u.service;

import java.util.Optional;

import vn.care4u.entity.Account;

public interface AccountService {

	boolean existsById(String id);

	Optional<Account> findById(String id);


}
