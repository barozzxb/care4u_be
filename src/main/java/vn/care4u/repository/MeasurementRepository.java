package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.care4u.entity.Measurement;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    // Hàm này giúp tìm sinh hiệu theo ID phiếu khám (sau này dùng để xem lại chi tiết)
    Optional<Measurement> findByMedicalRecordId(Long medicalRecordId);

    // Hàm này giúp tìm tất cả lịch sử đo của bệnh nhân (để vẽ biểu đồ sức khỏe)
    List<Measurement> findByPatientIdOrderByTimeDesc(Long patientId);
}