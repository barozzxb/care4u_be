package vn.care4u.controller.api.v1.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.dto.DashboardDTO;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.DashboardService;

@RestController
@RequestMapping("/api/v1/admin/dashboard")
@Tag(name = "Admin Dashboard API", description = "API for admin dashboard functionalities")
public class DashboardAPI {
	
	@Autowired
	DashboardService dashboardServ;

	@GetMapping("/")
	public ApiResponse<DashboardDTO> getDashboardData() {
		
		return ApiResponse.<DashboardDTO>builder()
				.status(200)
				.message("Cập nhật dữ liệu dashboard thành công")
				.body(dashboardServ.getData())
				.build();
	}
}
