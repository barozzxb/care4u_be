package vn.care4u.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.care4u.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Optional<Patient> findByAccount_Email(String email);  // Tìm qua Account
	Optional<Patient> findByEmail(String email);          // Tìm trực tiếp
}
