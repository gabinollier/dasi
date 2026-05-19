/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web.controleur;

import dao.JpaUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.modele.AuthentifierAction;
import web.modele.CreerDemandeAction;
import web.modele.FinirSoutienAction;
import web.modele.GetMatieresAction;
import web.modele.GetSoutienEnCoursIntervenantAction;
import web.modele.InscrireEleveAction;
import web.vue.UtilisateurSerialisation;
import web.vue.MatieresSerialisation;
import web.vue.SoutienSerialisation;
import web.vue.SuccessSerialisation;

/**
 *
 * @author gollier
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* TODO output your page here. You may use following sample code. */
        /*out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ActionServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
        out.println("</body>");
        out.println("</html>");*/

        String todo = request.getParameter("todo");
        System.out.println("Trace : todo = " + todo);

        switch(todo)
        {
            case "get-matieres" : {
                new GetMatieresAction().execute(request);
                new MatieresSerialisation().appliquer(request, response);
                break;
            }
            case "authentifier" : {
                new AuthentifierAction().execute(request);
                new UtilisateurSerialisation().appliquer(request, response);
                break;
            }
            case "inscrire-eleve" : {
                new InscrireEleveAction().execute(request);
                new SuccessSerialisation().appliquer(request, response);
                break;
            }
            case "creer-demande" : {
                new CreerDemandeAction().execute(request);
                new SoutienSerialisation().appliquer(request, response);
                break;
            }
            case "get-soutien-en-cours-intervenant" : {
                new GetSoutienEnCoursIntervenantAction().execute(request);
                new SoutienSerialisation().appliquer(request, response);
                break;
            }
            case "finir-soutien" : {
                new FinirSoutienAction().execute(request);
                new SuccessSerialisation().appliquer(request, response);
                break;
            }
            default : {
                System.out.print("ATTENTION : todo = " + todo + ". N'existe pas !\n");
                break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        JpaUtil.fermerFabriquePersistance();
    }
}
