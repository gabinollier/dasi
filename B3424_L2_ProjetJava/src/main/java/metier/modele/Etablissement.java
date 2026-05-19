/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 *
 * @author echaffraix
 */
@Entity
@Table (name = "Etablissement")
public class Etablissement {
    @Id
    private String codeUAI;
    private String nom;
    private String IPS;
    private float longitude;
    private float latitude;
    private String academie;
    private int nbSoutiens;
    private long dureeTotaleSoutiens;

    public Etablissement() {
    }

    public Etablissement(String codeUAI) {
        this.codeUAI = codeUAI;
        this.nom = null;
        this.nbSoutiens = 0;
    }

    public String getCodeUAI() {
        return codeUAI;
    }

    public String getNom() {
        return nom;
    }

    public String getIPS() {
        return IPS;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getAcademie() {
        return academie;
    }

    public int getNb_soutiens() {
        return nbSoutiens;
    }

    public int getNbSoutiens() {
        return nbSoutiens;
    }

    public long getDureeTotaleSoutiens() {
        return dureeTotaleSoutiens;
    }
   
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCodeUAI(String codeUAI) {
        this.codeUAI = codeUAI;
    }

    public void setIPS(String IPS) {
        this.IPS = IPS;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setAcademie(String academie) {
        this.academie = academie;
    }
    
    @Override
    public String toString() {
        return "Etablissement{" + "codeUAI=" + codeUAI + ", nom=" + nom + '}';
    }
    
    public void ajouterSoutien() {
        this.nbSoutiens++;
    }
    
    public void augmenterDureeTotale(long nouvelleDuree) {
        this.dureeTotaleSoutiens = this.dureeTotaleSoutiens + nouvelleDuree;
    }
    
    public double getMoyenneDuree() {
        double moyenne = 0;
        if (nbSoutiens != 0) {
            moyenne = (double) dureeTotaleSoutiens / nbSoutiens;
        }
        return moyenne;
    }
}
