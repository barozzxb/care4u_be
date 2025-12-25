package vn.care4u.service;

import org.springframework.web.multipart.MultipartFile;
import vn.care4u.entity.Patient;
import vn.care4u.model.dto.PatientUpdateDTO;

public interface PatientService {

    boolean existsById(Long id);

    <S extends Patient> S save(S entity);

    Patient updatePatientInfo(String accountEmail, PatientUpdateDTO dto);

    Patient updatePatientInfoWithAvatar(
            String accountEmail,
            PatientUpdateDTO dto,
            MultipartFile avatar
    );

    Patient getPatientById(String accountEmail);
}
