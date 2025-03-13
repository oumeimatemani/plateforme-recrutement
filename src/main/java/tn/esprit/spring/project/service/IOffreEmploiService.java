package tn.esprit.spring.project.service;

import java.util.List;

import tn.esprit.spring.project.models.OffreEmploi;

public interface IOffreEmploiService {
    OffreEmploi createOffre(OffreEmploi offreEmploi);
    List<OffreEmploi> getAllOffres();
    OffreEmploi getOffreById(Long id);
    void deleteOffre(Long id);
}
