package sbcallmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbcallmanagement.entity.Advisor;


@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
    
}
