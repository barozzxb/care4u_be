package vn.care4u.service;

import vn.care4u.entity.Patient;

public interface PatientService {

	boolean existsById(Long id);

	<S extends Patient> S save(S entity);

}
