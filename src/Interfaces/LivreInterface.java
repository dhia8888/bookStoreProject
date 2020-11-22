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
import java.util.HashSet;

/**
 *
 * @author aymen
 */
public interface LivreInterface {

    public ArrayList<Livre> getLivres() throws SQLException;

    public Livre getLivreById(int id) throws SQLException;

    public ArrayList<Livre> getLivreByGenre(Genre genre) throws SQLException;

    public ArrayList<Livre> getLivreByAuteur(Auteur auteur) throws SQLException;

    public ArrayList<Livre> getLivreByEdition(Edition edition) throws SQLException;

    public void ajouterLivre(Livre livre) throws SQLException;

    public void modifierLivre(Livre livre) throws SQLException;

    public void suprimerLivre(Livre livre) throws SQLException;

    public Edition getEditionByLivre(int id) throws SQLException;

    public HashSet<Genre> getGenresByLivre(Livre livre) throws SQLException;

    public HashSet<Auteur> getAuteursByLivre(Livre livre) throws SQLException;

    public ArrayList<Edition> getEditions() throws SQLException;

    public ArrayList<Auteur> getAuteurs() throws SQLException;

    public ArrayList<Genre> getGenres() throws SQLException;

    public void addAuteur(Auteur a) throws SQLException;

    public void addGenre(Genre g) throws SQLException;

    public void addEdition(Edition e) throws SQLException;
}
