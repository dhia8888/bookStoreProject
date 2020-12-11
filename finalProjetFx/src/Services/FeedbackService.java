/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Interfaces.FeedbackInterface;
import Model.Feedback;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 */
public class FeedbackService implements FeedbackInterface{
    private Connection cnx;
     public FeedbackService() {
      cnx = MyConnection.getInstance().getConnection();
    }
     public ArrayList<Feedback> getFeedbacks() throws SQLException {
        ArrayList<Feedback> results = new ArrayList<>();
        String request = "SELECT * FROM `feedback`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Feedback f = new Feedback();
            f.setId_fb(rst.getInt("id"));
            f.setNb_etoile(rst.getInt(2));
            f.setCommentaire(rst.getString(3));
            f.setId_membre(rst.getInt(4));
            f.setId_livre(rst.getInt(5));


            results.add(f);
        }
        return results;
     }
    public ArrayList<Feedback> getFeedbacksByUser(int id_user) throws SQLException {
                ArrayList<Feedback> results = new ArrayList<>();

        String request = "SELECT * FROM `feedback` WHERE id_user =" + id_user;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
 while (rst.next()) {
            Feedback f = new Feedback();
            f.setId_fb(rst.getInt("id"));
            f.setNb_etoile(rst.getInt(2));
            f.setCommentaire(rst.getString(3));
            f.setId_membre(rst.getInt(4));
            f.setId_livre(rst.getInt(5));
            results.add(f);
        }
        return results;
}
    public Feedback getFeedbackById(int id_fb) throws SQLException {
        String request = "SELECT * FROM `feedback` WHERE id =" + id_fb;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        if (rst.next()) {
            Feedback f = new Feedback();
            f.setId_fb(rst.getInt("id"));
            f.setNb_etoile(rst.getInt(2));
            f.setCommentaire(rst.getString(3));
            f.setId_membre(rst.getInt(4));
            f.setId_livre(rst.getInt(5));
            return f;
        }
        return null;
}
    
    public ArrayList<Feedback> getFeedbacksByBook(int id_livre) throws SQLException {
         ArrayList<Feedback> results = new ArrayList<>();
        String request = "SELECT * FROM `feedback` WHERE livre_id =" + id_livre;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Feedback f = new Feedback();
            f.setId_fb(rst.getInt("id"));
            f.setNb_etoile(rst.getInt(2));
            f.setCommentaire(rst.getString(3));
            f.setId_membre(rst.getInt(4));
            f.setId_livre(rst.getInt(5));
            results.add(f);
        }
        return results;
}
}
