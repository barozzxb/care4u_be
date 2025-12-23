package vn.care4u.controller.api.v1.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.entity.Account;
import vn.care4u.model.AccountDetail;
import vn.care4u.model.dto.PostDTO;
import vn.care4u.model.request.PostCreateRequest;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.PostService;

@RestController
@RequestMapping("/api/v1/admin/posts")
@Tag(name = "Manage Post API", description = "API for managing posts for ADMIN")
public class ManagePostAPI {

	@Autowired
	PostService postServ;
	
	@GetMapping("")
	public ApiResponse<Page<PostDTO>> getAll(Pageable pageable) {
		return ApiResponse.<Page<PostDTO>>builder()
				.status(200)
				.message("Lấy danh sách bài viết thành công")
				.body(postServ.getAllPost(pageable))
				.build();
	}
	
	@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<Void> addPost(@ModelAttribute PostCreateRequest dto,
	        @RequestParam(value = "image", required = false) MultipartFile image,
	        Authentication authentication) {
		AccountDetail principal = (AccountDetail) authentication.getPrincipal();
	    Account account = principal.getAccount();
	    
		postServ.addPost(dto, image, account);
		return ApiResponse.<Void>builder()
				.status(201)
				.message("Tạo bài viết thành công")
				.build();
	}
	
	@PutMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<Void> editPost(@ModelAttribute PostCreateRequest dto,
	        @RequestParam(value = "image", required = false) MultipartFile image,
	        Authentication authentication) {
		postServ.updatePost(dto, image);
		return ApiResponse.<Void>builder()
				.status(200)
				.message("Chỉnh sửa bài viết thành công")
				.build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ApiResponse<Void> deletePost(@PathVariable("id") Long id) {
		postServ.deletePost(id);
		return ApiResponse.<Void>builder()
				.status(200)
				.message("Xóa bài viết thành công")
				.build();
	}
	
}
