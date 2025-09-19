package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.care4u.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
