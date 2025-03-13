package tn.esprit.spring.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.project.models.Candidature;
import tn.esprit.spring.project.models.EtatCandidature;
import tn.esprit.spring.project.service.CandidatureService;

@RestController
@RequestMapping("/candidature")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    // Liste de toutes les candidatures
    @GetMapping("/AllCandidatures")
    public ResponseEntity<List<Candidature>> getAllCandidatures() {
        return ResponseEntity.ok(candidatureService.getAllCandidatures());
    }

    // Création d'une candidature
    @PostMapping("/createCandidature")
    public ResponseEntity<Candidature> createCandidature(@RequestBody Candidature candidature) {
        return ResponseEntity.ok(candidatureService.createCandidature(candidature));
    }

    // Récupérer une candidature par ID
    @GetMapping("/{id}")
    public ResponseEntity<Candidature> getCandidatureById(@PathVariable Long id) {
        return ResponseEntity.ok(candidatureService.getCandidatureById(id));
    }

    // Mise à jour de l'état d'une candidature
    @PutMapping("/{id}/updateEtat")
    public ResponseEntity<Candidature> updateEtat(@PathVariable Long id, @RequestParam EtatCandidature nouvelEtat) {
        return ResponseEntity.ok(candidatureService.updateEtat(id, nouvelEtat));
    }

    // Mise à jour des informations d'une candidature
    @PutMapping("/{id}")
    public ResponseEntity<Candidature> updateCandidature(@PathVariable Long id, @RequestBody Candidature updatedCandidature) {
        return ResponseEntity.ok(candidatureService.updateCandidature(id, updatedCandidature));
    }

    // Suppression d'une candidature
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidature(@PathVariable Long id) {
        candidatureService.deleteCandidature(id);
        return ResponseEntity.ok("Candidature supprimée avec succès !");
    }


}
