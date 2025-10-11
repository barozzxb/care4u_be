package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.care4u.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

}
