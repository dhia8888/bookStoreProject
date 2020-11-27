/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Edition;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface EditionInterface {
     public Edition getEditionByLivre(int id) throws SQLException;
     public ArrayList<Edition> getEditions() throws SQLException;
     public void addEdition(Edition e) throws SQLException;
}
