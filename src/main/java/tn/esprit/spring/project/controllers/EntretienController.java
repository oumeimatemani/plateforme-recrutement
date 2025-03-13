package tn.esprit.spring.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.project.models.Entretien;
import tn.esprit.spring.project.service.IEntretienService;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/entretiens")
public class EntretienController {

    @Autowired
    private IEntretienService entretienService;

    // Planifier un entretien
    @PostMapping("/planifier/{candidatureId}")
    public ResponseEntity<?> planifierEntretien(
        @PathVariable Long candidatureId,
        @RequestParam("dateEntretien") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateEntretien) {
        
        // Log pour voir si la date est bien reçue
        System.out.println("Date reçue: " + dateEntretien);
    
        Entretien entretien = entretienService.planifierEntretien(candidatureId, dateEntretien);
        return ResponseEntity.status(HttpStatus.CREATED).body(entretien);
    }

    // Noter un entretien
    @PutMapping("/noter/{entretienId}")
    public ResponseEntity<Entretien> noterEntretien(
        @PathVariable Long entretienId, 
        @RequestBody Map<String, Object> payload) {
        
        Integer note = (Integer) payload.get("note");
        String feedback = (String) payload.get("feedback");
    
        return ResponseEntity.ok(entretienService.noterEntretien(entretienId, note, feedback));
    }
    

    // Liste des entretiens
    @GetMapping("/all")
    public ResponseEntity<List<Entretien>> getAllEntretiens() {
        return ResponseEntity.ok(entretienService.getAllEntretiens());
    }

    // Récupérer un entretien par ID
    @GetMapping("/{id}")
    public ResponseEntity<Entretien> getEntretienById(@PathVariable Long id) {
        return ResponseEntity.ok(entretienService.getEntretienById(id));
    }
}
