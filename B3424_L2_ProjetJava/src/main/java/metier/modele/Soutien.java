/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author echaffraix
 */
@Entity
public class Soutien {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Eleve eleve;
    @ManyToOne
    private Intervenant intervenant;
    @ManyToOne
    private Matiere matiere;
    @ManyToOne
    private Theme theme;
    private String lien;
    private String description;
    private String statut;
    private String bilan;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private LocalDate date;

    public Soutien() {
    }

    public Soutien(Eleve eleve, Matiere matiere, Theme theme, String description) {
        this.eleve = eleve;
        this.matiere = matiere;
        this.theme = theme;
        this.description = description;
        this.statut = "STARTED";
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public String getBilan() {
        return bilan;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }
    
    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public Theme getTheme() {
        return theme;
    }

    public String getLien() {
        return lien;
    }

    public String getDescription() {
        return description;
    }

    public String getStatut() {
        return statut;
    }
    
    @Override
    public String toString() {
        return "Soutien{" + "id=" + id + ", eleve=" + eleve + ", intervenant=" + intervenant + ", matiere=" + matiere + ", theme=" + theme + ", lien=" + lien + ", description=" + description + ", statut=" + statut + '}';
    }
}
