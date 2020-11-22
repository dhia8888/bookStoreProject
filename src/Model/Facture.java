package Model;



public class Facture {
   private int id;

    private String date_fact;

    private float montant;

    public Facture(String date_fact, float montant, int commande_id) {
        this.date_fact = date_fact;
        this.montant = montant;
        this.commande_id = commande_id;
    }

    public Facture(int id, String date_fact, float montant, int commande_id) {
        this.id = id;
        this.date_fact = date_fact;
        this.montant = montant;
        this.commande_id = commande_id;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", date_fact='" + date_fact + '\'' +
                ", montant=" + montant +
                ", commande_id=" + commande_id +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public Facture ()
    {

    }

    public void setDate_fact(String date_fact) {
        this.date_fact = date_fact;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    private int commande_id;


    public int getId() {
        return id;
    }

    public String getDate_fact() {
        return date_fact;
    }

    public float getMontant() {
        return montant;
    }

    public int getCommande_id() {
        return commande_id;
    }

}