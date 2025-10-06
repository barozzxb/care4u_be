package vn.care4u.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.care4u.entity.Account;
import vn.care4u.entity.Admin;
import vn.care4u.entity.Doctor;
import vn.care4u.entity.Patient;
import vn.care4u.entity.Staff;
import vn.care4u.enumeration.ERole;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.response.AuthResponse;
import vn.care4u.repository.AccountRepository;
import vn.care4u.service.AccountService;
import vn.care4u.service.AdminService;
import vn.care4u.service.DoctorService;
import vn.care4u.service.PatientService;
import vn.care4u.service.StaffService;
import vn.care4u.utils.JwtUtils;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accRepo;
	
	@Autowired
	AdminService adminServ;
	
	@Autowired
	PatientService patientServ;
	
	@Autowired
	DoctorService doctorServ;
	
	@Autowired
	StaffService staffServ;
	
	private final JwtUtils jwtUtil;
	
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<Account> findById(String id) {
		return accRepo.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return accRepo.existsById(id);
	}

}
