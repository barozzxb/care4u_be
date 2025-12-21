package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.care4u.entity.Measurement;
import vn.care4u.entity.Patient;

import java.util.Optional;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    Optional<Measurement> findTopByPatientOrderByTimestampDesc(Patient patient);
}
