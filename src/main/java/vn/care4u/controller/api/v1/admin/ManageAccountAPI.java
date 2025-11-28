package vn.care4u.controller.api.v1.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.dto.AccountDTO;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Accounts", description = "Accounts API for Admin to manage")
public class ManageAccountAPI {

	@Autowired
	private AccountService accServ;
	
	@Operation(summary = "Get all accounts", description = "Get all accounts")
	@GetMapping
	public ApiResponse<Page<AccountDTO>> getAll(Pageable pageable) {
		return ApiResponse.<Page<AccountDTO>>builder()
				.status(200)
				.message("Lấy danh sách tài khoản thành công")
				.body(accServ.findAll(pageable))
				.build();
	}
	
//	@Operation(summary = "Get account by id", description = "Get account by id")
//	@GetMapping("/{id}")
//	public ApiResponse<AccountDTO> getById(@PathVariable String id) {
//		return ApiResponse.<AccountDTO>builder()
//				.status(200)
//				.message("Lấy phòng ban thành công")
//				.body(accServ.findById(id))
//				.build();
//	}
	
	@PutMapping("/deactive/{id}")
	public ApiResponse<Void> lock(@PathVariable String id) {
		accServ.deActive(id);
		return ApiResponse.<Void>builder()
				.status(200)
				.message("Khóa tài khoản thành công")
				.build();
	}
	
	@PutMapping("/active/{id}")
	public ApiResponse<Void> active(@PathVariable String id) {
		accServ.setActive(id);
		return ApiResponse.<Void>builder()
				.status(200)
				.message("Mở khóa tài khoản thành công")
				.build();
	}
}
