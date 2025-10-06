package vn.care4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Account;
import vn.care4u.entity.Admin;
import vn.care4u.entity.Doctor;
import vn.care4u.entity.Patient;
import vn.care4u.entity.Staff;
import vn.care4u.model.dto.UserDetailDTO;
import vn.care4u.service.AdminService;
import vn.care4u.service.DoctorService;
import vn.care4u.service.PatientService;
import vn.care4u.service.StaffService;
import vn.care4u.service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService{

	@Autowired
	PatientService patientServ;
	
	@Autowired
	DoctorService doctorServ;
	
	@Autowired
	AdminService adminServ;
	
	@Autowired
	StaffService staffServ;
	
	@Override
	public UserDetailDTO getDetail(Account acc) {
		
		UserDetailDTO userDetail = new UserDetailDTO();
		switch (acc.getRole()) {
		case PATIENT:
			Patient p = acc.getPatient();
			userDetail.setAvatar(p.getAvatar());
			userDetail.setFirstname(p.getFirstname());
			userDetail.setLastname(p.getLastname());
			break;
		case DOCTOR:
			Doctor d = acc.getDoctor();
			userDetail.setAvatar(d.getAvatar());
			userDetail.setFirstname(d.getFirstname());
			userDetail.setLastname(d.getLastname());
			break;
		case ADMIN:
			Admin a = acc.getAdmin();
			userDetail.setAvatar(a.getAvatar());
			userDetail.setFirstname(a.getFirstname());
			userDetail.setLastname(a.getLastname());
			break;
		case STAFF:
			Staff s = acc.getStaff();
			userDetail.setAvatar(s.getAvatar());
			userDetail.setFirstname(s.getFirstname());
			userDetail.setLastname(s.getLastname());
			break;
		default:
			break;
		}
		
		return userDetail;
	}
}
