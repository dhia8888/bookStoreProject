package Model;

import java.util.Objects;

public class Genre {
    private int id_genre;
    private String nom_genre;

    public Genre() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nom_genre);
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
        final Genre other = (Genre) obj;
        if (!Objects.equals(this.nom_genre, other.nom_genre)) {
            return false;
        }
        return true;
    }



    public Genre(String nom_genre) {
        this.nom_genre = nom_genre;
    }

    public int getId_genre() {
        return id_genre;
    }

    public void setId_genre(int id_genre) {
        this.id_genre = id_genre;
    }

    public String getNom_genre() {
        return nom_genre;
    }

    public void setNom_genre(String nom_genre) {
        this.nom_genre = nom_genre;
    }

    @Override
    public String toString() {
        return "Genre{" + "id_genre=" + id_genre + ", nom_genre=" + nom_genre + '}';
    }


}