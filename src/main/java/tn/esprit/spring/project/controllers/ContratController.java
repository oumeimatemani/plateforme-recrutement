package tn.esprit.spring.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.project.models.Contrat;
import tn.esprit.spring.project.service.ContratService;

@RestController
@RequestMapping("/contrats")
public class ContratController {

    @Autowired
    private ContratService contratService;

    @PostMapping("/create")
    public ResponseEntity<Contrat> genererContrat(@RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.genererContrat(contrat));
    }

    @PutMapping("/{id}/signer")
    public ResponseEntity<Contrat> signerContrat(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.signerContrat(id));
    }
}
