/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Genre;
import Model.Livre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Lenovo
 */
public interface GenreInterface {
    public HashSet<Genre> getGenresByLivre(Livre livre) throws SQLException;
    public Genre getGenreById(int id) throws SQLException;
    public ArrayList<Genre> getGenres() throws SQLException;
    public void addGenre(Genre g) throws SQLException;
    
}
