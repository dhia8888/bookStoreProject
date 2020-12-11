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

    public Panier(int id, String titreLiv, int nbrArticle, float total, int client_id) {
        this.id = id;
        this.titreLiv = titreLiv;
        this.nbrArticle = nbrArticle;
        this.total = total;
        this.client_id = client_id;
    }
    public Panier(String titreLiv, int nbrArticle, float total, int client_id) {
        this.titreLiv = titreLiv;
        this.nbrArticle = nbrArticle;
        this.total = total;
        this.client_id = client_id;
    }


    private String titreLiv;

    private int nbrArticle;

    private float total ;

    public String getTitreLiv() {
        return titreLiv;
    }

    public void setTitreLiv(String titreLiv) {
        this.titreLiv = titreLiv;
    }





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



    public int getClient_id() {
        return client_id;
    }

    public void setNbrArticle(int nbrArticle) {
        this.nbrArticle = nbrArticle;
    }

    public void setTotal(float total) {
        this.total = total;
    }


    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public Panier ()
    {
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", nbrArticle=" + nbrArticle +
                ", total=" + total +
                ", client_id=" + client_id +
                '}';
    }
}
