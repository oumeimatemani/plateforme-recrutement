package tn.esprit.spring.project.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidature_id", nullable = false)
    private Candidature candidature;

    @Column(nullable = false)
    private LocalDateTime dateEntretien;  

    private String feedback;
    
    private Integer note; // Notation de l'entretien par exemple sur /20 

    public Entretien() {}

    public Entretien(Candidature candidature, LocalDateTime dateEntretien, String feedback, Integer note) {
        this.candidature = candidature;
        this.dateEntretien = dateEntretien;
        this.feedback = feedback;
        this.note = note;
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Candidature getCandidature() { return candidature; }
    public void setCandidature(Candidature candidature) { this.candidature = candidature; }

    public LocalDateTime getDateEntretien() { return dateEntretien; }
    public void setDateEntretien(LocalDateTime dateEntretien) { this.dateEntretien = dateEntretien; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public Integer getNote() { return note; }
    public void setNote(Integer note) { this.note = note; }
}
