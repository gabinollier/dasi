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
public class Professeur extends Intervenant {
    private String typeEtablissement;

    public Professeur() {
    }
    
    public Professeur(String typeEtablissement, String nom, String prenom, int niveauScolaireMin, int niveauScolaireMax, String email, String mdp, String numeroTelephone) {
        super(nom, prenom, niveauScolaireMin, niveauScolaireMax, email, mdp, numeroTelephone);
        this.typeEtablissement = typeEtablissement;
    }

    public String getTypeEtablissement() {
        return typeEtablissement;
    }
}
