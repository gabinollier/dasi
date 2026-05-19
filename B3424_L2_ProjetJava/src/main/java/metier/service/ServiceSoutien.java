/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.service;

import static console.Instructif.printlnConsoleIHM;
import dao.EtablissementDao;
import dao.IntervenantDao;
import dao.JpaUtil;
import dao.MatiereDao;
import dao.SoutienDao;
import dao.ThemeDao;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Soutien;
import metier.modele.Theme;
import util.Message;

/**
 *
 * @author echaffraix
 */
public class ServiceSoutien {
    public static Soutien creerDemande(Eleve eleve, Matiere matiere, Theme theme, String description){
        Soutien soutien = null;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            int niveauEleve = eleve.getNiveauScolaire();
            SoutienDao soutienDao = new SoutienDao();
            soutien = new Soutien(eleve, matiere,theme,description);
            soutienDao.create(soutien);
            
            IntervenantDao intervenantDao = new IntervenantDao();
            Intervenant intervenant = intervenantDao.findIntervenantLibre(niveauEleve);
            
            if(intervenant == null) {
                printlnConsoleIHM("Aucun Intervenant Disponible");
                soutien.setStatut("CANCELLED");
                soutienDao.merge(soutien);
            }
            else {
                soutien.setIntervenant(intervenant);
                intervenant.ajouterSoutien();
                intervenant.setLibre(false);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
                soutien.setHeureDebut(LocalTime.now());
                String time = LocalTime.now().format(dtf); // récupère l'heure actuelle et formate
                LocalDate dateAjd = LocalDate.now();
                soutien.setDate(dateAjd);

                String message = String.format(
                        "Bonjour %s. Merci de prendre en charge la demande de soutien en %s demandée à %s par %s en classe de %sème.",
                        intervenant.getPrenom(),
                        matiere.getNom(),
                        time,
                        eleve.getPrenom(),
                        niveauEleve
                );
                Message.envoyerNotification(intervenant.getNumeroTelephone(), message);

                String lien = String.format(
                        "https://servif.insa-lyon.fr/InteractIF/visio.html?eleve=%s&intervenant=%s%s",
                        eleve.getEmail(),
                        intervenant.getPrenom().toLowerCase().charAt(0),
                        intervenant.getNom().toLowerCase()
                );
                soutien.setLien(lien);
                intervenantDao.merge(intervenant);
                soutienDao.merge(soutien);
            }
            JpaUtil.validerTransaction();
        } catch (Exception ex){
            ex.printStackTrace();  
            JpaUtil.annulerTransaction();  
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return soutien;
    }
    
    public static Theme getThemeByName(String nom)
    {
        Theme theme;
        try 
        {
            JpaUtil.creerContextePersistance();
            
            ThemeDao themeDao = new ThemeDao();
            theme = themeDao.findByName(nom);
            
        }
        catch (Exception ex) {
            theme = null;
            ex.printStackTrace();  
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return theme;
    }
    
    public static void finirSoutien(Soutien soutien, String corps) {
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            SoutienDao soutienDao = new SoutienDao();
            soutien.setBilan(corps);
            soutien.setHeureFin(LocalTime.now());
            soutien.setStatut("FINISHED");
            
            IntervenantDao intervenantDao = new IntervenantDao();
            Intervenant intervenant = soutien.getIntervenant();
            intervenant.setLibre(true);
            
            Eleve eleve = soutien.getEleve();
            EtablissementDao etablissementDao = new EtablissementDao();
            Etablissement etablissement = eleve.getEtablissement();
            etablissement.ajouterSoutien();
            
            Duration duree = Duration.between(
                soutien.getHeureDebut(),
                soutien.getHeureFin()
            );

            long minutes = duree.toMinutes();
            etablissement.augmenterDureeTotale(minutes);
            
            etablissementDao.merge(etablissement);
            intervenantDao.merge(intervenant);
            soutienDao.merge(soutien);
            JpaUtil.validerTransaction();
            
            Message.envoyerMail(
                    intervenant.getEmail(), 
                    eleve.getEmail(), 
                    String.format("Bilan de ton soutien avec %s", intervenant.getPrenom()),
                    corps
            );
        }
        catch (Exception ex) {
            ex.printStackTrace();  
            JpaUtil.annulerTransaction();  
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public static List<Matiere> getMatieres() {
        List<Matiere> listeMatieres = null;
        try {
            JpaUtil.creerContextePersistance();
            MatiereDao matiereDao = new MatiereDao();
            listeMatieres = matiereDao.getMatieresDAO();
        } catch (Exception ex) {
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeMatieres;
    }
    
    public static List<Soutien> getSoutiensEleve(Eleve eleve) {
        List<Soutien> listeSoutiens = null;
        try {
            JpaUtil.creerContextePersistance();
            SoutienDao soutienDao = new SoutienDao();
            listeSoutiens = soutienDao.getSoutiensEleveDAO(eleve);
        } catch (Exception ex) {
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeSoutiens;
    }
    
    public static Soutien getSoutienEnCoursIntervenant(Intervenant intervenant) {
        Soutien soutien = null;
        try {
            JpaUtil.creerContextePersistance();
            SoutienDao soutienDao = new SoutienDao();
            soutien = soutienDao.getSoutienIntervenantStartedDAO(intervenant);
        } catch (Exception ex) {
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return soutien;
    }

    public static List<Soutien> getSoutiensTerminesIntervenant(Intervenant intervenant) {
        List<Soutien> listeSoutiens = null;
        try {
            JpaUtil.creerContextePersistance();
            SoutienDao soutienDao = new SoutienDao();
            listeSoutiens = soutienDao.getSoutiensIntervenantFinishedDAO(intervenant);
        } catch (Exception ex) {
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeSoutiens;
    }

    public static List<Etablissement> getEtablissements() {
        List<Etablissement> listeEtablissements = null;
        try {
            JpaUtil.creerContextePersistance();
            EtablissementDao etablissementDao = new EtablissementDao();
            listeEtablissements = etablissementDao.findAll();
        } catch (Exception ex) {
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeEtablissements;
    }
}