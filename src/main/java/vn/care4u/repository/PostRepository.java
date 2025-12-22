package vn.care4u.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.care4u.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	Page<Post> findByAccount_Email(String email, Pageable pageable);
}
