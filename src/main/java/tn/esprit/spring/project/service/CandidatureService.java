package tn.esprit.spring.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.project.models.Candidature;
import tn.esprit.spring.project.models.EtatCandidature;
import tn.esprit.spring.project.models.User;
import tn.esprit.spring.project.repository.CandidatureRepository;
import tn.esprit.spring.project.repository.UserRepository;

@Service
public class CandidatureService implements ICandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Candidature createCandidature(Candidature candidature) {
        User candidat = userRepository.findById(candidature.getCandidat().getId())
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        candidature.setCandidat(candidat);
        return candidatureRepository.save(candidature);
    }

    @Override
    public Candidature getCandidatureById(Long id) {
        return candidatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvée avec l'ID : " + id));
    }

    @Override
    public List<Candidature> getAllCandidatures() {
        return candidatureRepository.findAll();
    }

    @Override
    public Candidature updateEtat(Long id, EtatCandidature nouvelEtat) {
        Candidature candidature = getCandidatureById(id);
        candidature.setEtat(nouvelEtat);
        return candidatureRepository.save(candidature);
    }

    @Override
    public Candidature updateCandidature(Long id, Candidature updatedCandidature) {
        Candidature existingCandidature = getCandidatureById(id);

        existingCandidature.setDateSoumission(updatedCandidature.getDateSoumission());
        existingCandidature.setEtat(updatedCandidature.getEtat());

        // Vérifier si le candidat a changé et récupérer ses informations
        if (updatedCandidature.getCandidat() != null && updatedCandidature.getCandidat().getId() != null) {
            User candidat = userRepository.findById(updatedCandidature.getCandidat().getId())
                    .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));
            existingCandidature.setCandidat(candidat);
        }

        return candidatureRepository.save(existingCandidature);
    }

    @Override
    public void deleteCandidature(Long id) {
        Candidature candidature = getCandidatureById(id);
        candidatureRepository.delete(candidature);
    }
}
