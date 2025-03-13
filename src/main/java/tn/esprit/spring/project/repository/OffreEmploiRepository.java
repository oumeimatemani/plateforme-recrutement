package tn.esprit.spring.project.repository;

import tn.esprit.spring.project.models.OffreEmploi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OffreEmploiRepository extends JpaRepository<OffreEmploi, Long> {
    List<OffreEmploi> findByTitreContainingIgnoreCase(String titre);
    List<OffreEmploi> findByLocalisationContainingIgnoreCase(String localisation);
}
