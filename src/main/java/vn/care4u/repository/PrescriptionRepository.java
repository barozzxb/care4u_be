package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.care4u.entity.Prescription;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByPatientIdOrderByCreatedAtDesc(Long patientId);

}
