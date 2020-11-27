/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Panier;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public interface PanierInterface {

    public void addPanier(Panier p) throws SQLException;

    public ResultSet lister_Panier() throws SQLException;

    public void updatePanier(Panier p) throws SQLException;

    public void deletePanier(int id) throws SQLException;

}
