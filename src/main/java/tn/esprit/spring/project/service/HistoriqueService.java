package tn.esprit.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.project.models.Historique;
import tn.esprit.spring.project.repository.HistoriqueRepository;

@Service
public class HistoriqueService implements IHistoriqueService {

    @Autowired
    private HistoriqueRepository historiqueRepository;

    @Override
    public Historique ajouterHistorique(Historique historique) {
        return historiqueRepository.save(historique);
    }

    @Override
    public List<Historique> getHistorique() {
        return historiqueRepository.findAll();
    }
}
