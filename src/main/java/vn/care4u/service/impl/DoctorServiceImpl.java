package vn.care4u.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Doctor;
import lombok.RequiredArgsConstructor;

import vn.care4u.entity.Doctor;
import vn.care4u.model.dto.AppointmentDTO;
import vn.care4u.model.dto.DoctorDTO;
import vn.care4u.repository.DoctorRepository;
import vn.care4u.service.DoctorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{


	private  final DoctorRepository doctorRepo;

	@Override
	public List<AppointmentDTO> getAppointment(Long doctorId, String q) {
		return List.of(); // TODO: implement sau
	}

	@Override
	public void updateAppointmentStatus(Long doctorId, Long apptId, String status) {
		// TODO: implement sau
	}

	@Override
	public Doctor save(Doctor entity) {
		return doctorRepo.save(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return doctorRepo.existsById(id);
	}

	// helper, KHÔNG @Override
	public DoctorDTO mapToDTO(Doctor doctor) {
		return DoctorDTO.builder()
				.id(doctor.getId())
				.firstname(doctor.getFirstname())
				.lastname(doctor.getLastname())
				.gender(doctor.getGender())
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

	@Override
	public List<DoctorDTO> mapToDTOList(List<Doctor> doctors) {
		// TODO Auto-generated method stub
		return null;
	}
}
