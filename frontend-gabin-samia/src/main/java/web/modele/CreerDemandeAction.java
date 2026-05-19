/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import metier.modele.Eleve;
import metier.modele.Matiere;
import metier.modele.Soutien;
import metier.modele.Theme;
import metier.service.ServiceCompte;
import metier.service.ServiceSoutien;

/**
 *
 * @author gollier
 */
public class CreerDemandeAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        String authentifiantUser = (String)request.getSession().getAttribute("identifiant");
        String motdepasseUser = (String)request.getSession().getAttribute("motdepasse");
        
        Eleve eleve = ServiceCompte.authentificationEleve(authentifiantUser, motdepasseUser);
        
        if (eleve == null) 
        {
            throw new Error("Eleve pas connecté");
        }
        
        String themeName = request.getParameter("themeName");
        Theme theme = ServiceSoutien.getThemeByName(themeName);

        Matiere matiere = theme.getMatiere();
        
        String description = request.getParameter("description") ;
        
        Soutien soutien = ServiceSoutien.creerDemande(eleve, matiere, theme, description);

        if (soutien != null)
        {
            request.setAttribute("soutien", soutien); 
        }
    }
}
