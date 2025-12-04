package vn.care4u.service;

import vn.care4u.entity.Patient;
import vn.care4u.model.dto.PatientUpdateDTO;

public interface PatientService {

    boolean existsById(Long id);

    <S extends Patient> S save(S entity);

    Patient updatePatientInfo(String accountEmail, PatientUpdateDTO dto);

    Patient getPatientById(String accountEmail);
}
