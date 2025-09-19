package vn.care4u.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	private JwtUtils jwtUtil;
	
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<Account> findById(String id) {
		return accRepo.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return accRepo.existsById(id);
	}

	/**
	 * Input email and password to login
	 */
	@Override
	public AuthResponse login(String email, String password) {
		Optional<Account> optAcc = accRepo.findById(email);
		if(optAcc.isPresent()) {
			Account acc = optAcc.get();
			if(passwordEncoder.matches(password, acc.getPassword())) {
				if(!acc.getStatus()) {
					throw new GeneralException(ErrorCode.ACCOUNT_DISABLED);
				}
				String token = jwtUtil.generateToken(acc.getEmail(), acc.getRole());
				return new AuthResponse(token, acc.getStatus());
			} else {
				throw new GeneralException(ErrorCode.INVALID_INFORMATION);
			} 
		} else {
			throw new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND);
		}
	}
	
	@Override
	public String register(String email, String password,ERole role) {
		if(accRepo.existsById(email)) {
			throw new GeneralException(ErrorCode.ACCOUNT_EXISTED);
		}
		try {
			String encodedPassword = passwordEncoder.encode(password);
			Account newAcc = new Account();
			newAcc.setEmail(email);
			newAcc.setPassword(encodedPassword);
			newAcc.setRole(role);
			newAcc.setStatus(false);
			switch (role) {
			case ADMIN:
				Admin newAdmin = new Admin();
				String fileUrl = "/uploads/avatar/user_default.png";
				newAdmin.setAvatar(fileUrl);
				adminServ.save(newAdmin);
				break;
			case PATIENT:
				Patient newPatient = new Patient();
				String fileUrl1 = "/uploads/avatar/user_default.png";
				newPatient.setAvatar(fileUrl1);
				patientServ.save(newPatient);
				break;
			case DOCTOR:
				Doctor newDoctor = new Doctor();
				String fileUrl2 = "/uploads/avatar/user_default.png";
				newDoctor.setAvatar(fileUrl2);
				doctorServ.save(newDoctor);
				break;
			case STAFF:
				Staff newStaff = new Staff();
				String fileUrl3 = "/uploads/avatar/user_default.png";
				newStaff.setAvatar(fileUrl3);
				staffServ.save(newStaff);
				break;
			default:
				throw new GeneralException(ErrorCode.INVALID_INFORMATION);
			}
			accRepo.save(newAcc);
			return "Register successfully";
		} catch (Exception e) {
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
		
	}

}
