/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import metier.modele.Autre;
import metier.modele.Eleve;
import metier.modele.Etudiant;
import metier.modele.Intervenant;
import metier.modele.Professeur;
import metier.modele.Soutien;

/**
 *
 * @author gollier
 */
public class SoutienSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        
        Soutien soutien = (Soutien)request.getAttribute("soutien");
        
        
        JsonObjectBuilder objetBuilder = Json.createObjectBuilder();
        
        if (soutien != null)
        {
            objetBuilder.add("exists", true)
                .add("debut", soutien.getHeureDebut().format(DateTimeFormatter.ISO_TIME))
                .add("matiere", soutien.getMatiere().getNom())
                .add("theme", soutien.getTheme().getNom())                
                .add("lienVisio", soutien.getLien())
                .add("prenomIntervenant", soutien.getIntervenant().getPrenom())
                .add("nomIntervenant", soutien.getIntervenant().getNom())
                .add("description", soutien.getDescription())
                .add("nomEleve", soutien.getEleve().getPrenom())
                .add("prenomEleve", soutien.getEleve().getNom());

            Eleve eleve = soutien.getEleve();
            if (eleve != null) {
                JsonObjectBuilder eleveBuilder = Json.createObjectBuilder()
                        .add("id", eleve.getId())
                        .add("nom", eleve.getNom())
                        .add("prenom", eleve.getPrenom())
                        .add("niveauScolaire", eleve.getNiveauScolaire())
                        .add("email", eleve.getEmail());

                if (eleve.getDateNaissance() != null) {
                    eleveBuilder.add("dateNaissance", eleve.getDateNaissance().format(DateTimeFormatter.ISO_DATE));
                }

                objetBuilder.add("eleve", eleveBuilder);
            }
            
            Intervenant intervenant = soutien.getIntervenant();
            if (intervenant instanceof Professeur)
            {
                objetBuilder.add("typeIntervenant", "Professeur")
                    .add("typeEtablissement",((Professeur) intervenant).getTypeEtablissement());
            }
            else if (intervenant instanceof Etudiant)
            {
                objetBuilder.add("typeIntervenant", "Etudiant")
                    .add("specialite",((Etudiant) intervenant).getSpecialite())
                    .add("universite",((Etudiant) intervenant).getUniversite());
            } else if (intervenant instanceof Autre)
            {
                objetBuilder.add("typeIntervenant", "Autre")
                        .add("activite", ((Autre)intervenant).getActivite());
            }
        }
        else 
        {
            objetBuilder.add("exists", false);
        }
        
        PrintWriter out = response.getWriter();
        out.println(objetBuilder.build());
        out.close();
    }
    
}
