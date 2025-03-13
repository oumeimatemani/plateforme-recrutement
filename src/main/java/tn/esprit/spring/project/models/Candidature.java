package tn.esprit.spring.project.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "candidatures")
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dateSoumission;

    @Enumerated(EnumType.STRING)
    private EtatCandidature etat;


    @ManyToOne(fetch = FetchType.EAGER) // Charger automatiquement les infos du candidat
    @JoinColumn(name = "candidat_id", nullable = true) // Autoriser NULL //Cela permet de conserver la candidature dans la base de données même si l'utilisateur est supprimé
    private User candidat;

    
    public void mettreAJourEtat(EtatCandidature nouvelEtat) {
        this.etat = nouvelEtat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(Date dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public EtatCandidature getEtat() {
        return etat;
    }

    public void setEtat(EtatCandidature etat) {
        this.etat = etat;
    }

    public User getCandidat() {
        return candidat;
    }

    public void setCandidat(User candidat) {
        this.candidat = candidat;
    }

  
    
}
