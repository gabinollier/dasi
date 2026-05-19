/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import metier.modele.Etablissement;

/**
 *
 * @author echaffraix
 */
public class EtablissementDao {

    public EtablissementDao() {
    }
    
    public void create(Etablissement etablissement) {
        JpaUtil.obtenirContextePersistance().persist(etablissement);
    }
    
    public void merge(Etablissement etablissement) {
    JpaUtil.obtenirContextePersistance().merge(etablissement);
    }
    
    public Etablissement findById(String codeUAI) {
        return JpaUtil.obtenirContextePersistance().find(Etablissement.class, codeUAI);
    }

    public List<Etablissement> findAll() {
        try {
            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT e FROM Etablissement e", Etablissement.class)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
}
