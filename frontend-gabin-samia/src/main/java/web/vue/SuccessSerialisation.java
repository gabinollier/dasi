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
public class SuccessSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        
        Boolean success = (Boolean) request.getAttribute("success");
        
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder(); 
        
        jsonObjectBuilder.add("success", success) ; 
       
        PrintWriter out = response.getWriter();
        out.println(jsonObjectBuilder.build());
        out.close();
    }
    
}
