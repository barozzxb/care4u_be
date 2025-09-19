package vn.care4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Patient;
import vn.care4u.repository.PatientRepository;
import vn.care4u.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	PatientRepository patientRepo;

	@Override
	public <S extends Patient> S save(S entity) {
		return patientRepo.save(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return patientRepo.existsById(id);
	}
	

}
