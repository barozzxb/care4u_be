package vn.care4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.care4u.entity.Drug;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}
