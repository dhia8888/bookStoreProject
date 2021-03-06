package Model;

import java.util.Objects;

public class Edition {

    private int id_editeur;
    private String pays;
    private String adresse;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.pays);
        hash = 11 * hash + Objects.hashCode(this.adresse);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edition other = (Edition) obj;
        if ((!Objects.equals(this.pays, other.pays))&&(!Objects.equals(this.adresse, other.adresse)) ){
            return false;
        }

        return true;
    }



    public Edition() {
    }

    public Edition( String pays, String adresse) {
        this.pays = pays;
        this.adresse = adresse;
    }

    public int getId_editeur() {
        return id_editeur;
    }

    public void setId_editeur(int id_editeur) {
        this.id_editeur = id_editeur;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Edition{" + "id_editeur=" + id_editeur + ", pays=" + pays + ", adresse=" + adresse + '}';
    }

}