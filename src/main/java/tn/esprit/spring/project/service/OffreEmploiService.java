package tn.esprit.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.project.models.OffreEmploi;
import tn.esprit.spring.project.repository.OffreEmploiRepository;

@Service
public class OffreEmploiService implements IOffreEmploiService {

    @Autowired
    private OffreEmploiRepository offreEmploiRepository;

    @Override
    public OffreEmploi createOffre(OffreEmploi offreEmploi) {
        return offreEmploiRepository.save(offreEmploi);
    }

    @Override
    public List<OffreEmploi> getAllOffres() {
        return offreEmploiRepository.findAll();
    }

    @Override
    public OffreEmploi getOffreById(Long id) {
        return offreEmploiRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Offre non trouvée"));
    }

    @Override
    public void deleteOffre(Long id) {
        offreEmploiRepository.deleteById(id);
    }
}
