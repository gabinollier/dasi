/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author echaffraix
 */
@Entity
@Table (name = "ELEVE")
public class Eleve {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private int niveauScolaire;
    private LocalDate dateNaissance;
    @Column(unique = true)
    private String email;
    private String mdp;
    @ManyToOne
    private Etablissement etablissement;

    public Eleve() {
    }

    public Eleve(String nom, String prenom, int niveauScolaire, LocalDate dateNaissance, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.niveauScolaire = niveauScolaire;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.mdp = mdp;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public int getNiveauScolaire() {
        return niveauScolaire;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public String getMdp() {
        return mdp;
    }
    
    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @Override
    public String toString() {
        return "Eleve{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
}
