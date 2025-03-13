package tn.esprit.spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.project.models.Entretien;
import tn.esprit.spring.project.models.Candidature;
import tn.esprit.spring.project.repository.EntretienRepository;
import tn.esprit.spring.project.repository.CandidatureRepository;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class EntretienService implements IEntretienService {

    @Autowired
    private EntretienRepository entretienRepository;

    @Autowired
    private CandidatureRepository candidatureRepository;

    // Planifier un entretien
    @Override
    public Entretien planifierEntretien(Long candidatureId, LocalDateTime dateEntretien) {
        Candidature candidature = candidatureRepository.findById(candidatureId)
            .orElseThrow(() -> new RuntimeException("Candidature not found"));

        Entretien entretien = new Entretien();
        entretien.setCandidature(candidature);
        entretien.setDateEntretien(dateEntretien);

        return entretienRepository.save(entretien);
    }


    // Noter un entretien
    @Override
    public Entretien noterEntretien(Long entretienId, Integer note, String feedback) {
        Entretien entretien = entretienRepository.findById(entretienId)
                .orElseThrow(() -> new RuntimeException("Entretien non trouvé !"));

        entretien.setNote(note);
        entretien.setFeedback(feedback);

        return entretienRepository.save(entretien);
    }

    // Récupérer tous les entretiens
    @Override
    public List<Entretien> getAllEntretiens() {
        return entretienRepository.findAll();
    }

    // Récupérer un entretien par ID
    @Override
    public Entretien getEntretienById(Long id) {
        return entretienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entretien non trouvé !"));
    }
}
