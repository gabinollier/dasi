/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Soutien;
import metier.service.ServiceCompte;
import metier.service.ServiceSoutien;

/**
 *
 * @author gollier
 */
public class GetSoutienEnCoursIntervenantAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        String authentifiantUser = (String)request.getSession().getAttribute("identifiant");
        String motdepasseUser = (String)request.getSession().getAttribute("motdepasse");
        
        Intervenant intervenant = ServiceCompte.authentificationIntervenant(authentifiantUser, motdepasseUser);
        
        if (intervenant == null)
        {
            throw new Error("Intervenant pas connecté.");
        }
        
        Soutien soutien = ServiceSoutien.getSoutienEnCoursIntervenant(intervenant);

        if (soutien != null) {
            Eleve eleve = soutien.getEleve();
            if (eleve != null) {
                List<Soutien> soutiensEleve = ServiceSoutien.getSoutiensEleve(eleve);
                request.setAttribute("soutiensEleve", soutiensEleve);
            }
        }

        request.setAttribute("soutien", soutien);

    }
    
}
