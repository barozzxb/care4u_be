package vn.care4u.controller.api.v1.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.dto.AdminDTO;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.AdminService;

@RestController
@RequestMapping("/api/v1/admin/info")
@Tag(name = "Admin Information API", description = "API for admin to update information")
public class AdminAPI {

	@Autowired
	private AdminService adminServ;
	
	@GetMapping("/{email}")
	public ApiResponse<AdminDTO> getInfo(@PathVariable String email) {
		
		return ApiResponse.<AdminDTO>builder()
				.status(200)
				.message("Cập nhật dữ liệu thành công")
				.body(adminServ.findByEmail(email))
				.build();
	};
	
	@PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Void> updateInfo(
            @ModelAttribute AdminDTO dto,
            @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile) {

        adminServ.updateInfo(dto, avatarFile);

        return ApiResponse.<Void>builder()
                .status(200)
                .message("Cập nhật thông tin thành công")
                .build();
    }
}
