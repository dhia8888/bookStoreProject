package Model;


import java.util.HashSet;

public class Commande {

     HashSet<Livre> listesLivres;

    public Commande(int id_client, int id_com, String date_com, int livraison) {
        this.id_client = id_client;
        this.id_com = id_com;
        this.date_com = date_com;
        this.livraison = livraison;
    }

    public Commande(int id_client, String date_com, int livraison) {
        this.id_client = id_client;
        this.date_com = date_com;
        this.livraison = livraison;
    }

    private int id_client;
    private int id_com;
    private String date_com;
    private int livraison;

    public Commande()
    {
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_com() {
        return id_com;
    }

    public String getDate_com() {
        return date_com;
    }

    public int getLivraison() {
        return livraison;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public void setDate_com(String date_com) {
        this.date_com = date_com;
    }

    public void setLivraison(int livraison) {
        this.livraison = livraison;
    }

    @Override
    public String toString() {
        return "Commande{" +
                ", id_com=" + id_com +
                "id_client=" + id_client +
                ", date_com='" + date_com + '\'' +
                ", livraison=" + livraison +
                '}';
    }



}