package tn.esprit.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.project.models.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
 

}
