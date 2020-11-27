/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.EditionInterface;
import Model.Edition;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class EditionService implements EditionInterface{
     private Connection cnx;

    public EditionService() {
        cnx = MyConnection.getInstance().getConnection();
    }
    public ArrayList<Edition> getEditions() throws SQLException {
        ArrayList<Edition> editions = new ArrayList<>();
        String request1 = "SELECT * FROM `edition`";
        Statement stm1 = cnx.createStatement();
        ResultSet rst1 = stm1.executeQuery(request1);
        while (rst1.next()) {
            Edition e = new Edition();
            e.setId_editeur(rst1.getInt("id"));
            e.setAdresse(rst1.getString(2));
            e.setPays(rst1.getString(3));
            editions.add(e);
        }
        return editions;
    }
    public Edition getEditionByLivre(int id) throws SQLException {
        Edition e = null;
        String request1 = "SELECT * FROM `edition` WHERE id=" + id;
        Statement stm1 = cnx.createStatement();
        ResultSet rst1 = stm1.executeQuery(request1);
        if (rst1.next()) {
            e = new Edition();
            e.setId_editeur(rst1.getInt("id"));
            e.setAdresse(rst1.getString(2));
            e.setPays(rst1.getString(3));
        }
        return e;
    }
    
    public void addEdition(Edition e) throws SQLException {
        ArrayList<Edition> editions = getEditions();
        if (!editions.contains(e)) {
            String request = "INSERT INTO `edition`(`adresse`, `pays`) VALUES ('" + e.getAdresse() + "','" + e.getPays() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(request, Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            e.setId_editeur(generatedKeys.getInt(1));
        } else {
            for (Edition edt : editions) {
                if (edt.equals(e)) {
                    e.setId_editeur(edt.getId_editeur());
                    
                    
                }
            }
        }
    }

    
}
