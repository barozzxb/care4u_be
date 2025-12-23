package vn.care4u.controller.api.v1.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import vn.care4u.model.dto.DoctorDTO;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.DoctorService;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorManageAPI {

	@Autowired
	DoctorService doctorServ;
	
	@Operation(summary = "Get all doctors", description = "Get all accounts")
	@GetMapping("/get-all")
	public ApiResponse<Page<DoctorDTO>> getAllDoctors(Pageable pageable) {
		return ApiResponse.<Page<DoctorDTO>>builder()
				.status(200)
				.message("Lấy danh sách tài khoản thành công")
				.body(doctorServ.getAllDoctors(pageable))
				.build();
	}
}
