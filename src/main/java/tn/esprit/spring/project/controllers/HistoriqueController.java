package tn.esprit.spring.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.project.models.Historique;
import tn.esprit.spring.project.service.HistoriqueService;

@RestController
@RequestMapping("/historique")
public class HistoriqueController {

    @Autowired
    private HistoriqueService historiqueService;

    @GetMapping("/")
    public List<Historique> getHistorique() {
        return historiqueService.getHistorique();
    }
}
