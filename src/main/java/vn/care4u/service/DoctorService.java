package vn.care4u.service;

import vn.care4u.entity.Doctor;
import vn.care4u.model.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {

    boolean existsById(Long id);

    <S extends Doctor> S save(S entity);

    DoctorDTO mapToDTO(Doctor doctor);

    List<DoctorDTO> mapToDTOList(List<Doctor> doctors);
}
