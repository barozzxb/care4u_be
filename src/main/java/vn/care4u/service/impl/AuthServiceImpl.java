package vn.care4u.service.impl;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import vn.care4u.model.dto.UserDetailDTO;
import vn.care4u.model.request.LoginRequest;
import vn.care4u.model.response.AuthResponse;
import vn.care4u.repository.AccountRepository;
import vn.care4u.service.AdminService;
import vn.care4u.service.AuthService;
import vn.care4u.service.DoctorService;
import vn.care4u.service.PatientService;
import vn.care4u.service.RedisService;
import vn.care4u.service.StaffService;
import vn.care4u.service.UserDetailService;
import vn.care4u.utils.JwtUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
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
	
	@Autowired
	UserDetailService userDetailServ;
	
	private final JwtUtils jwtUtil;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authManager;
	
	private final RedisService redisService;
	
	public Optional<Account> findById(String id) {
		return accRepo.findById(id);
	}

	public boolean existsById(String id) {
		return accRepo.existsById(id);
	}

	/**
	 * Input email and password to login
	 */
	@Override
	public AuthResponse login(LoginRequest request) {
		//authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		Account acc = accRepo.findById(request.getEmail()).orElseThrow(() -> new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND));
		
		if(!passwordEncoder.matches(request.getPassword(), acc.getPassword())) {
			throw new GeneralException(ErrorCode.INVALID_CREDENTIALS);
		}
		
		if(!acc.getStatus()) {
			throw new GeneralException(ErrorCode.ACCOUNT_DISABLED);
		}
		
		String token = jwtUtil.generateToken(acc.getEmail(), acc.getRole());
		String refreshToken = jwtUtil.generateRefreshToken(acc.getEmail());
		
		String redisKey = "refreshToken:" + acc.getEmail();
		redisService.set(redisKey, refreshToken, jwtUtil.getJwtRefreshExpirationMs(), TimeUnit.MILLISECONDS);
		
		UserDetailDTO dto = userDetailServ.getDetail(acc);
		return new AuthResponse(token, refreshToken, acc.getStatus(), acc.getRole().name(), dto);
	}
	
	@Override
	public void logout(String email) {
		String redisKey = "refreshToken:" + email;
		redisService.delete(redisKey);
	}
	
	@Override
	public String refreshToken(String refreshToken) {
		if(!jwtUtil.validateJwtToken(refreshToken)) {
			throw new GeneralException(ErrorCode.INVALID_TOKEN);
		}
		
		String email = jwtUtil.getUsernameFromJwtToken(refreshToken);
		String redisKey = "refreshToken:" + email;
		String storedRefreshToken = redisService.get(redisKey);
		if(storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
			throw new GeneralException(ErrorCode.INVALID_TOKEN);
		}
		Account acc = accRepo.findById(email).orElseThrow(() -> new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND));
		String newToken = jwtUtil.generateToken(acc.getEmail(), acc.getRole());
		return newToken;
	}
	
	@Transactional(rollbackOn = Exception.class)
	@Override
	public String register(String email, String password,ERole role) {
		if (email == null || email.isEmpty() || password == null || password.isEmpty() || role == null) {
			throw new GeneralException(ErrorCode.INVALID_INFORMATION);
		}
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
			accRepo.save(newAcc);
			switch (role) {
			case ADMIN:
				Admin newAdmin = new Admin();
				String fileUrl = "/uploads/avatar/user_default.png";
				newAdmin.setAvatar(fileUrl);
				newAdmin.setAccount(newAcc);
				adminServ.save(newAdmin);
				break;
			case PATIENT:
				Patient newPatient = new Patient();
				String fileUrl1 = "/uploads/avatar/user_default.png";
				newPatient.setAvatar(fileUrl1);
				newPatient.setAccount(newAcc);
				patientServ.save(newPatient);
				break;
			case DOCTOR:
				Doctor newDoctor = new Doctor();
				String fileUrl2 = "/uploads/avatar/user_default.png";
				newDoctor.setAvatar(fileUrl2);
				newDoctor.setAccount(newAcc);
				doctorServ.save(newDoctor);
				break;
			case STAFF:
				Staff newStaff = new Staff();
				String fileUrl3 = "/uploads/avatar/user_default.png";
				newStaff.setAvatar(fileUrl3);
				newStaff.setAccount(newAcc);
				staffServ.save(newStaff);
				break;
			default:
				throw new GeneralException(ErrorCode.INVALID_INFORMATION);
			}
			return "Register successfully";
		} catch (Exception e) {
			e.printStackTrace();
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
		
	}
}
