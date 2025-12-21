package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.care4u.entity.Appointment;
import vn.care4u.entity.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByDoctorAndDateAndTime(
            Doctor doctor,
            LocalDate date,
            LocalTime time
    );

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    Optional<Appointment> findByIdAndPatientId(Long id, Long patientId);
}
