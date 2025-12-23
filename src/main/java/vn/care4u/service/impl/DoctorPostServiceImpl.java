package vn.care4u.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.care4u.entity.*;
import vn.care4u.enumeration.PostStatus;
import vn.care4u.model.request.CreateDoctorPostRequest;
import vn.care4u.repository.*;
import vn.care4u.service.DoctorPostService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorPostServiceImpl implements DoctorPostService {

    private final DoctorPostRepository postRepo;
    private final DoctorRepository doctorRepo;

    @Override
    public DoctorPost createPostByDoctor(CreateDoctorPostRequest req, Authentication auth) {
        String email = auth.getName();

        Doctor doctor = doctorRepo.findByAccountEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        DoctorPost post = new DoctorPost();
        post.setTitle(req.getTitle());
        post.setContent(req.getContent());
        post.setThumbnail(req.getThumbnail());
        post.setStatus(PostStatus.PUBLISHED);
        post.setDoctor(doctor);
        post.setCreatedAt(LocalDateTime.now());

        return postRepo.save(post);
    }

    @Override
    public List<DoctorPost> getPostsByDoctor(Authentication auth) {
        String email = auth.getName();
        Doctor doctor = doctorRepo.findByAccountEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return postRepo.findByDoctor(doctor);
    }

    private Doctor getDoctorFromAuth(Authentication auth) {
        String email = auth.getName();
        return doctorRepo.findByAccountEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    private DoctorPost getOwnedPost(Long id, Authentication auth) {
        Doctor doctor = getDoctorFromAuth(auth);

        DoctorPost post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getDoctor().getId().equals(doctor.getId())) {
            throw new org.springframework.security.access.AccessDeniedException("Not your post");
        }
        return post;
    }

    @Override
    public DoctorPost getPostById(Long id, Authentication auth) {
        return getOwnedPost(id, auth);
    }

    @Override
    public DoctorPost updatePost(
            Long id,
            CreateDoctorPostRequest req,
            Authentication auth
    ) {
        DoctorPost post = getOwnedPost(id, auth);
        post.setTitle(req.getTitle());
        post.setContent(req.getContent());
        post.setThumbnail(req.getThumbnail());
        return postRepo.save(post);
    }

    @Override
    public void deletePost(Long id, Authentication auth) {
        DoctorPost post = getOwnedPost(id, auth);
        postRepo.delete(post);
    }

}
