/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.SQLException;

/**
 *
 * @author AYOUB
 */
public interface StatistiqueInterface {
    public int getNbrUser() throws SQLException ;
    public int getNbrBook() throws SQLException;
    public int getNbrAuteur() throws SQLException;
    public int getNbrEdition() throws SQLException ;
    public int getNbrCommande() throws SQLException ;
}
