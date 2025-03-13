package tn.esprit.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.project.models.OffreEmploi;

@Repository
public interface OffreEmploiRepository extends JpaRepository<OffreEmploi, Long> {
}
