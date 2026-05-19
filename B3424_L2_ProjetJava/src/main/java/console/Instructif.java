/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package console;

import dao.JpaUtil;
import java.time.LocalDate;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Soutien;
import metier.modele.Theme;
import metier.service.ServiceCompte;
import metier.service.ServiceInitialisation;
import metier.service.ServiceSoutien;
import static util.Saisie.lireChaine;

/**
 *
 * @author echaffraix
 */
public class Instructif {

    public static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        ServiceInitialisation.InitIntervenants();
        ServiceInitialisation.InitMatiereTheme();
        /*testEleve();
        testIntervenant();*/
        JpaUtil.fermerFabriquePersistance();
    }
    
    private static void testEleve() {
        System.out.println("\n===== TEST ELEVE =====");

        ServiceCompte serviceInscription = new ServiceCompte();

        // Création du compte
        printlnConsoleIHM("Création compte");
        Eleve e1 = new Eleve("Dutour", "Alice", 3, LocalDate.of(2012, 5, 21), "alice@mail.fr", "12345");
        Boolean resultat1 = serviceInscription.inscrireEleve(e1, "0010080G");
        System.out.println("[INFO] Résultat inscription : " + resultat1);
        System.out.println("[INFO] Eleve créé : " + e1);

        // Authentification
        System.out.println("\n[INFO] Tentative de connexion élève...");
        Eleve eleve = ServiceCompte.authentificationEleve("alice@mail.fr", "12345");

        if (eleve != null) {
            System.out.println("[OK] Authentification réussie : " + eleve.getPrenom());

            Matiere matiereSoutien = null;
            Theme themeSoutien = null;

            System.out.println("\n[INFO] Récupération des matières...");
            List<Matiere> matieres = ServiceSoutien.getMatieres();

            for (Matiere matiere : matieres) {
                System.out.println(" - Matière trouvée : " + matiere.getNom());

                if (matiere.getNom().equals("Informatique")) {
                    matiereSoutien = matiere;
                    System.out.println("[OK] Matière sélectionnée : Informatique");
                    break;
                }
            }

            if (matiereSoutien != null) {
                System.out.println("\n[INFO] Parcours des thèmes de la matière...");
                for (Theme theme : matiereSoutien.getThemes()) {
                    System.out.println("   - Thème trouvé : " + theme.getNom());

                    if (theme.getNom().equals("Programmation")) {
                        themeSoutien = theme;
                        System.out.println("[OK] Thème sélectionné : Programmation");
                        break;
                    }
                }
            }

            System.out.println("\n[INFO] Création demande de soutien...");
            Soutien soutien = ServiceSoutien.creerDemande(
                eleve,
                matiereSoutien,
                themeSoutien,
                "Ceci est la description du soutien."
            );

            if (soutien != null) {
                System.out.println("[OK] Soutien créé !");
                if (soutien.getIntervenant() != null) {
                    System.out.println(" -> Intervenant assigné : " + soutien.getIntervenant().getPrenom());
                    System.out.println(" -> Email : " + soutien.getIntervenant().getEmail());
                    System.out.println(" -> Mot de passe : " + soutien.getIntervenant().getMdp());
                }
                else {
                    System.out.println("Soutien annulé -> Aucun intervenant n'a été trouvé");
                }
            } else {
                System.out.println("[ERREUR] Échec création soutien");
            }

        } else {
            System.out.println("[ERREUR] Authentification échouée");
        }
    }
    
    private static void testIntervenant() {
        System.out.println("\n===== TEST INTERVENANT =====");

        String email = lireChaine("Entrez votre email :");
        String mdp = lireChaine("Entrez votre mot de passe :");

        System.out.println("\n[INFO] Tentative de connexion intervenant...");
        Intervenant intervenant = ServiceCompte.authentificationIntervenant(email, mdp);

        if (intervenant != null) {
            System.out.println("[OK] Connexion réussie !");
            System.out.println(" -> Nom : " + intervenant.getNom());
            System.out.println(" -> Prénom : " + intervenant.getPrenom());

            System.out.println("\n[INFO] Recherche soutien en cours...");
            Soutien soutienIntervenant = ServiceSoutien.getSoutienEnCoursIntervenant(intervenant);

            if (soutienIntervenant != null) {
                System.out.println("[OK] Soutien trouvé !");
                System.out.println(" -> Élève : " + soutienIntervenant.getEleve().getPrenom());
                System.out.println(" -> Matière : " + soutienIntervenant.getMatiere().getNom());
                System.out.println(" -> Thème : " + soutienIntervenant.getTheme().getNom());

                System.out.println("\n[INFO] Fin du soutien...");
                ServiceSoutien.finirSoutien(soutienIntervenant, "Bravo tu as fait une super séance !");
                System.out.println("[OK] Soutien terminé !");
            } else {
                System.out.println("[INFO] Aucun soutien en cours");
            }

        } else {
            System.out.println("[ERREUR] Connexion échouée");
        }
    }
    
    public static void printlnConsoleIHM(Object o) {
        String BG_CYAN = "\u001b[46m";
        String RESET = "\u001B[0m";

        System.out.print(BG_CYAN);
        System.out.println(String.format("%-80s", o));
        System.out.print(RESET);
    }
}
