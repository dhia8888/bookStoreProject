/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Feedback;
<<<<<<< HEAD
=======

>>>>>>> feature7
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface FeedbackInterface {
    public ArrayList<Feedback> getFeedbacks() throws SQLException;
    public ArrayList<Feedback> getFeedbacksByUser(int id_user) throws SQLException;
    public Feedback getFeedbackById(int id_fb) throws SQLException ;
    public ArrayList<Feedback> getFeedbacksByBook(int id_livre) throws SQLException;

}
