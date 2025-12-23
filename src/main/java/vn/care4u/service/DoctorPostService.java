package vn.care4u.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import vn.care4u.entity.DoctorPost;
import vn.care4u.model.request.CreateDoctorPostRequest;

public interface DoctorPostService {

	DoctorPost createPostByDoctor(CreateDoctorPostRequest req, Authentication auth);

	List<DoctorPost> getPostsByDoctor(Authentication auth);

	DoctorPost getPostById(Long id, Authentication auth);

	DoctorPost updatePost(Long id, CreateDoctorPostRequest req, Authentication auth);

	void deletePost(Long id, Authentication auth);

}
