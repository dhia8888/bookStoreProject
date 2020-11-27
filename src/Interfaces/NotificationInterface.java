/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Notification;
<<<<<<< HEAD
=======

>>>>>>> feature7
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface NotificationInterface {
    public void addNotification(Notification n) throws SQLException;
    public ArrayList<Notification> getNotifications() throws SQLException;
    public void deleteNotification(int id_notif) throws SQLException;
    public void updateNotification(Notification n) throws SQLException;
    public Notification getNotificationById(int id_notif) throws SQLException;
    public int getNotificationWithNoView() throws SQLException;
}
