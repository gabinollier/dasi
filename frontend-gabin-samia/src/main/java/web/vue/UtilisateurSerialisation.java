/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import web.test.DemandeTest;

/**
 *
 * @author gollier
 */
public class UtilisateurSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        
        Eleve eleve = (Eleve) request.getAttribute("eleve");
        Intervenant intervenant = (Intervenant) request.getAttribute("intervenant");
        
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder(); 
        
        if (eleve == null && intervenant == null){
            jsonObjectBuilder.add("type", "null") ; 
        } else if (eleve != null){
            jsonObjectBuilder.add("type", "eleve") 
                .add("id", eleve.getId())
                .add("nom", eleve.getNom())
                .add("prenom", eleve.getPrenom())
                .add("niveauScolaire", eleve.getNiveauScolaire())
                .add("email", eleve.getEmail())
                .add("mdp", eleve.getMdp())
                .add("dateNaissance", eleve.getDateNaissance().format(DateTimeFormatter.ISO_DATE));
                //TODO : ajouter etablissement 
        } else {
            jsonObjectBuilder.add("type", "intervenant") 
                .add("id", intervenant.getId())
                .add("nom", intervenant.getNom())
                .add("prenom", intervenant.getPrenom())
                .add("niveauScolaireMin", intervenant.getNiveauScolaireMin())
                .add("niveauScolaireMax", intervenant.getNiveauScolaireMax())
                .add("email", intervenant.getEmail())
                .add("mdp", intervenant.getMdp())
                .add("numeroTelephone", intervenant.getNumeroTelephone())
                .add("nbSoutien", intervenant.getNbSoutien())
                .add("libre", intervenant.getLibre());
        }
       
        PrintWriter out = response.getWriter();
        out.println(jsonObjectBuilder.build());
        out.close();
    }
    
}
