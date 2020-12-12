/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class Ligne_commande {
     private  int commande_id;

    private int livre_id ;

    private int quantite;

    public Ligne_commande(int commande_id, int livre_id, int quantite) {
        this.commande_id = commande_id;
        this.livre_id = livre_id;
        this.quantite = quantite;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public int getLivre_id() {
        return livre_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public void setLivre_id(int livre_id) {
        this.livre_id = livre_id;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "ligne_commande{" +
                "commande_id=" + commande_id +
                ", livre_id=" + livre_id +
                ", quantite=" + quantite +
                '}';
    }
}
