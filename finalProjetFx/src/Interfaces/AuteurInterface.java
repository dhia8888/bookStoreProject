/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Model.Auteur;
import Model.Livre;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Lenovo
 */
public interface AuteurInterface {
    public ArrayList<Auteur> getAuteurs() throws SQLException;
    public Auteur getAuteurById(int id) throws SQLException;
    public void addAuteur(Auteur a) throws SQLException;
    public HashSet<Auteur> getAuteursByLivre(Livre livre) throws SQLException;
    
}
