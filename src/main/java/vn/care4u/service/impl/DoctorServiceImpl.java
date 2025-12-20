package vn.care4u.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.care4u.entity.Doctor;
import vn.care4u.model.dto.AppointmentDTO;
import vn.care4u.model.dto.DoctorDTO;
import vn.care4u.model.dto.DoctorProfileDTO;
import vn.care4u.model.request.UpdateDoctorProfileRequest;
import vn.care4u.repository.DoctorRepository;
import vn.care4u.service.DoctorService;
import vn.care4u.service.CurrentUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository doctorRepo;
	private final CurrentUserService currentUserService;

	@Override
	public List<AppointmentDTO> getAppointment(Long doctorId, String q) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void updateAppointmentStatus(Long doctorId, Long apptId, String status) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public Doctor save(Doctor entity) {
		return doctorRepo.save(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return doctorRepo.existsById(id);
	}

	@Override
	public DoctorProfileDTO getMyProfile() {
		Doctor doctor = currentUserService.currentDoctor();
		return mapToProfileDTO(doctor);
	}

	@Override
	@Transactional
	public DoctorProfileDTO updateMyProfile(UpdateDoctorProfileRequest req) {
		Doctor doctor = currentUserService.currentDoctor();

		doctor.setFirstname(req.getFirstname());
		doctor.setLastname(req.getLastname());
		doctor.setPhonenum(req.getPhonenum());
		doctor.setGender(req.getGender());
		doctor.setDob(req.getDob());
		doctor.setAddress(req.getAddress());
		doctor.setBio(req.getBio());
		doctor.setEducation(req.getEducation());
		doctor.setExperience(req.getExperience());
		doctor.setCertification(req.getCertification());
		doctor.setWorkinghour(req.getWorkinghour());

		return mapToProfileDTO(doctorRepo.save(doctor));
	}

	private DoctorProfileDTO mapToProfileDTO(Doctor doctor) {
		return DoctorProfileDTO.builder()
				.id(doctor.getId())
				.firstname(doctor.getFirstname())
				.lastname(doctor.getLastname())
				.gender(doctor.getGender())
				.dob(doctor.getDob())
				.address(doctor.getAddress())
				.phonenum(doctor.getPhonenum())
				.avatar(doctor.getAvatar())
				.bio(doctor.getBio())
				.certification(doctor.getCertification())
				.education(doctor.getEducation())
				.experience(doctor.getExperience())
				.workinghour(doctor.getWorkinghour())
				.build();
	}

	public DoctorDTO mapToDTO(Doctor doctor) {
		return DoctorDTO.builder()
				.id(doctor.getId())
				.firstname(doctor.getFirstname())
				.lastname(doctor.getLastname())
				.gender(doctor.getGender())
				.address(doctor.getAddress())
				.phonenum(doctor.getPhonenum())
				.avatar(doctor.getAvatar())
				.build();
	}
}
