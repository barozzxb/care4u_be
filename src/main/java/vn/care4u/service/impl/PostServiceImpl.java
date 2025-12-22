package vn.care4u.service.impl;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import vn.care4u.entity.Account;
import vn.care4u.entity.Post;
import vn.care4u.enumeration.EPostType;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.dto.PostDTO;
import vn.care4u.model.request.PostCreateRequest;
import vn.care4u.repository.PostRepository;
import vn.care4u.service.PostService;

@Service
public class PostServiceImpl implements PostService {


	private static final List<String> ALLOWED_IMAGE_TYPES = List.of("image/jpeg", "image/png", "image/gif",
			"image/jpg");
	private final long MAX_AVATAR_SIZE = 25L * 1024L * 1024L;
	private final String uploadDir = "./uploads/posts";
	private final String postUrlPrefix = "/uploads/posts";

	@Autowired
	PostRepository postRepo;

	

	public boolean existsById(Long id) {
		return postRepo.existsById(id);
	}

	@Override
	public Page<PostDTO> getAllPost(Pageable pageble) {
		Page<Post> posts = postRepo.findAll(pageble);
		return posts.map(this::mapToDTO);
	}

	@Override
	public PostDTO getById(Long id) {
		Optional<Post> post = postRepo.findById(id);
		if (!post.isPresent())
			return null;
		return mapToDTO(post.get());
	}

	public Page<PostDTO> getAllPostByEmail(String email, Pageable pageable) {
		Page<Post> posts = postRepo.findByAccount_Email(email, pageable);
		return posts.map(this::mapToDTO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addPost(PostCreateRequest dto,MultipartFile image, Account account) {
		try {
			Post post = Post.builder()
					.title(dto.getTitle())
					.type(EPostType.valueOf(dto.getType()))
					.content(dto.getContent())
					.created(new Timestamp(System.currentTimeMillis()))
					.updated(new Timestamp(System.currentTimeMillis()))
					.account(account)
					.build();
			if (image != null && !image.isEmpty()) {
                String contentType = image.getContentType();
                if (contentType == null || !ALLOWED_IMAGE_TYPES.contains(contentType)) {
                    throw new GeneralException(ErrorCode.INVALID_IMAGE);
                }

                if (image.getSize() > MAX_AVATAR_SIZE) {
                    throw new GeneralException(ErrorCode.FILE_TOO_LARGE);
                }

                Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
                Files.createDirectories(uploadPath);

                String original = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
                String ext = "";
                int i = original.lastIndexOf('.');
                if (i >= 0) ext = original.substring(i);
                String fileName = dto.getId() + "_" + UUID.randomUUID() + ext;

                Path target = uploadPath.resolve(fileName);

                try (InputStream in = image.getInputStream()) {
                    Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
                }

                String avatarUrl = postUrlPrefix.endsWith("/") 
                        ? postUrlPrefix + fileName 
                        : postUrlPrefix + "/" + fileName;

                post.setImage(avatarUrl);
			}
			postRepo.save(post);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
	}

	@Override
	public void updatePost(PostCreateRequest dto, MultipartFile image) {
		try {
			Post post = postRepo.findById(dto.getId()).orElseThrow(() -> new GeneralException(ErrorCode.POST_NOT_FOUND));

			post.setContent(dto.getContent());
			post.setTitle(dto.getTitle());
			post.setType(EPostType.valueOf(dto.getType()));
			post.setUpdated(new Timestamp(System.currentTimeMillis()));

			if (image != null && !image.isEmpty()) {
                String contentType = image.getContentType();
                if (contentType == null || !ALLOWED_IMAGE_TYPES.contains(contentType)) {
                    throw new GeneralException(ErrorCode.INVALID_IMAGE);
                }

                if (image.getSize() > MAX_AVATAR_SIZE) {
                    throw new GeneralException(ErrorCode.FILE_TOO_LARGE);
                }

//                if (dto.getImage() != null && !dto.getImage().isBlank()) {
//                    try {
//                        String existing = dto.getImage();
//                        if (existing.startsWith(postUrlPrefix)) {
//                            existing = existing.substring(postUrlPrefix.length());
//                        }
//                        Path existingPath = Paths.get(uploadDir).resolve(existing).normalize();
//                        Files.deleteIfExists(existingPath);
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }

                Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
                Files.createDirectories(uploadPath);

                String original = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
                String ext = "";
                int i = original.lastIndexOf('.');
                if (i >= 0) ext = original.substring(i);
                String fileName = dto.getId() + "_" + UUID.randomUUID() + ext;

                Path target = uploadPath.resolve(fileName);

                try (InputStream in = image.getInputStream()) {
                    Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
                }

                String avatarUrl = postUrlPrefix.endsWith("/") 
                        ? postUrlPrefix + fileName 
                        : postUrlPrefix + "/" + fileName;

                post.setImage(avatarUrl);
			}
			postRepo.save(post);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deletePost(Long id) {
		try {
			postRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
	}

	private PostDTO mapToDTO(Post p) {
		return PostDTO.builder().id(p.getId()).title(p.getTitle()).type(p.getType().name()).content(p.getContent())
				.image(p.getImage()).created(p.getCreated()).updated(p.getUpdated()).account_email(p.getAccount().getEmail()).build();
	}

}
