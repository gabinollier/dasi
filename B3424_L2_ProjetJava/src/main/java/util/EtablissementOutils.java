/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import static console.Instructif.printlnConsoleIHM;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import metier.modele.Etablissement;

/**
 *
 * @author echaffraix
 */
public class EtablissementOutils {
    
    public static Etablissement getEtablissement(String codeUAI) {
        JsonObject infoEtablissement = null;
        Etablissement etablissement = new Etablissement(codeUAI);
        
        try {
            printlnConsoleIHM("Début API");

            HttpClient httpClient = HttpClient.newHttpClient();
            URI requestUri = URI.create(
                "https://data.education.gouv.fr/api/explore/v2.1/catalog/datasets/fr-en-adresse-et-geolocalisation-etablissements-premier-et-second-degre/records"
                + "?refine=numero_uai:" + URLEncoder.encode(codeUAI, StandardCharsets.UTF_8)
            );

            HttpRequest httpRequest = HttpRequest.newBuilder(requestUri).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() == 200) {
                infoEtablissement = Json.createReader(new StringReader(httpResponse.body())).readObject();
            } else {
                throw new IOException("HTTP Error Status Code " + httpResponse.statusCode());
            }

            if (infoEtablissement != null) {
                JsonArray results = infoEtablissement.getJsonArray("results");
                if (results != null && !results.isEmpty()) {
                    JsonObject premierResult = results.getJsonObject(0);

                    etablissement.setNom(
                        premierResult.getString("appellation_officielle", "Nom inconnu")
                    );

                    if (premierResult.containsKey("latitude") && premierResult.containsKey("longitude")) {
                        double latitude = premierResult.getJsonNumber("latitude").doubleValue();
                        double longitude = premierResult.getJsonNumber("longitude").doubleValue();

                        etablissement.setLatitude((float) latitude);
                        etablissement.setLongitude((float) longitude);
                    }

                    etablissement.setAcademie(
                        premierResult.getString("libelle_academie", "Inconnue")
                    );
                }
            }

            URI ipsUri = URI.create(
                "https://data.education.gouv.fr/api/explore/v2.1/catalog/datasets/fr-en-ips_colleges/records"
                + "?refine=uai:" + URLEncoder.encode(codeUAI, StandardCharsets.UTF_8)
            );

            HttpRequest ipsRequest = HttpRequest.newBuilder(ipsUri).GET().build();
            HttpResponse<String> ipsResponse = httpClient.send(ipsRequest, HttpResponse.BodyHandlers.ofString());

            if (ipsResponse.statusCode() == 200) {
                JsonObject ipsJson = Json.createReader(new StringReader(ipsResponse.body())).readObject();
                JsonArray resultsIps = ipsJson.getJsonArray("results");

                if (resultsIps != null && !resultsIps.isEmpty()) {
                    JsonObject ipsResult = resultsIps.getJsonObject(0);

                    if (ipsResult.containsKey("ips")) {
                        double ipsValue = ipsResult.getJsonNumber("ips").doubleValue();
                        etablissement.setIPS(String.valueOf(ipsValue));
                    } else {
                        etablissement.setIPS(null);
                    }
                } else {
                    etablissement.setIPS(null);
                }
            } else {
                etablissement.setIPS(null);
            }

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }

        return etablissement;
    }
}
