package Model;

import java.util.Date;

public class Facture {
    private int id_fact;
    private Date date;
    private int montant;

    public Facture() {
    }

    public Facture(int id_fact, Date date, int montant) {
        this.id_fact = id_fact;
        this.date = date;
        this.montant = montant;
    }

    public Facture(Date date, int montant) {
        this.date = date;
        this.montant = montant;
    }

    public int getId_fact() {
        return id_fact;
    }

    public void setId_fact(int id_fact) {
        this.id_fact = id_fact;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Facture{" + "id_fact=" + id_fact + ", date=" + date + ", montant=" + montant + '}';
    }


}