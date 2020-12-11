package Model;

import java.util.HashSet;
import java.util.Objects;

public class Livre {

    private int id_livre;
    private String titre_livre;
    private String langue;
    private String description;
    private int nb_pages;
    private int nb_exemplaires;
    private String annee;
    private Double prix;
    private String image;

    private Edition editeur;
    private HashSet<Auteur> auteurs;
    private HashSet<Genre> genres;
    private HashSet<Feedback> feedbacks;

    public Livre(String titre_livre, String langue, String description, int nb_pages, int nb_exemplaires, String annee, Double prix, String image) {
        this.titre_livre = titre_livre;
        this.langue = langue;
        this.description = description;
        this.nb_pages = nb_pages;
        this.nb_exemplaires = nb_exemplaires;
        this.annee = annee;
        this.prix = prix;
        this.image = image;

        auteurs = new HashSet<Auteur>();
        genres = new HashSet<Genre>();
        feedbacks = new HashSet<Feedback>();

    }

    public Livre() {
        auteurs = new HashSet<Auteur>();
        genres = new HashSet<Genre>();
        feedbacks = new HashSet<Feedback>();

    }



    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.titre_livre);
        hash = 43 * hash + Objects.hashCode(this.langue);
        hash = 43 * hash + Objects.hashCode(this.editeur);
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
        final Livre other = (Livre) obj;
        if ((!Objects.equals(this.titre_livre, other.titre_livre)) || (!Objects.equals(this.langue, other.langue)) || (!Objects.equals(this.editeur, other.editeur))) {
            return false;
        }

        return true;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public Edition getEditeur() {
        return editeur;
    }

    public void setEditeur(Edition editeur) {
        this.editeur = editeur;
    }

    public String getTitre_livre() {
        return titre_livre;
    }

    public void setTitre_livre(String titre_livre) {
        this.titre_livre = titre_livre;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNb_pages() {
        return nb_pages;
    }

    public void setNb_pages(int nb_pages) {
        this.nb_pages = nb_pages;
    }

    public int getNb_exemplaires() {
        return nb_exemplaires;
    }

    public void setNb_exemplaires(int nb_exemplaires) {
        this.nb_exemplaires = nb_exemplaires;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    @Override
    public String toString() {
        return "Livre{" + "id_livre=" + id_livre + ", editeur=" + editeur + ", titre_livre=" + titre_livre + ", langue=" + langue + ", description=" + description + ", nb_pages=" + nb_pages + ", nb_exemplaires=" + nb_exemplaires + ", annee=" + annee + '}';
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setAuteurs(HashSet<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public void setGenres(HashSet<Genre> genres) {
        this.genres = genres;
    }

    public void setFeedbacks(HashSet<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Double getPrix() {
        return prix;
    }

    public HashSet<Auteur> getAuteurs() {
        return auteurs;
    }

    public HashSet<Genre> getGenres() {
        return genres;
    }

    public HashSet<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
