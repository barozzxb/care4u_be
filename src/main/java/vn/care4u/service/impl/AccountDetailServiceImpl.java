package vn.care4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Account;
import vn.care4u.model.AccountDetail;
import vn.care4u.repository.AccountRepository;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {

	@Autowired
	AccountRepository accRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account acc = accRepo.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản với username: " + username));
		return new AccountDetail(acc);
	}

}
