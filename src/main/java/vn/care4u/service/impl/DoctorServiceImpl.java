package vn.care4u.service.impl;

<<<<<<< Updated upstream
import lombok.RequiredArgsConstructor;
=======
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

import vn.care4u.entity.Doctor;
import vn.care4u.model.dto.AppointmentDTO;
import vn.care4u.model.dto.DoctorDTO;
import vn.care4u.repository.DoctorRepository;
import vn.care4u.service.DoctorService;

import java.util.List;

@Service
<<<<<<< Updated upstream
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
=======
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepo;

    @Override
    public <S extends Doctor> S save(S entity) {
        return doctorRepo.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return doctorRepo.existsById(id);
    }

    @Override
    public DoctorDTO mapToDTO(Doctor doctor) {
        if (doctor == null) return null;

        return DoctorDTO.builder()
                .id(doctor.getId())
                .firstname(doctor.getFirstname())
                .lastname(doctor.getLastname())
                .bio(doctor.getBio())
                .education(doctor.getEducation())        
                .certification(doctor.getCertification())
                .experience(doctor.getExperience())
                .workinghour(doctor.getWorkinghour())
                .patients(0)  
                .rating(0.0)   
                .build();
    }

    @Override
    public List<DoctorDTO> mapToDTOList(List<Doctor> doctors) {
        return doctors.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
>>>>>>> Stashed changes
}
