package vn.care4u.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Account;
import vn.care4u.repository.AccountRepository;
import vn.care4u.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accRepo;

	@Override
	public Optional<Account> findById(String id) {
		return accRepo.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return accRepo.existsById(id);
	}

	

}
