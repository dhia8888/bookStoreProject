/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.GenreInterface;
import Model.Genre;
import Model.Livre;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Lenovo
 */
public class GenreService implements GenreInterface{
    
    private Connection cnx;

    public GenreService() {
        cnx = MyConnection.getInstance().getConnection();
    }
    public HashSet<Genre> getGenresByLivre(Livre livre) throws SQLException {
        String request2 = "SELECT * FROM `genre_livre` WHERE livre_id=" + livre.getId_livre();
        Statement stm2 = cnx.createStatement();
        ResultSet rst2 = stm2.executeQuery(request2);
        HashSet<Genre> genres = new HashSet<Genre>();
        while (rst2.next()) {
            Genre g = getGenreById(rst2.getInt(2));
            genres.add(g);
        }
        return genres;
    }
    public Genre getGenreById(int id) throws SQLException {
        String request2 = "SELECT * FROM `genre` WHERE id =" + id;
        Statement stm2 = cnx.createStatement();
        ResultSet rst2 = stm2.executeQuery(request2);

        Genre g = new Genre();
        if (rst2.next()) {

            g.setId_genre(rst2.getInt(1));
            g.setNom_genre(rst2.getString(2));

        }
        return g;
    }
    
     public void addGenre(Genre g) throws SQLException {
        ArrayList<Genre> genres = getGenres();
        if (!genres.contains(g)) {
            String request = "INSERT INTO `genre`(`nom_genre`) VALUES ('" + g.getNom_genre() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(request, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            g.setId_genre(generatedKeys.getInt(1));
        } else {
            for (Genre gnr : genres) {
                if (gnr.equals(g)) {
                    g.setId_genre(gnr.getId_genre());

                }
            }
        }
    }
     public ArrayList<Genre> getGenres() throws SQLException {
        String request2 = "SELECT * FROM `genre`";
        Statement stm2 = cnx.createStatement();
        ResultSet rst2 = stm2.executeQuery(request2);
        ArrayList<Genre> genres = new ArrayList<Genre>();
        while (rst2.next()) {
            Genre g = new Genre();
            g.setId_genre(rst2.getInt(1));
            g.setNom_genre(rst2.getString(2));
            genres.add(g);
        }
        return genres;
    }
    
}
