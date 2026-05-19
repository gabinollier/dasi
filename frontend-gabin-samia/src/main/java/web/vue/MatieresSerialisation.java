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
import java.util.List;
import metier.modele.Matiere;
import metier.modele.Theme;

/**
 *
 * @author gollier
 */
public class MatieresSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        
        List<Matiere> matieres = (List<Matiere>)request.getAttribute("matieres");
        
        var arrayBuilder = Json.createArrayBuilder();
        for(Matiere matiere : matieres)
        {
            JsonArrayBuilder themeArrayBuilder = Json.createArrayBuilder();

            for (Theme theme : matiere.getThemes())
            {
                JsonObjectBuilder themeObjectBuilder = Json.createObjectBuilder()
                        .add("id", theme.getId())
                        .add("nom", theme.getNom());
                
                themeArrayBuilder.add(themeObjectBuilder);
            }
            
            
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                    .add("id", matiere.getId())
                    .add("nom", matiere.getNom())                   
                    .add("themes", themeArrayBuilder);

            
            arrayBuilder.add(jsonObjectBuilder);
        }
        
        PrintWriter out = response.getWriter();
        out.println(arrayBuilder.build());
        out.close();
    }
    
}
