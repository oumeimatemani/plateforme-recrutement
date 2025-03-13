package tn.esprit.spring.project.service;

import java.util.List;

import tn.esprit.spring.project.models.Historique;

public interface IHistoriqueService {
    Historique ajouterHistorique(Historique historique);
    List<Historique> getHistorique();
}
