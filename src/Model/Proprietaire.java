package Model;

public class Proprietaire extends User
{
    private String immatriculation;

    public Proprietaire() {
        super();
    }

    public Proprietaire(int id, String nom, String prenom, String username, String email, String adresse, String role, String immatriculatio) {
        super(id, nom, prenom, username, email, adresse, role);
        this.immatriculation = immatriculation;
    }

    public Proprietaire(String nom, String prenom, String username, String email, String adresse, String role, String immatriculatio) {
        super(nom, prenom, username, email, adresse, role);
        this.immatriculation = immatriculation;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String toString() {
        String res = super.toString() + " immatriculation :" + this.immatriculation;
        return res;
    }

}





