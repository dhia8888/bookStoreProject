package Model;

public class Gerant extends User{

    private int rib;
    private String matricule;
    private double salaire;

    public Gerant() {
        super();
    }

    public Gerant(int id, String nom, String prenom, String username, String email, String adresse,
                  String role,int rib,String matricule,double salaire) {
        super(id, nom, prenom, username, email, adresse, role);
        this.rib=rib;
        this.matricule=matricule;
        this.salaire=salaire;

    }
    public Gerant(String nom, String prenom, String username, String email, String adresse,
                  String role,int rib,String matricule,double salaire) {
        super(nom, prenom, username, email, adresse, role);
        this.rib=rib;
        this.matricule=matricule;
        this.salaire=salaire;
    }

    public int getRib() {
        return rib;
    }

    public void setRib(int rib) {
        this.rib = rib;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }


}