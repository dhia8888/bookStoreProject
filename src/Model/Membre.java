package Model;

import java.util.HashSet;
import java.util.Set;

public class Membre extends User {
    private int numinscrit;

    private static Set<Livre> livres_populaires;
    private Set<Livre> livre_recommande;

    public Membre() {
        super();
    }

    public Membre(int id, String nom, String prenom, String username, String email, String adresse, String role, int numinscrit) {
        super(id, nom, prenom, username, email, adresse, role);
        this.numinscrit = numinscrit;
        livres_populaires = new HashSet<Livre>();
        livre_recommande = new HashSet<Livre>();
    }

    public Membre(String nom, String prenom, String username, String email, String adresse, String role, int numinscrit) {
        super(nom, prenom, username, email, adresse, role);
        this.numinscrit = numinscrit;
        livres_populaires = new HashSet<Livre>();
        livre_recommande = new HashSet<Livre>();
    }

    public int getNuminscrit() {
        return numinscrit;
    }

    public void setNuminscrit(int numinscrit) {
        this.numinscrit = numinscrit;
    }

}