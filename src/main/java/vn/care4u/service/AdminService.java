package vn.care4u.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import vn.care4u.entity.Admin;
import vn.care4u.model.dto.AdminDTO;

public interface AdminService {

	long MAX_AVATAR_SIZE = 5L * 1024L * 1024L;

	boolean existsById(Long id);

	<S extends Admin> S save(S entity);

	void updateInfo(AdminDTO dto, MultipartFile avatarFile);

	AdminDTO findByEmail(String email);
}
