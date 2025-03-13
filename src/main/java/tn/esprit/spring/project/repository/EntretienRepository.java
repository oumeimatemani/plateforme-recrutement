package tn.esprit.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.project.models.Entretien;

public interface EntretienRepository extends JpaRepository<Entretien, Long> {
}
