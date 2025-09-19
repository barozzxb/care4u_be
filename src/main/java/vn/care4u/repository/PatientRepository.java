package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.care4u.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
