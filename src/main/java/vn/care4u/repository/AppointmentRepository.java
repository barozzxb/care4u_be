package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.care4u.entity.Appointment;
import vn.care4u.entity.Doctor;
import vn.care4u.entity.Patient;
import vn.care4u.enumeration.EStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Appointment findByDoctorAndDateAndTime(Doctor doctor, LocalDate date, LocalTime time);

	List<Appointment> findByPatientId(Long patientId);

	List<Appointment> findByDoctorId(Long doctorId);

	Optional<Appointment> findByIdAndPatientId(Long id, Long patientId);

	List<Appointment> findByDoctorIdOrderByTimeAsc(Long doctorId);

	Optional<Appointment> findFirstByPatientAndDoctorOrderByDateDesc(Patient patient, Doctor doctor);
	
	@Query("""
			  select a from Appointment a
			  where a.doctor.id = :doctorId
			    and ( lower(a.patient.firstname) like lower(concat('%', :q, '%'))
			       or lower(a.patient.lastname)  like lower(concat('%', :q, '%'))
			       or lower(coalesce(a.notes, '')) like lower(concat('%', :q, '%')) )
			  order by a.date asc, a.time asc
			""")
	List<Appointment> search(@Param("doctorId") Long doctorId, @Param("q") String q);

	@Query("""
			    SELECT COUNT(a)
			    FROM Appointment a
			    WHERE a.doctor.id = :doctorId
			      AND a.date >= :today
			      AND a.status = :status
			""")
	long countUpcomingByDoctor(@Param("doctorId") Long doctorId, @Param("today") LocalDate today,
			@Param("status") EStatus status);

	@Query("""
			    SELECT a
			    FROM Appointment a
			    WHERE a.doctor.id = :doctorId
			      AND a.date = :today
			      AND a.status = :status
			    ORDER BY a.time ASC
			""")
	List<Appointment> findTodayAppointments(@Param("doctorId") Long doctorId, @Param("today") LocalDate today,
			@Param("status") EStatus status);

	Optional<Appointment> findByIdAndDoctorId(Long id, Long doctorId);
}