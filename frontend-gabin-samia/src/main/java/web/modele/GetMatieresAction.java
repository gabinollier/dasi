/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import metier.modele.Matiere;
import metier.service.ServiceSoutien;

/**
 *
 * @author gollier
 */
public class GetMatieresAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        
        List<Matiere> matieres = ServiceSoutien.getMatieres();
        
        request.setAttribute("matieres", matieres);
    }
    
}
