package vn.care4u.service;

import java.util.List;

import vn.care4u.model.dto.DepartmentDTO;

public interface DepartmentService {

	void deleteDepartment(String id);

	void editDepartment(DepartmentDTO dto);

	void createDepartment(DepartmentDTO departmentDTO);

	DepartmentDTO getDepartmentById(String id);

	List<DepartmentDTO> getAllDepartments();

}
