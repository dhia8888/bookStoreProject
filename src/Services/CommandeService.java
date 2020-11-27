/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.CommandeInterface;
import Model.Commande;
import Model.Ligne_commande;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class CommandeService implements CommandeInterface{
     private Connection cnx;
    private ServiceLigne_Commande service_ligne_comm = new ServiceLigne_Commande();
    public CommandeService()
    {
        cnx = MyConnection.getInstance().getConnection();
    }
    public void addCommande(Commande c , int quantite ,int livre_id) throws SQLException
    {
        PreparedStatement preparedStmt = cnx.prepareStatement("INSERT into commande values (null,?,?,?)");
        preparedStmt.setString(1,c.getDate_com());
        preparedStmt.setInt(2,c.getLivraison());
        preparedStmt.setInt(3,c.getId_client());
        preparedStmt.executeUpdate();
        System.out.println( "Ajoute avec Succes commande");


        Commande com = getCommandeMaxid();
        Ligne_commande ligne_commande = new Ligne_commande(com.getId_com(),livre_id,quantite);
        service_ligne_comm.add(ligne_commande);

        System.out.println( "Ajoute avec Succes  ligne come commande");
    }

    public Commande getCommandeMaxid() throws SQLException
    {
        String request = "SELECT * FROM commande ORDER BY id DESC";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        if (rst.next()) {
            Commande c = new Commande();
            c.setId_com(rst.getInt(1));
            c.setDate_com(rst.getString(2));
            c.setLivraison(rst.getInt(3));
            c.setId_client(rst.getInt(4));
            return c;
        }
        return null;
    }

    public ArrayList<Commande> lister_Commandes() throws SQLException
    {
        ArrayList<Commande> results = new ArrayList<>();
        String request = "SELECT * FROM commande  ";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        while (rst.next())
        {
           Commande c = new Commande();
           c.setId_com(rst.getInt(1));
           c.setDate_com(rst.getString(2));
           c.setLivraison(rst.getInt(3));
           c.setId_client(rst.getInt(4));
           results.add(c);
        }
        return results;
    }

    public void updateCommande(Commande c) throws SQLException
    {
        PreparedStatement preparedStmt = cnx.prepareStatement("UPDATE commande SET date_com= ? ,livraison = ?   WHERE  id = ?");
        preparedStmt.setString(1,c.getDate_com());
        preparedStmt.setInt(2,c.getLivraison());
        preparedStmt.setInt(3,c.getId_com());
        preparedStmt.executeUpdate();
        System.out.println( "Update avec Succes commande");
    }

     public void deleteCommande (int id ) throws SQLException
     {
         service_ligne_comm.delete(id);
         System.out.println( "DELETE  avec Succes ligne commande");
        PreparedStatement preparedStmt = cnx.prepareStatement(" DELETE FROM commande WHERE id = ?; ");
        preparedStmt.setInt(1,id);
        preparedStmt.executeUpdate();
        System.out.println( "DELETE  avec Succes commande");
     }
    
}
