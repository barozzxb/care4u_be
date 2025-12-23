package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.care4u.entity.DoctorPost;
import vn.care4u.entity.Doctor;

import java.util.List;

public interface DoctorPostRepository extends JpaRepository<DoctorPost, Long> {

    List<DoctorPost> findByDoctor(Doctor doctor);
}
