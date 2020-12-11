/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.NotificationInterface;
import Model.Notification;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class NotificationService implements NotificationInterface{
    private Connection cnx;

    public NotificationService() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addNotification(Notification n) throws SQLException {
//        String request = "INSERT INTO `notification` (`id`, `type`, `message`,'vu')"
//                + " VALUES (NULL, '"  + n.getType() + "', '"+ n.getMessage() +"', '"+ n.getVu()+"')";
//
//        Statement stm = cnx.createStatement();
//        stm.executeUpdate(request);
          PreparedStatement stm = cnx.prepareStatement("INSERT INTO `notification`(`type`, `message`, `vu`) VALUES (?,?,?)");
        stm.setString(1,n.getType());
        stm.setString(2,n.getMessage());
        stm.setInt(3,n.getVu());


        stm.executeUpdate();
        System.out.println("Ligne ajoutée");
    }

    public ArrayList<Notification> getNotifications() throws SQLException {
        ArrayList<Notification> results = new ArrayList<>();
        String request = "SELECT * FROM `notification`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Notification n = new Notification();
            n.setId_notif(rst.getInt("id"));
            n.setType(rst.getString(2));
            n.setMessage(rst.getString(3));
            n.setVu(rst.getInt(4));

            results.add(n);
        }

        return results;
    }
    
     public void deleteNotification(int id_notif) throws SQLException {
        String request = "DELETE FROM `notification` WHERE id =" + id_notif;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }
     
     public void updateNotification(Notification n) throws SQLException {
        String request = "UPDATE `notification` SET `vu`=? WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setInt(1, 1);
        pst.setInt(2, n.getId_notif());
        pst.executeUpdate();

    }
     
     public Notification getNotificationById(int id_notif) throws SQLException {
        String request = "SELECT * FROM `notification` WHERE id =" + id_notif;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        if (rst.next()) {
            Notification n = new Notification();
            n.setId_notif(rst.getInt("id"));
            n.setType(rst.getString(2));
            n.setMessage(rst.getString(3));
            n.setVu(rst.getInt(4));
           
            return n;
        }
        return null;
     }
        
      public int getNotificationWithNoView() throws SQLException {
           String request = "SELECT COUNT(*) FROM `notification` WHERE vu =0";
           Statement stm = cnx.createStatement();
           ResultSet rst = stm.executeQuery(request);
           rst.next();
            
        return rst.getInt(1);
      }
}
