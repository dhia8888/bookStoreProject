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

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface UserInterface {

    public void addProprietaire(Proprietaire p) throws SQLException;
    public void addMember(Membre m) throws SQLException;
    public void addGerant(Gerant g) throws SQLException;
    public ArrayList<User> getUsers() throws SQLException;
    public ArrayList<Membre> getMembers() throws SQLException;
    public ArrayList<Gerant> getGerants() throws SQLException;
    public void deleteUser(int id) throws SQLException;

}
