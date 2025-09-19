package vn.care4u.service;

import vn.care4u.entity.Admin;

public interface AdminService {

	boolean existsById(Long id);

	<S extends Admin> S save(S entity);

}
