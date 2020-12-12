/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Auteur;
import Model.Edition;
import Model.Genre;
import Model.Livre;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aymen
 */
public interface LivreInterface {

    public ArrayList<Livre> getLivres() throws SQLException;
    public Livre getLivreById(int id) throws SQLException;
    public ArrayList<Livre> getLivresByGenre(Genre genre) throws SQLException;
    public ArrayList<Livre> getLivresByAuteur(Auteur auteur) throws SQLException;
    public ArrayList<Livre> getLivresByEdition(Edition edition) throws SQLException;
    public void addLivre(Livre livre) throws SQLException;
    public void updateLivre(Livre livre) throws SQLException;
    public void deleteLivre(Livre livre) throws SQLException;
    public void linkLivreAuteur(Livre l, Auteur a) throws SQLException;
    public void linkLivreGenre(Livre l, Genre g) throws SQLException;
}
