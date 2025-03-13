package tn.esprit.spring.project.service;

import tn.esprit.spring.project.models.Entretien;

import java.util.List;
import java.time.LocalDateTime;

public interface IEntretienService {
    Entretien planifierEntretien(Long candidatureId, LocalDateTime dateEntretien);
    Entretien noterEntretien(Long entretienId, Integer note, String feedback);
    List<Entretien> getAllEntretiens();
    Entretien getEntretienById(Long id);
}
