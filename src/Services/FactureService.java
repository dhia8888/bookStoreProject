/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.FactureInterface;
import Model.Facture;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class FactureService implements FactureInterface{
     private Connection cnx;

        public FactureService() {
            cnx = MyConnection.getInstance().getConnection();

        }

        public void addFacture(Facture f) throws SQLException {

            /*
            String request = "INSERT INTO facture VALUES ('"+f.getDate_fact()+"','"+f.getMontant()+"','"+f.getCommande_id()+"')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(request);
            System.out.println(" insert data facture ");
        */

            PreparedStatement preparedStmt = cnx.prepareStatement("INSERT INTO facture  VALUES (null,?,?,?)");
            preparedStmt.setString(1,f.getDate_fact());
            preparedStmt.setFloat(2,f.getMontant());
            preparedStmt.setInt(3,f.getCommande_id());
            preparedStmt.executeUpdate();
            System.out.println( "add avec Succes Facture");

        }

        public ArrayList<Facture> getFactures() throws SQLException {
            ArrayList<Facture> results = new ArrayList<>();
            String request = "SELECT * FROM `Facture`";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(request);

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

        public Facture getFacture(int id) throws SQLException {
            String request = "SELECT * FROM `Facture` WHERE id =" + id;
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(request);

            if (rst.next()) {
                Facture f = new Facture();
                f.setId(rst.getInt("id"));
                f.setDate_fact(rst.getString(2));
                f.setMontant(rst.getFloat(3));
                f.setCommande_id(rst.getInt(4));
                return f;
            }

            return null;
        }

        public void updateFacture(Facture f) throws SQLException {

            PreparedStatement pst = cnx.prepareStatement("UPDATE facture SET date_fact=?,montant=?,commande_id=? WHERE commande_id = ?");
            pst.setString(1,f.getDate_fact());
            pst.setFloat(2,f.getMontant());
            pst.setInt(3, f.getCommande_id());
            pst.setInt(4,f.getCommande_id());
            pst.executeUpdate();
            System.out.println(" update facture");

        }

        public void deleteFacture(int id) throws SQLException {
            String request = "DELETE FROM Facture WHERE id =" + id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(request);
            System.out.println(" delete facture");
        }
}
