package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.care4u.entity.Doctor;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    Optional<Doctor> findByAccountEmail(String email);

    @Query("select d.id from Doctor d where d.account.email = :email")
    Optional<Long> findIdByAccountEmail(String email);

}
