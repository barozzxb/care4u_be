package vn.care4u.service.impl;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import vn.care4u.entity.Admin;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.dto.AdminDTO;
import vn.care4u.repository.AdminRepository;
import vn.care4u.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepo;

	private static final List<String> ALLOWED_IMAGE_TYPES = List.of("image/jpeg", "image/png", "image/gif", "image/jpg");
	private final String uploadDir = "./uploads/avatar";
    private final String avatarUrlPrefix = "/uploads/avatar";

	@Override
	public <S extends Admin> S save(S entity) {
		return adminRepo.save(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return adminRepo.existsById(id);
	}

	@Override
	public AdminDTO findByEmail(String email) {
		Admin admin = adminRepo.findByAccount_Email(email)
				.orElseThrow(() -> new GeneralException(ErrorCode.ADMIN_NOT_FOUND));
		return this.mapToDTO(admin);
	}

	@Override
	@Transactional
    public void updateInfo(AdminDTO dto, MultipartFile avatarFile) {
        Admin admin = adminRepo.findByAccount_Email(dto.getEmail())
                .orElseThrow(() -> new GeneralException(ErrorCode.ADMIN_NOT_FOUND));

        try {
            admin.setFirstname(dto.getFirstname());
            admin.setLastname(dto.getLastname());
            admin.setAddress(dto.getAddress());
            admin.setDob(dto.getDob());
            admin.setGender(dto.getGender());
            admin.setPhonenum(dto.getPhonenum());

            if (avatarFile != null && !avatarFile.isEmpty()) {
                String contentType = avatarFile.getContentType();
                if (contentType == null || !ALLOWED_IMAGE_TYPES.contains(contentType)) {
                    throw new GeneralException(ErrorCode.INVALID_IMAGE);
                }

                if (avatarFile.getSize() > MAX_AVATAR_SIZE) {
                    throw new GeneralException(ErrorCode.FILE_TOO_LARGE);
                }

                if (admin.getAvatar() != null && !admin.getAvatar().isBlank()) {
                    try {
                        String existing = admin.getAvatar();
                        if (existing.startsWith(avatarUrlPrefix)) {
                            existing = existing.substring(avatarUrlPrefix.length());
                        }
                        Path existingPath = Paths.get(uploadDir).resolve(existing).normalize();
                        Files.deleteIfExists(existingPath);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
                Files.createDirectories(uploadPath);

                String original = StringUtils.cleanPath(Objects.requireNonNull(avatarFile.getOriginalFilename()));
                String ext = "";
                int i = original.lastIndexOf('.');
                if (i >= 0) ext = original.substring(i);

                String emailPrefix = dto.getEmail().split("@")[0];
                emailPrefix = emailPrefix.replaceAll("[^a-zA-Z0-9-_]", "");
                String fileName = emailPrefix + "_" + UUID.randomUUID() + ext;

                Path target = uploadPath.resolve(fileName);

                try (InputStream in = avatarFile.getInputStream()) {
                    Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
                }

                String avatarUrl = avatarUrlPrefix.endsWith("/") 
                        ? avatarUrlPrefix + fileName 
                        : avatarUrlPrefix + "/" + fileName;

                admin.setAvatar(avatarUrl);
            }

            adminRepo.save(admin);
        } catch (GeneralException ge) {
            throw ge;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
        }
	}

	public AdminDTO mapToDTO(Admin admin) {
		return AdminDTO.builder().id(admin.getId()).firstname(admin.getFirstname()).lastname(admin.getLastname())
				.gender(admin.getGender()).address(admin.getAddress()).phonenum(admin.getPhonenum())
				.avatar(admin.getAvatar()).email(admin.getAccount().getEmail()).dob(admin.getDob()).build();

	}
}
