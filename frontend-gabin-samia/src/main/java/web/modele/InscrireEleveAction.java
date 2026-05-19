/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import metier.modele.Eleve;
import metier.service.ServiceCompte;

/**
 *
 * @author gollier
 */
public class InscrireEleveAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        String prenom = request.getParameter("prenom") ; 
        String nom= request.getParameter("nom") ;
        String datedenaissance= request.getParameter("datedenaissance") ;
        Integer niveauscolaire= Integer.valueOf(request.getParameter("niveauscolaire")) ;
        String uai= request.getParameter("uai") ;
        String email= request.getParameter("email") ;
        String motdepasse= request.getParameter("motdepasse") ;

        
        Eleve eleve = new Eleve(nom, prenom, niveauscolaire, LocalDate.parse(datedenaissance, DateTimeFormatter.ISO_DATE), email, motdepasse); 
        ServiceCompte serviceCompte = new ServiceCompte() ; 
        Boolean reussite = serviceCompte.inscrireEleve(eleve, uai) ; 
        
        request.setAttribute("success", reussite) ; 
    }
}
