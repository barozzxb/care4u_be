package vn.care4u.controller.api.v1.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.dto.DepartmentDTO;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.DepartmentService;

@RestController
@RequestMapping("/api/v1/departments")
@Tag(name = "Department", description = "Department API for Admin to manage")
public class DepartmentAPI {

	@Autowired
	private DepartmentService depServ;
	
	@Operation(summary = "Get all departments", description = "Get all departments")
	@GetMapping("/")
	public ApiResponse<List<DepartmentDTO>> getAll() {
		return ApiResponse.<List<DepartmentDTO>>builder()
				.status(200)
				.message("Lấy danh sách phòng ban thành công")
				.body(depServ.getAllDepartments())
				.build();
	}
	
	@Operation(summary = "Get department by id", description = "Get department by id")
	@GetMapping("/{id}")
	public ApiResponse<DepartmentDTO> getById(@PathVariable String id) {
		return ApiResponse.<DepartmentDTO>builder()
				.status(200)
				.message("Lấy phòng ban thành công")
				.body(depServ.getDepartmentById(id))
				.build();
	}
	
	@PostMapping("/create")
	public ApiResponse<Void> create(@RequestBody DepartmentDTO departmentDTO) {
		depServ.createDepartment(departmentDTO);
		return ApiResponse.<Void>builder()
				.status(200)
				.message("Tạo phòng ban thành công")
				.build();
	}
	
	@PutMapping("/update")
	public ApiResponse<Void> update(@RequestBody DepartmentDTO departmentDTO) {
		depServ.editDepartment(departmentDTO);
		return ApiResponse.<Void>builder()
				.status(200)
				.message("Cập nhật phòng ban thành công")
				.build();
	}
	
	@PutMapping("/delete/{id}")
	public ApiResponse<Void> delete(@PathVariable String id) {
		depServ.deleteDepartment(id);
		return ApiResponse.<Void>builder()
				.status(200)
				.message("Xoá phòng ban thành công")
				.build();
	}
}
