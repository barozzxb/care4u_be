package vn.care4u.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import vn.care4u.entity.Doctor;
import vn.care4u.entity.Department;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByDepartment(Department department);

    Optional<Doctor> findByAccountEmail(String email);

    @Query("select d.id from Doctor d where d.account.email = :email")
    Optional<Long> findIdByAccountEmail(String email);

}
