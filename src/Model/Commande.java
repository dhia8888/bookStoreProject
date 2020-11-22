package Model;

import java.util.Date;

public class Commande {

    private int id_com;
    private Date date_com;
    private int totale;
    private String livraison;

    public Commande() {
    }

    public Commande(int id_com, Date date_com, int totale, String livraison) {
        this.id_com = id_com;
        this.date_com = date_com;
        this.totale = totale;
        this.livraison = livraison;
    }

    public Commande(Date date_com, int totale, String livraison) {
        this.date_com = date_com;
        this.totale = totale;
        this.livraison = livraison;
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public Date getDate_com() {
        return date_com;
    }

    public void setDate_com(Date date_com) {
        this.date_com = date_com;
    }

    public int getTotale() {
        return totale;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }

    public String getLivraison() {
        return livraison;
    }

    public void setLivraison(String livraison) {
        this.livraison = livraison;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_com=" + id_com + ", date_com=" + date_com + ", totale=" + totale + ", livraison=" + livraison + '}';
    }



}