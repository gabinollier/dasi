/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.service;

import dao.IntervenantDao;
import dao.JpaUtil;
import dao.MatiereDao;
import java.util.ArrayList;
import java.util.List;
import metier.modele.Autre;
import metier.modele.Etudiant;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Professeur;
import metier.modele.Theme;

/**
 *
 * @author echaffraix
 */
public class ServiceInitialisation {
    public static void InitIntervenants() {
        // Création et ajout des intervenants dans une liste
        List<Intervenant> intervenants = new ArrayList<>();

        // 3 Professeurs
        intervenants.add(new Professeur("Lycée", "Dupont", "Camille", 6, 3, "camille.dupont@mail.fr", "mdp123", "0612345678"));
        intervenants.add(new Professeur("Collège", "Martin", "Jean", 5, 2, "jean.martin@mail.fr", "password", "0623456789"));
        intervenants.add(new Professeur("Université", "Bernard", "Claire", 7, 4, "claire.bernard@mail.fr", "securepass", "0634567890"));

        // 3 Étudiants
        intervenants.add(new Etudiant("Informatique", "INSA Lyon", "Leclerc", "Tom", 6, 5, "tom.leclerc@mail.fr", "pass123", "0645678901"));
        intervenants.add(new Etudiant("Mathématiques", "Sorbonne", "Durand", "Lucie", 5, 4, "lucie.durand@mail.fr", "mypassword", "0656789012"));
        intervenants.add(new Etudiant("Physique", "Polytech", "Moreau", "Max", 6, 6, "max.moreau@mail.fr", "123456", "0667890123"));

        // 3 Autres
        intervenants.add(new Autre("Retraité", "Chaffraix", "Emilien", 4, 3, "emilien.chaffraix@mail.fr", "pass567", "0678901234"));
        intervenants.add(new Autre("Consultant", "Roux", "Sophie", 3, 2, "sophie.roux@mail.fr", "secure1", "0689012345"));
        intervenants.add(new Autre("Formateur", "Faure", "Antoine", 5, 3, "antoine.faure@mail.fr", "secret!", "0690123456"));

        // Boucle pour persister chaque intervenant
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            IntervenantDao intervenantDao = new IntervenantDao();
            for (Intervenant i : intervenants) {
                intervenantDao.create(i);
                System.out.println("Persisté : " + i.getNom() + " " + i.getPrenom());
            }
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public static void InitMatiereTheme() {
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            MatiereDao matiereDao = new MatiereDao();

            // Français
            Matiere francais = new Matiere("Français");
            francais.addTheme(new Theme("Le roman"));
            francais.addTheme(new Theme("La poésie"));
            francais.addTheme(new Theme("Le théâtre"));
            matiereDao.create(francais);

            // Mathématiques
            Matiere maths = new Matiere("Mathématiques");
            maths.addTheme(new Theme("Algèbre"));
            maths.addTheme(new Theme("Géométrie"));
            maths.addTheme(new Theme("Analyse"));
            matiereDao.create(maths);

            // Physique
            Matiere physique = new Matiere("Physique");
            physique.addTheme(new Theme("Mécanique"));
            physique.addTheme(new Theme("Électricité"));
            physique.addTheme(new Theme("Thermodynamique"));
            matiereDao.create(physique);

            // Informatique
            Matiere info = new Matiere("Informatique");
            info.addTheme(new Theme("Programmation"));
            info.addTheme(new Theme("Bases de données"));
            info.addTheme(new Theme("Réseaux"));
            matiereDao.create(info);

            // Histoire
            Matiere histoire = new Matiere("Histoire");
            histoire.addTheme(new Theme("Moyen Âge"));
            histoire.addTheme(new Theme("Renaissance"));
            histoire.addTheme(new Theme("Révolution française"));
            matiereDao.create(histoire);

            // Biologie
            Matiere bio = new Matiere("Biologie");
            bio.addTheme(new Theme("Botanique"));
            bio.addTheme(new Theme("Zoologie"));
            bio.addTheme(new Theme("Génétique"));
            matiereDao.create(bio);

            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
}