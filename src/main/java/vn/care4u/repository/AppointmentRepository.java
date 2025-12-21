package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< Updated upstream
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.care4u.entity.Appointment;

=======
import vn.care4u.entity.Appointment;
import vn.care4u.entity.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;
>>>>>>> Stashed changes
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

<<<<<<< Updated upstream
    List<Appointment> findByDoctorIdOrderByTimeAsc(Long doctorId);

    @Query("""
    select a from Appointment a
    where a.doctor.id = :doctorId
      and ( lower(a.patient.firstname) like lower(concat('%', :q, '%'))
         or lower(a.patient.lastname)  like lower(concat('%', :q, '%'))
         or lower(coalesce(a.notes, '')) like lower(concat('%', :q, '%')) )
    order by a.date asc, a.time asc
  """)
    List<Appointment> search(@Param("doctorId") Long doctorId, @Param("q") String q);

    Optional<Appointment> findByIdAndDoctorId(Long id, Long doctorId);
=======
    Appointment findByDoctorAndDateAndTime(
            Doctor doctor,
            LocalDate date,
            LocalTime time
    );

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    Optional<Appointment> findByIdAndPatientId(Long id, Long patientId);
>>>>>>> Stashed changes
}
