package tn.esprit.spring.project.service;

import java.util.List;

import tn.esprit.spring.project.models.Candidature;
import tn.esprit.spring.project.models.EtatCandidature;

public interface ICandidatureService {
    Candidature createCandidature(Candidature candidature);
    Candidature getCandidatureById(Long id);
    Candidature updateEtat(Long id, EtatCandidature nouvelEtat);
    List<Candidature> getAllCandidatures();
    void deleteCandidature(Long id);
    Candidature updateCandidature(Long id, Candidature updatedCandidature);

}

