package vn.care4u.service;

import vn.care4u.entity.Doctor;
import vn.care4u.model.dto.DoctorDTO;
import vn.care4u.model.dto.AppointmentDTO;
import vn.care4u.model.dto.DoctorProfileDTO;
import vn.care4u.model.request.UpdateDoctorProfileRequest;

import java.util.List;

public interface DoctorService {

	boolean existsById(Long id);

	<S extends Doctor> S save(S entity);

	DoctorDTO mapToDTO(Doctor doctor);

	List<DoctorDTO> mapToDTOList(List<Doctor> doctors);

	List<AppointmentDTO> getAppointment(Long doctorId, String q);

	void updateAppointmentStatus(Long doctorId, Long apptId, String status);

	Doctor save(Doctor entity);
	boolean existsById(Long id);

	DoctorProfileDTO getMyProfile();
	DoctorProfileDTO updateMyProfile(UpdateDoctorProfileRequest req);

}
