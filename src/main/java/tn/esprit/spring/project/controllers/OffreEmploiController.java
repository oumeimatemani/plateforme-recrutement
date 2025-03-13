package tn.esprit.spring.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.project.models.OffreEmploi;
import tn.esprit.spring.project.service.OffreEmploiService;

@RestController
@RequestMapping("/offres")
public class OffreEmploiController {

    @Autowired
    private OffreEmploiService offreEmploiService;

    @GetMapping("/")
    public List<OffreEmploi> getAllOffres() {
        return offreEmploiService.getAllOffres();
    }
}
