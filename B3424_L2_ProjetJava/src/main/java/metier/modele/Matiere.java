/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author echaffraix
 */
@Entity
public class Matiere {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
    private List<Theme> themes;

    public Matiere() {
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public Matiere(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Long getId() {
        return id;
    }

    public void setThemes(List themes) {
        this.themes = themes;
    }
    
    public void addTheme(Theme theme) {
        if (themes == null) {
            themes = new ArrayList<>();
        }
        themes.add(theme);
        theme.setMatiere(this);
    }
}
