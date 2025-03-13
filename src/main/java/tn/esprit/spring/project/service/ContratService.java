package tn.esprit.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.project.models.Contrat;
import tn.esprit.spring.project.repository.ContratRepository;

@Service
public class ContratService implements IContratService {

    @Autowired
    private ContratRepository contratRepository;

    @Override
    public Contrat genererContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat signerContrat(Long id) {
        Contrat contrat = contratRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contrat not found"));
    
        // Utilisation correcte de java.sql.Date
        contrat.setDateSignature(new java.sql.Date(System.currentTimeMillis()));
    
        return contratRepository.save(contrat);
    }
    

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }


}
