package vn.care4u.controller.api.v1.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.dto.PostDTO;
import vn.care4u.model.request.PostCreateRequest;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.PostService;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "Post API", description = "API for posts")
public class PostAPI {

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
	
	@GetMapping("/{id}")
	public ApiResponse<PostDTO> getById(@PathVariable("id") Long id) {
		return ApiResponse.<PostDTO>builder()
				.status(200)
				.message("Lấy danh sách bài viết thành công")
				.body(postServ.getById(id))
				.build();
	}
}