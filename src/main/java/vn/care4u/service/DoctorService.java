package vn.care4u.service;

import vn.care4u.entity.Doctor;

public interface DoctorService {

	boolean existsById(Long id);

	<S extends Doctor> S save(S entity);

}
