/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Soutien;

/**
 *
 * @author echaffraix
 */
public class SoutienDao {
    public void create(Soutien soutien) {
        JpaUtil.obtenirContextePersistance().persist(soutien);
    }
    
    public Soutien merge(Soutien soutien) {
        return JpaUtil.obtenirContextePersistance().merge(soutien);
    }
    
    public Soutien findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Soutien.class, id);
    }
    
    public List<Soutien> getSoutiensEleveDAO(Eleve eleve) {
        try {
            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT s FROM Soutien s WHERE s.eleve.id = :id", Soutien.class)
                    .setParameter("id", eleve.getId())
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public Soutien getSoutienIntervenantStartedDAO(Intervenant intervenant) {
        try {
            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT s FROM Soutien s WHERE s.intervenant.id = :id AND s.statut = :statut", Soutien.class)
                    .setParameter("id", intervenant.getId())
                    .setParameter("statut", "STARTED")
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<Soutien> getSoutiensIntervenantFinishedDAO(Intervenant intervenant) {
        try {
            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT s FROM Soutien s WHERE s.intervenant.id = :id AND s.statut = :statut", Soutien.class)
                    .setParameter("id", intervenant.getId())
                    .setParameter("statut", "FINISHED")
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
}
