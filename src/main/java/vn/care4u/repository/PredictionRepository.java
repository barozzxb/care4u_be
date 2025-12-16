package vn.care4u.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.care4u.entity.Patient;
import vn.care4u.entity.Prediction;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {

	List<Prediction> findByPatient(Patient patient);
	List<Prediction> findByPatientId(Long patientId);
}
