/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import metier.modele.Eleve;

/**
 *
 * @author echaffraix
 */
public class EleveDao {

    public EleveDao() {
    }
    
    public void create(Eleve eleve) {
        JpaUtil.obtenirContextePersistance().persist(eleve);
    }
    
    public Eleve findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Eleve.class, id);
    }
    
    public Eleve findByLogin(String mail, String mdp) {
        try {
            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT e FROM Eleve e WHERE e.email = :mail AND e.mdp = :mdp", Eleve.class)
                    .setParameter("mail", mail)
                    .setParameter("mdp", mdp)
                    .getSingleResult();
        } catch (Exception ex) {
            return null; // aucun élève trouvé avec ce mail
        }   
    }
}