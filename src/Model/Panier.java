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
public class Panier {
     private int id ;

    private int nbrArticle;

    private float total ;

    private int commande_id;

    private  int client_id;
      public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public int getNbrArticle() {
        return nbrArticle;
    }

    public float getTotal() {
        return total;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setNbrArticle(int nbrArticle) {
        this.nbrArticle = nbrArticle;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

   


    public Panier(int nbrArticle, float total, int commande_id, int client_id) {
        this.nbrArticle = nbrArticle;
        this.total = total;
        this.commande_id = commande_id;
        this.client_id = client_id;
    }
    
}
