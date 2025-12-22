package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.care4u.entity.Appointment;
import vn.care4u.entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.care4u.enumeration.EStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorIdOrderByDateAscTimeAsc(Long doctorId);

    Optional<Appointment> findByIdAndDoctorId(Long id, Long doctorId);
    Appointment findByDoctorAndDateAndTime(
            Doctor doctor,
            LocalDate date,
            LocalTime time
    );

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);


    @Query("""
        SELECT COUNT(a)
        FROM Appointment a
        WHERE a.doctor.id = :doctorId
          AND a.date >= :today
          AND a.status = :status
    """)
    long countUpcomingByDoctor(
            @Param("doctorId") Long doctorId,
            @Param("today") LocalDate today,
            @Param("status") EStatus status
    );
    @Query("""
    SELECT a
    FROM Appointment a
    WHERE a.doctor.id = :doctorId
      AND a.date = :today
      AND a.status = :status
    ORDER BY a.time ASC
""")
    List<Appointment> findTodayAppointments(
            @Param("doctorId") Long doctorId,
            @Param("today") LocalDate today,
            @Param("status") EStatus status
    );

}
