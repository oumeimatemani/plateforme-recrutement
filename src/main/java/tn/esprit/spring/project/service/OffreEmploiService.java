package tn.esprit.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.project.models.OffreEmploi;
import tn.esprit.spring.project.repository.OffreEmploiRepository;

@Service
public class OffreEmploiService implements IOffreEmploiService {

   @Autowired
    private OffreEmploiRepository OffreEmploiRepository;

    public OffreEmploi ajouterOffre(OffreEmploi offre) {
        return OffreEmploiRepository.save(offre);
    }

    public List<OffreEmploi> getAllOffres() {
        return OffreEmploiRepository.findAll();
    }

    public OffreEmploi getOffreById(Long id) {
        return OffreEmploiRepository.findById(id).orElseThrow(() -> new RuntimeException("Offre non trouvée"));
    }

    public OffreEmploi modifierOffre(Long id, OffreEmploi nouvelleOffre) {
        OffreEmploi offreExistante = getOffreById(id);
        offreExistante.setTitre(nouvelleOffre.getTitre());
        offreExistante.setDescription(nouvelleOffre.getDescription());
        offreExistante.setEntreprise(nouvelleOffre.getEntreprise());
        offreExistante.setLocalisation(nouvelleOffre.getLocalisation());
        offreExistante.setSalaire(nouvelleOffre.getSalaire());
        offreExistante.setTypeContrat(nouvelleOffre.getTypeContrat());
        offreExistante.setCompetences(nouvelleOffre.getCompetences());
        offreExistante.setDateExpiration(nouvelleOffre.getDateExpiration());

        return OffreEmploiRepository.save(offreExistante);
    }

    public void supprimerOffre(Long id) {
        OffreEmploiRepository.deleteById(id);
    }

    public List<OffreEmploi> rechercherParTitre(String titre) {
        return OffreEmploiRepository.findByTitreContainingIgnoreCase(titre);
    }

    public List<OffreEmploi> rechercherParLocalisation(String localisation) {
        return OffreEmploiRepository.findByLocalisationContainingIgnoreCase(localisation);
    }
}