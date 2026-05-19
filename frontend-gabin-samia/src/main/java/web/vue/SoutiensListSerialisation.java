/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import metier.modele.Soutien;

/**
 *
 * @author gollier
 */
public class SoutiensListSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        
        List<Soutien> soutiens = (List<Soutien>)request.getAttribute("soutiens");
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        
        if (soutiens != null)
        {
            for (Soutien soutien : soutiens)
            {
                LocalDateTime dateDebut = LocalDateTime.of(soutien.getDate(), soutien.getHeureDebut());
                String dateDebutFormatee = dateDebut.format(DateTimeFormatter.ISO_DATE_TIME);
                
                JsonObjectBuilder objetBuilder = Json.createObjectBuilder()
                        .add("dateDebut", dateDebutFormatee)
                        .add("matiere", soutien.getMatiere().getNom())
                        .add("theme", soutien.getTheme().getNom())
                        .add("prenomIntervenant", soutien.getIntervenant().getPrenom())
                        .add("nomIntervenant", soutien.getIntervenant().getNom())
                        .add("statut", soutien.getStatut())
                        .add("bilan", soutien.getBilan() != null ? soutien.getBilan() : "")
                        .add("prenomEleve", soutien.getEleve().getPrenom())
                        .add("nomEleve", soutien.getEleve().getNom())
                        .add("classeEleve", soutien.getEleve().getNiveauScolaire());
                
                arrayBuilder.add(objetBuilder);
            }
        }
        
        PrintWriter out = response.getWriter();
        out.println(arrayBuilder.build());
        out.close();
    }
    
}
