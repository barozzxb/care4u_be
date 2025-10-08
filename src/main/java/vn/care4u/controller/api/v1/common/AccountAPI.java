package vn.care4u.controller.api.v1.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
@Tag(name = "Account", description = "Account API")
public class AccountAPI {

	@Autowired
	AccountService accServ;
	
	@Operation(summary = "Set Active Account", description = "Set Active Account by ID")
	@PatchMapping("/set-active")
	public ApiResponse<Void> setActive(String id) {
		accServ.setActive(id);
		return ApiResponse.<Void>builder()
				.status(200)
				.message("Kích hoạt tài khoản thành công")
				.build();
	}
}
