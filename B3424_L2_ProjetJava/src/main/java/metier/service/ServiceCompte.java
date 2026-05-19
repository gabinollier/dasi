/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.service;

import dao.EleveDao;
import dao.EtablissementDao;
import dao.IntervenantDao;
import dao.JpaUtil;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.modele.Intervenant;
import util.EtablissementOutils;
import util.Message;

/**
 *
 * @author echaffraix
 */
public class ServiceCompte {

    public ServiceCompte() {
    }
    
    public Boolean inscrireEleve(Eleve eleve, String codeUAI)
    {
        Boolean creationSucces = false;
        EleveDao eleveDao = new EleveDao();
        EtablissementDao etablissementDao = new EtablissementDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            Etablissement etablissement = etablissementDao.findById(codeUAI);
            if (etablissement == null) {
                etablissement = EtablissementOutils.getEtablissement(codeUAI);
                etablissementDao.create(etablissement);// inscire etablissement
            }
 
            eleve.setEtablissement(etablissement);
            eleveDao.create(eleve);
            JpaUtil.validerTransaction();
            Message.envoyerMail(
                    "contact@instruct.if", 
                    eleve.getEmail(), 
                    "Bienvenue sur le réseau INSTRUCT'IF", 
                    String.format("Bonjour %s, nous te confirmons ton inscription sur le réseau INSTRUCT'IF. Si tu as besoin d'un soutien pour tes leçons ou tes devoirs, rends-toi sur notre site pour uns mise en relation avec un intervenant.",
                    eleve.getPrenom()));
                    
            creationSucces = true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            Message.envoyerMail(
                    "contact@instruct.if", 
                    eleve.getEmail(), 
                    "Echec de l'inscription sur le réseau INSTRUCT'IF", 
                    String.format("Bonjour %s, ton inscription sur le réseau INSTRUCUT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.",
                    eleve.getPrenom()));
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return creationSucces;
    }
    
    public static Eleve authentificationEleve(String mail, String mdp){
        JpaUtil.creerContextePersistance();
        try {
            EleveDao eleveDao = new EleveDao();
            Eleve e1 = eleveDao.findByLogin(mail, mdp);
            if (e1 == null){
                System.out.println("Erreur de connexion");
            }
            return e1;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public static Intervenant authentificationIntervenant(String mail, String mdp){
        JpaUtil.creerContextePersistance();
        try {
            IntervenantDao intervenantDao = new IntervenantDao();
            Intervenant i1 = intervenantDao.findByLogin(mail, mdp);
            if (i1 == null){
                System.out.println("Erreur de connexion");
            }
            return i1;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
}
