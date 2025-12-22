package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.care4u.entity.MedicalRecord;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    List<MedicalRecord> findByPatientIdOrderByCreatedAtDesc(Long patientId);
}
