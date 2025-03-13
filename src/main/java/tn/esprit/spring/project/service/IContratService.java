package tn.esprit.spring.project.service;

import java.util.List;
import tn.esprit.spring.project.models.Contrat;

public interface IContratService {
    Contrat genererContrat(Contrat contrat);
    Contrat signerContrat(Long id);
    List<Contrat> getAllContrats();
}
