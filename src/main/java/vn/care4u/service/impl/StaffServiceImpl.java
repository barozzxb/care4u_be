package vn.care4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Staff;
import vn.care4u.repository.StaffRepository;
import vn.care4u.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	StaffRepository staffRepo;

	@Override
	public <S extends Staff> S save(S entity) {
		return staffRepo.save(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return staffRepo.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		staffRepo.deleteById(id);
	}
	
}
