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
public class DeconnecterAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        if (request.getSession(false) != null) {
            request.getSession().removeAttribute("identifiant");
            request.getSession().removeAttribute("motdepasse");
            request.getSession().removeAttribute("nom");
            request.getSession().removeAttribute("prenom");
            request.getSession().removeAttribute("type");
            request.getSession().removeAttribute("classe");
        }

        request.setAttribute("success", true);
    }

}
