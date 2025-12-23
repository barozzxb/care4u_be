package vn.care4u.controller.api.v1.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.care4u.model.request.CreateDoctorPostRequest;
import vn.care4u.service.DoctorPostService;

@RestController
@RequestMapping("/api/v1/doctor/posts")
@RequiredArgsConstructor
public class DoctorPostController {

	private final DoctorPostService postService;

	@PreAuthorize("hasRole('DOCTOR')")
	@PostMapping
	public ResponseEntity<?> createPost(@RequestBody CreateDoctorPostRequest request, Authentication auth) {
		return ResponseEntity.ok(postService.createPostByDoctor(request, auth));
	}

	@PreAuthorize("hasRole('DOCTOR')")
	@GetMapping
	public ResponseEntity<?> myPosts(Authentication auth) {
		return ResponseEntity.ok(postService.getPostsByDoctor(auth));
	}

	// 🔥 LẤY 1 POST (EDIT)
	@PreAuthorize("hasRole('DOCTOR')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id, Authentication auth) {
		return ResponseEntity.ok(postService.getPostById(id, auth));
	}

	// 🔥 UPDATE
	@PreAuthorize("hasRole('DOCTOR')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CreateDoctorPostRequest request,
			Authentication auth) {
		return ResponseEntity.ok(postService.updatePost(id, request, auth));
	}

	// 🔥 DELETE
	@PreAuthorize("hasRole('DOCTOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id, Authentication auth) {
		postService.deletePost(id, auth);
		return ResponseEntity.noContent().build();
	}
}
