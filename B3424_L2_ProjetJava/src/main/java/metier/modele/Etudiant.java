/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author echaffraix
 */
@Entity
public class Etudiant extends Intervenant {
    private String specialite;
    private String universite;

    public Etudiant() {
    }
    
    public Etudiant(String specialite, String universite, String nom, String prenom, int niveauScolaireMin, int niveauScolaireMax, String email, String mdp, String numeroTelephone) {
        super(nom, prenom, niveauScolaireMin, niveauScolaireMax, email, mdp, numeroTelephone);
        this.specialite = specialite;
        this.universite = universite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getUniversite() {
        return universite;
    }  
}
