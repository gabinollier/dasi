/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import metier.modele.Matiere;

/**
 *
 * @author echaffraix
 */
public class MatiereDao {
    public void create(Matiere matiere) {
        JpaUtil.obtenirContextePersistance().persist(matiere);
    }
    
    public Matiere findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Matiere.class, id);
    } 
    
    public Matiere findByName(String nom) {
        try {
            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT m FROM Matiere m WHERE m.nom = :nom", Matiere.class)
                    .setParameter("nom", nom)
                    .getSingleResult();
        } catch (Exception ex) {
            return null; // aucune matière trouvée
        }
    }
    
    public List<Matiere> getMatieresDAO() {
        try {
            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT m FROM Matiere m", Matiere.class)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
}
