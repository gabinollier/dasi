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

/**
 *
 * @author gollier
 */
public class UtilisateurSessionSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        Object nom = request.getAttribute("nom");
        Object prenom = request.getAttribute("prenom");
        Object type = request.getAttribute("type");
        Object classe = request.getAttribute("classe");

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        if (nom == null || prenom == null || type == null) {
            jsonObjectBuilder.add("connected", false);
        } else {
            jsonObjectBuilder
                    .add("connected", true)
                    .add("nom", nom.toString())
                    .add("prenom", prenom.toString())
                    .add("type", type.toString());

            if (classe != null) {
                if (classe instanceof Integer) {
                    jsonObjectBuilder.add("classe", (Integer) classe);
                } else {
                    jsonObjectBuilder.add("classe", classe.toString());
                }
            }
        }

        PrintWriter out = response.getWriter();
        out.println(jsonObjectBuilder.build());
        out.close();
    }

}
