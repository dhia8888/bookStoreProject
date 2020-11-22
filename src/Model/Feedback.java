package Model;

public class Feedback {
    private int id_fb;
    private int nb_etoile;
    private String commentaire;

    public Feedback() {
    }

    public Feedback(int id_fb, int nb_etoile, String commentaire) {
        this.id_fb = id_fb;
        this.nb_etoile = nb_etoile;
        this.commentaire = commentaire;
    }

    public Feedback(int nb_etoile, String commentaire) {
        this.nb_etoile = nb_etoile;
        this.commentaire = commentaire;
    }

    public int getId_fb() {
        return id_fb;
    }

    public void setId_fb(int id_fb) {
        this.id_fb = id_fb;
    }

    public int getNb_etoile() {
        return nb_etoile;
    }

    public void setNb_etoile(int nb_etoile) {
        this.nb_etoile = nb_etoile;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id_fb=" + id_fb + ", nb_etoile=" + nb_etoile + ", commentaire=" + commentaire + '}';
    }


}