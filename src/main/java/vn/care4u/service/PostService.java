package vn.care4u.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import vn.care4u.entity.Account;
import vn.care4u.model.dto.PostDTO;
import vn.care4u.model.request.PostCreateRequest;

public interface PostService {

	void deletePost(Long id);

	void updatePost(PostCreateRequest dto, MultipartFile image);

	void addPost(PostCreateRequest dto, MultipartFile image, Account account);

	PostDTO getById(Long id);

	Page<PostDTO> getAllPost(Pageable pageble);

}
