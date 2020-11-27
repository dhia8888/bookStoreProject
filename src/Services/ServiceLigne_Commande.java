/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.LigneCommandeInterface;
import Model.Ligne_commande;
import utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class ServiceLigne_Commande implements LigneCommandeInterface{
    private Connection cnx;

    public ServiceLigne_Commande ()
        {
            cnx = MyConnection.getInstance().getConnection();
        }

    public void add(Ligne_commande ligne_com )throws SQLException
    {

        PreparedStatement preparedStmt = cnx.prepareStatement("INSERT INTO `ligne_commande`(`commande_id`, `livre_id`, `quantite`) VALUES (?,?,?) ");

        preparedStmt.setInt(1,ligne_com.getCommande_id());
        preparedStmt.setInt(2,ligne_com.getLivre_id());
        preparedStmt.setInt(3,ligne_com.getQuantite());
        preparedStmt.executeUpdate();
    }

    public void delete(int id  )throws SQLException
    {
        PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM `ligne_commande` WHERE commande_id = ? ");
        preparedStmt.setInt(1,id);
        preparedStmt.executeUpdate();

    }

    
}
