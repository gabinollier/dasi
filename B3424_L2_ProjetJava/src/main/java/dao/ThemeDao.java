/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import metier.modele.Theme;

/**
 *
 * @author echaffraix
 */
public class ThemeDao {
    public Theme findByName(String nom) {
        
        try {
            System.out.print("Appel au ThemeDao avec nom = " + nom + "\n");

            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT t FROM Theme t WHERE t.nom = :nom", Theme.class)
                    .setParameter("nom", nom)
                    .getSingleResult();
        } catch (Exception ex) {
            System.out.print("Erreur dans ThemeDao : " + ex);
            return null; // aucun thème trouvé
        }
    }
}
