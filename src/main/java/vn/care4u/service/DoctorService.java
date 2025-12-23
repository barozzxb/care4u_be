package vn.care4u.service;

import vn.care4u.entity.Doctor;
import vn.care4u.model.dto.DoctorDTO;
import vn.care4u.model.dto.AppointmentDTO;
import vn.care4u.model.dto.DoctorProfileDTO;
import vn.care4u.model.request.UpdateDoctorProfileRequest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {

	boolean existsById(Long id);

	Doctor save(Doctor entity);

	DoctorDTO mapToDTO(Doctor doctor);

	List<DoctorDTO> mapToDTOList(List<Doctor> doctors);

	List<AppointmentDTO> getAppointment(Long doctorId, String q);

	void updateAppointmentStatus(Long doctorId, Long apptId, String status);

	DoctorProfileDTO getMyProfile();

	DoctorProfileDTO updateMyProfile(UpdateDoctorProfileRequest req);

	Page<DoctorDTO> getAllDoctors(Pageable pageable);
}

