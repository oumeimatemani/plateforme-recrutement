package tn.esprit.spring.project.service;

import tn.esprit.spring.project.models.OffreEmploi;
import java.util.List;

public interface IOffreEmploiService {

    OffreEmploi ajouterOffre(OffreEmploi offre);

    List<OffreEmploi> getAllOffres();

    OffreEmploi getOffreById(Long id);

    OffreEmploi modifierOffre(Long id, OffreEmploi nouvelleOffre);

    void supprimerOffre(Long id);

    List<OffreEmploi> rechercherParTitre(String titre);

    List<OffreEmploi> rechercherParLocalisation(String localisation);
}
