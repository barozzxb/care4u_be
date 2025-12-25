package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.care4u.entity.MedicalRecord;
import vn.care4u.enumeration.MedicalRecordStatus;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    List<MedicalRecord> findByDoctorIdOrderByCreatedAtDesc(Long doctorId);

    List<MedicalRecord> findByPatientIdOrderByCreatedAtDesc(Long patientId);

    @Query("""
        SELECT COUNT(m)
        FROM MedicalRecord m
        WHERE m.doctor.id = :doctorId
          AND m.status = 'PENDING'
    """)
    long countPendingByDoctor(@Param("doctorId") Long doctorId);

    List<MedicalRecord> findTop5ByDoctorIdAndStatusOrderByCreatedAtDesc(
            Long doctorId,
            MedicalRecordStatus status
    );
}
