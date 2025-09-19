package vn.care4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Doctor;
import vn.care4u.repository.DoctorRepository;
import vn.care4u.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	DoctorRepository doctorRepo;

	@Override
	public <S extends Doctor> S save(S entity) {
		return doctorRepo.save(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return doctorRepo.existsById(id);
	}
	

}
