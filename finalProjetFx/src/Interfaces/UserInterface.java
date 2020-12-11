/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Gerant;
import Model.Membre;
import Model.Proprietaire;
import Model.User;
import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface UserInterface {

    public void addProprietaire(Proprietaire p) throws SQLException;
    public void addMember(Membre m) throws SQLException;
    public void addGerant(Gerant g) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException ;
    public ArrayList<User> getUsers() throws SQLException;
    public ArrayList<Membre> getMembers() throws SQLException;
    public ObservableList<Gerant> getGerants() throws SQLException;
    public void deleteUser(User u) throws SQLException;
    public boolean UserExist(String email)throws SQLException;
    public boolean Login(String email, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException;
    public void resetPassword (String email) throws SQLException;

}
