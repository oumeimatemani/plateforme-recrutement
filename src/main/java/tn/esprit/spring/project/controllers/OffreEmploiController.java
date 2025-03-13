package tn.esprit.spring.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.project.models.OffreEmploi;
import tn.esprit.spring.project.service.IOffreEmploiService;

import java.util.List;

@RestController
@RequestMapping("/offres")
public class OffreEmploiController {

    @Autowired
    private IOffreEmploiService offreEmploiService;

    // Ajouter une nouvelle offre d'emploi
    @PostMapping("/add")
    public ResponseEntity<OffreEmploi> ajouterOffre(@RequestBody OffreEmploi offre) {
        return ResponseEntity.status(HttpStatus.CREATED).body(offreEmploiService.ajouterOffre(offre));
    }

    // Récupérer toutes les offres
    @GetMapping("/all")
    public ResponseEntity<List<OffreEmploi>> getAllOffres() {
        return ResponseEntity.ok(offreEmploiService.getAllOffres());
    }

    // Récupérer une offre par ID
    @GetMapping("/{id}")
    public ResponseEntity<OffreEmploi> getOffreById(@PathVariable Long id) {
        return ResponseEntity.ok(offreEmploiService.getOffreById(id));
    }

    // Modifier une offre d'emploi
    @PutMapping("/edit/{id}")
    public ResponseEntity<OffreEmploi> modifierOffre(@PathVariable Long id, @RequestBody OffreEmploi nouvelleOffre) {
        return ResponseEntity.ok(offreEmploiService.modifierOffre(id, nouvelleOffre));
    }

    // Supprimer une offre d'emploi
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> supprimerOffre(@PathVariable Long id) {
        offreEmploiService.supprimerOffre(id);
        return ResponseEntity.noContent().build();
    }

    // Rechercher des offres par titre
    @GetMapping("/search/titre")
    public ResponseEntity<List<OffreEmploi>> rechercherParTitre(@RequestParam String titre) {
        return ResponseEntity.ok(offreEmploiService.rechercherParTitre(titre));
    }

    // Rechercher des offres par localisation
    @GetMapping("/search/localisation")
    public ResponseEntity<List<OffreEmploi>> rechercherParLocalisation(@RequestParam String localisation) {
        return ResponseEntity.ok(offreEmploiService.rechercherParLocalisation(localisation));
    }
}
