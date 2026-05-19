/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import static metier.service.ServiceCompte.authentificationEleve;
import static metier.service.ServiceCompte.authentificationIntervenant;

/**
 *
 * @author gollier
 */
public class AuthentifierAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        String identifiant = request.getParameter("identifiant");
        String motDePasse = request.getParameter("motdepasse");
        
        Eleve eleve = authentificationEleve(identifiant, motDePasse) ; 
        if (eleve == null){
            Intervenant intervenant = authentificationIntervenant(identifiant, motDePasse) ; 
            if (intervenant != null){
                request.setAttribute("intervenant", intervenant);
                request.getSession().setAttribute("identifiant", identifiant);
                request.getSession().setAttribute("motdepasse", motDePasse);
            }
        } else {
            request.setAttribute("eleve", eleve);
            request.getSession().setAttribute("identifiant", identifiant);
            request.getSession().setAttribute("motdepasse", motDePasse);
        }

    }
    
}
