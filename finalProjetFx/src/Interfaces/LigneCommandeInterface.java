/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Ligne_commande;

import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public interface LigneCommandeInterface {

    public void add(Ligne_commande ligne_com) throws SQLException;
    public void delete(int id) throws SQLException;
}
