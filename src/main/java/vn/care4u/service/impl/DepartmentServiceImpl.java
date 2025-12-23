package vn.care4u.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Department;
import vn.care4u.entity.Doctor;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.dto.DepartmentDTO;
import vn.care4u.repository.DepartmentRepository;
import vn.care4u.repository.DoctorRepository;
import vn.care4u.service.DepartmentService;
import vn.care4u.service.DoctorService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepo;
	
	@Autowired
	DoctorService doctorServ;
	
	@Autowired
	DoctorRepository doctorRepo;
	
	@Override
	public List<DepartmentDTO> getAllDepartments() {
		return departmentRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	@Override
	public DepartmentDTO getDepartmentById(String id) {
		Department department = departmentRepo.findById(id).orElseThrow( () -> new GeneralException(ErrorCode.DEPARTMENT_NOT_FOUND) );
		return mapToDTO(department);
	}
	
	@Override
	public void createDepartment(DepartmentDTO departmentDTO) {
		if(departmentRepo.existsById(departmentDTO.getId())) {
			throw new GeneralException(ErrorCode.DEPARTMENT_ALREADY_EXISTS);
		}
		try {
			Department department = Department.builder()
					.id(departmentDTO.getId())
					.name(departmentDTO.getName())
					.description(departmentDTO.getDescription())
					.createdAt(new Timestamp(System.currentTimeMillis()))
					.build();
			departmentRepo.save(department);
		} catch (Exception e) {
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
	}
	
	@Override
	public void editDepartment(DepartmentDTO dto) {
		Department dpm = departmentRepo.findById(dto.getId()).orElseThrow(() -> new GeneralException(ErrorCode.DEPARTMENT_NOT_FOUND));
		try {
			dpm.setDescription(dto.getDescription());
			dpm.setName(dto.getName());
			
			departmentRepo.save(dpm);
		} catch (Exception e) {
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
	}
	
	@Override
	public void deleteDepartment(String id) {
		Department dpm = departmentRepo.findById(id).orElseThrow(() -> new GeneralException(ErrorCode.DEPARTMENT_NOT_FOUND));
		try {
			dpm.setDescription("Chuyên khoa tạm khóa");
			departmentRepo.save(dpm);
		} catch (Exception e) {
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
	}
	
	@Override
	public void addDoctorToDepartment(Long doctorId, String id ) {
		Doctor d = doctorRepo.findById(doctorId).orElseThrow(() -> new GeneralException(ErrorCode.DOCTOR_NOT_FOUND));
		Department dpm = departmentRepo.findById(id).orElseThrow(() -> new GeneralException(ErrorCode.DEPARTMENT_NOT_FOUND));
		try {
			
			dpm.getDoctors().add(d);
			departmentRepo.save(dpm);
		} catch (Exception e) {
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
	}
	
	@Override
	public void removeDoctorFromDepartment(Long doctorId, String id ) {
		Doctor d = doctorRepo.findById(doctorId).orElseThrow(() -> new GeneralException(ErrorCode.DOCTOR_NOT_FOUND));
		Department dpm = departmentRepo.findById(id).orElseThrow(() -> new GeneralException(ErrorCode.DEPARTMENT_NOT_FOUND));
		
		try {
			
			dpm.getDoctors().remove(d);
			departmentRepo.save(dpm);
		} catch (Exception e) {
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
	}
	
	private DepartmentDTO mapToDTO(Department department) {
		return DepartmentDTO.builder()
				.id(department.getId())
				.name(department.getName())
				.description(department.getDescription())
				.doctors(department.getDoctors().stream().map(doctorServ::mapToDTO).collect(Collectors.toList()))
				.build();
	}
}
