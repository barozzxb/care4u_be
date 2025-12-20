package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.care4u.entity.Measurement;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Optional<Measurement> findByMedicalRecordId(Long medicalRecordId);

    List<Measurement> findByPatientIdOrderByTimeDesc(Long patientId);
}