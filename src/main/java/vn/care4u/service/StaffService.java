package vn.care4u.service;

import vn.care4u.entity.Staff;

public interface StaffService {

	void deleteById(Long id);

	boolean existsById(Long id);

	<S extends Staff> S save(S entity);

}
