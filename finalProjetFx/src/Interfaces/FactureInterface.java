/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Facture;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface FactureInterface {

    public void addFacture(Facture f) throws SQLException;
    public ArrayList<Facture> getFactures() throws SQLException;
    public Facture getFacture(int id) throws SQLException;
    public void updateFacture(Facture f) throws SQLException;
    public void deleteFacture(int id) throws SQLException;
}
