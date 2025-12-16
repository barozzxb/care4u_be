package vn.care4u.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.care4u.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByAccount_Email(String email);
}
