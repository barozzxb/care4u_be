package vn.care4u.service;

import vn.care4u.entity.Doctor;
<<<<<<< Updated upstream
import vn.care4u.model.dto.AppointmentDTO;
=======
import vn.care4u.model.dto.DoctorDTO;
>>>>>>> Stashed changes

import java.util.List;

public interface DoctorService {

<<<<<<< Updated upstream
	List<AppointmentDTO> getAppointment(Long doctorId, String q);

	void updateAppointmentStatus(Long doctorId, Long apptId, String status);

	Doctor save(Doctor entity);
	boolean existsById(Long id);

=======
    boolean existsById(Long id);

    <S extends Doctor> S save(S entity);

    DoctorDTO mapToDTO(Doctor doctor);

    List<DoctorDTO> mapToDTOList(List<Doctor> doctors);
>>>>>>> Stashed changes
}
