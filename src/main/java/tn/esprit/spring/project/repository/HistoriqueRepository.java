package tn.esprit.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.project.models.Historique;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
}
