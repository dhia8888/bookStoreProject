package Services;
import Model.Facture;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatistiquesServiceVente
{
    Connection cnx;
    public StatistiquesServiceVente()
    {
        cnx = MyConnection.getInstance().getConnection();
    }
    public Double getRevenue() throws SQLException
    {
        PreparedStatement stm = cnx.prepareStatement("SELECT SUM(montant) from facture");
        ResultSet rst = stm.executeQuery();
        rst.next();
        return rst.getDouble(1);
    }

    public int getLivreNonVendu() throws SQLException
    {
        PreparedStatement stm = cnx.prepareStatement("SELECT count(livre.id) from livre where id not in (SELECT ligne_commande.livre_id from ligne_commande INNER JOIN facture on facture.commande_id=ligne_commande.commande_id)");
        ResultSet rst = stm.executeQuery();
        rst.next();
        return rst.getInt(1);
    }

    public int getLivreVendu() throws SQLException
    {
        PreparedStatement stm = cnx.prepareStatement("SELECT COUNT(ligne_commande.livre_id) from ligne_commande INNER JOIN facture on facture.commande_id=ligne_commande.commande_id");
        ResultSet rst = stm.executeQuery();
        rst.next();
        return rst.getInt(1);
    }



    public  ArrayList<String> getStatCommandeByGenreLivre() throws SQLException
    {
        int sum = 0;
        HashMap<Integer, String> stat = new HashMap<Integer, String>();
        PreparedStatement stm = cnx.prepareStatement("SELECT genre.nom_genre FROM ligne_commande INNER JOIN facture " +
                "on facture.commande_id=ligne_commande.commande_id INNER JOIN " +
                "genre_livre on genre_livre.livre_id=ligne_commande.livre_id " +
                "INNER JOIN genre on genre.id=genre_livre.genre_id");
        ResultSet rst= stm.executeQuery();
        ArrayList<String> liste = new ArrayList<>();
        while (rst.next())
        {
            liste.add(rst.getString(1));
        }

        return liste;
    }
    public  ArrayList<Facture> getBestSales () throws SQLException
    {
        ArrayList<Facture> results = new ArrayList<>();
        PreparedStatement stm = cnx.prepareStatement("SELECT * FROM facture ORDER by montant DESC LIMIT 5");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Facture f = new Facture();
            f.setId(rst.getInt("id"));
            f.setDate_fact(rst.getString(2));
            f.setMontant(rst.getFloat(3));
            f.setCommande_id(rst.getInt(4));
            results.add(f);
        }
        return results;
    }
    public  ArrayList<Facture> getSales () throws SQLException
    {
        ArrayList<Facture> results = new ArrayList<>();
        PreparedStatement stm = cnx.prepareStatement("SELECT facture.date_fact,facture.montant FROM `facture`");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Facture f = new Facture();
            f.setDate_fact(rst.getString(1));
            f.setMontant(rst.getFloat(2));
            results.add(f);
        }
        return results;
    }
    public ArrayList<String> getStatCommandeByAuteurLivre() throws SQLException
    {
        int sum = 0;
        Map<Integer, String> stat = new HashMap<Integer, String>();
        PreparedStatement stm = cnx.prepareStatement("SELECT auteur.prenom from facture " +
                "INNER JOIN ligne_commande on ligne_commande.commande_id=facture.commande_id INNER JOIN livre_auteur on ligne_commande.livre_id=livre_auteur.id_livre " +
                "INNER JOIN auteur on auteur.id=livre_auteur.id_auteur;");
        ResultSet rst= stm.executeQuery();
        ArrayList<String> liste = new ArrayList<>();
        while (rst.next())
        {
            liste.add(rst.getString(1));
        }
        return  liste;
    }
}
