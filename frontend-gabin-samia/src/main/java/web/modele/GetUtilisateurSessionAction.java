/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author gollier
 */
public class GetUtilisateurSessionAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Object nom = request.getSession().getAttribute("nom");
        Object prenom = request.getSession().getAttribute("prenom");
        Object type = request.getSession().getAttribute("type");
        Object classe = request.getSession().getAttribute("classe");

        request.setAttribute("nom", nom);
        request.setAttribute("prenom", prenom);
        request.setAttribute("type", type);
        request.setAttribute("classe", classe);
    }

}
