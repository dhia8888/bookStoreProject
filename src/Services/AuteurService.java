/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Interfaces.AuteurInterface;
import Model.Auteur;
import Model.Livre;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
/**
 *
 * @author Lenovo
 */
public class
AuteurService implements AuteurInterface{
     private Connection cnx;

    public AuteurService() {
        cnx = MyConnection.getInstance().getConnection();
    }
     public ArrayList<Auteur> getAuteurs() throws SQLException {
        ArrayList<Auteur> auteurs = new ArrayList<>();
        String request3 = "SELECT * FROM `auteur`";
        Statement stm3 = cnx.createStatement();
        ResultSet rst = stm3.executeQuery(request3);

        while (rst.next()) {
            Auteur a = new Auteur();
            a.setId_auteur(rst.getInt(1));
            a.setNom(rst.getString(2));
            a.setPrenom(rst.getString(3));
            a.setBio(rst.getString(4));
            a.setDate_naissance(rst.getString(5));
            auteurs.add(a);
        }
        return auteurs;
    }
     public Auteur getAuteurById(int id) throws SQLException {
        Auteur a = new Auteur();
        String request3 = "SELECT * FROM `auteur` WHERE id =" + id;
        Statement stm3 = cnx.createStatement();
        ResultSet rst = stm3.executeQuery(request3);

        if (rst.next()) {

            a.setId_auteur(rst.getInt(1));
            a.setNom(rst.getString(2));
            a.setPrenom(rst.getString(3));
            a.setBio(rst.getString(4));
            a.setDate_naissance(rst.getString(5));

        }
        return a;
    }
      public void addAuteur(Auteur a) throws SQLException {
        ArrayList<Auteur> auteurs = getAuteurs();
        if (!auteurs.contains(a)) {
            String request = "INSERT INTO `auteur`(`nom`, `prenom`, `bio`, `date_naissance`) VALUES ('"
                    + a.getNom() + "','" + a.getPrenom() + "','" + a.getBio() + "','" + a.getDate_naissance() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(request, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            a.setId_auteur(generatedKeys.getInt(1));
        } else {
            for (Auteur aut : auteurs) {
                if (aut.equals(a)) {
   
                    a.setId_auteur(aut.getId_auteur());
                }
            }
        }
    }
public HashSet<Auteur> getAuteursByLivre(Livre livre) throws SQLException {
        String request3 = "SELECT * FROM `livre_auteur` WHERE id_livre=" + livre.getId_livre();
        Statement stm3 = cnx.createStatement();
        ResultSet rst = stm3.executeQuery(request3);
        HashSet<Auteur> auteurs = new HashSet<Auteur>();
        
        while (rst.next()) {
        Auteur a = getAuteurById(rst.getInt(1));
        
        auteurs.add(a);
        }
        return auteurs;
    }
}
