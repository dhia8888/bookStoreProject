/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BookStore.utils.MyConnection;
import Interfaces.LivreInterface;
import Model.Auteur;
import Model.Edition;
import Model.Genre;
import Model.Livre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author aymen
 */
public class LivreServices implements LivreInterface {

    private Connection cnx;

    public LivreServices() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public ArrayList<Edition> getEditions() throws SQLException {
        ArrayList<Edition> editions = new ArrayList<>();
        String request1 = "SELECT * FROM `edition`";
        Statement stm1 = cnx.createStatement();
        ResultSet rst1 = stm1.executeQuery(request1);
        while (rst1.next()) {
            Edition e = new Edition();
            e.setId_editeur(rst1.getInt("id"));
            e.setAdresse(rst1.getString(2));
            e.setPays(rst1.getString(3));
            editions.add(e);
        }
        return editions;
    }

    public ArrayList<Auteur> getAuteurs() throws SQLException {
        ArrayList<Auteur> auteurs = new ArrayList<>();
        String request3 = "SELECT * FROM `auteur`";
        Statement stm3 = cnx.createStatement();
        ResultSet rst = stm3.executeQuery(request3);

        while (rst.next()) {
            Auteur a = new Auteur();
            a.setId_auteur(rst.getInt(1));
            a.setNom(rst.getString(2));
            a.setPrenom(rst.getString(3));
            a.setBio(rst.getString(4));
            a.setDate_naissance(rst.getString(5));
            auteurs.add(a);
        }
        return auteurs;
    }

    public Auteur getAuteurById(int id) throws SQLException {
        Auteur a = new Auteur();
        String request3 = "SELECT * FROM `auteur` WHERE id =" + id;
        Statement stm3 = cnx.createStatement();
        ResultSet rst = stm3.executeQuery(request3);

        if (rst.next()) {

            a.setId_auteur(rst.getInt(1));
            a.setNom(rst.getString(2));
            a.setPrenom(rst.getString(3));
            a.setBio(rst.getString(4));
            a.setDate_naissance(rst.getString(5));

        }
        return a;
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

    public void addAuteur(Auteur a) throws SQLException {
        ArrayList<Auteur> auteurs = getAuteurs();
        if (!auteurs.contains(a)) {
            String request = "INSERT INTO `auteur`(`nom`, `prenom`, `bio`, `date_naissance`) VALUES ('"
                    + a.getNom() + "','" + a.getPrenom() + "','" + a.getBio() + "','" + a.getDate_naissance() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(request, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            a.setId_auteur(generatedKeys.getInt(1));
        } else {
            for (Auteur aut : auteurs) {
                if (aut.equals(a)) {
   
                    a.setId_auteur(aut.getId_auteur());
                }
            }
        }
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

    public void addEdition(Edition e) throws SQLException {
        ArrayList<Edition> editions = getEditions();
        if (!editions.contains(e)) {
            String request = "INSERT INTO `edition`(`adresse`, `pays`) VALUES ('" + e.getAdresse() + "','" + e.getPays() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(request, Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            e.setId_editeur(generatedKeys.getInt(1));
        } else {
            for (Edition edt : editions) {
                if (edt.equals(e)) {
                    e.setId_editeur(edt.getId_editeur());
                    
                    
                }
            }
        }
    }

    public void linkLivreGenre(Livre l, Genre g) throws SQLException {
        String request = "INSERT INTO `genre_livre`(`genre_id`, `livre_id`) VALUES ('" + g.getId_genre() + "','" + l.getId_livre() + "')";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);

    }

    public void linkLivreAuteur(Livre l, Auteur a) throws SQLException {
        String request = "INSERT INTO `livre_auteur`(`id_auteur`, `id_livre`) VALUES ('" + a.getId_auteur() + "','" + l.getId_livre() + "')";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public Edition getEditionByLivre(int id) throws SQLException {
        Edition e = null;
        String request1 = "SELECT * FROM `edition` WHERE id=" + id;
        Statement stm1 = cnx.createStatement();
        ResultSet rst1 = stm1.executeQuery(request1);
        if (rst1.next()) {
            e = new Edition();
            e.setId_editeur(rst1.getInt("id"));
            e.setAdresse(rst1.getString(2));
            e.setPays(rst1.getString(3));
        }
        return e;
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

    public HashSet<Auteur> getAuteursByLivre(Livre livre) throws SQLException {
        String request3 = "SELECT * FROM `livre_auteur` WHERE id_livre=" + livre.getId_livre();
        Statement stm3 = cnx.createStatement();
        ResultSet rst = stm3.executeQuery(request3);
        HashSet<Auteur> auteurs = new HashSet<Auteur>();
        
        while (rst.next()) {
        Auteur a = getAuteurById(rst.getInt(1));
        
        auteurs.add(a);
        }
        return auteurs;
    }

    public ArrayList<Livre> getLivres() throws SQLException {
        ArrayList<Livre> livres = new ArrayList<>();
        String request = "SELECT * FROM `livre`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        while (rst.next()) {
            Livre l = new Livre();
            l.setId_livre(rst.getInt("id"));
            //get edition of book
            l.setEditeur(getEditionByLivre(rst.getInt(2)));
            
            /**
             * ******************************
             */
            l.setTitre_livre(rst.getString(3));
            l.setLangue(rst.getString(4));
            l.setDescription(rst.getString(5));
            l.setNb_pages(rst.getInt(6));
            l.setAnnee(rst.getString(7));
            l.setPrix(rst.getDouble(8));
            l.setNb_exemplaires(rst.getInt(9));
            //get GENRES of book
            l.setGenres(getGenresByLivre(l));
          
            //get AUTEUR of book
            l.setAuteurs(getAuteursByLivre(l));
         
            livres.add(l);
        }
    
        return livres;
    }

    public Livre getLivreById(int id) throws SQLException {
        Livre l = null;
        String request = "SELECT * FROM `livre` Where id =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        if (rst.next()) {
            l = new Livre();
            l.setId_livre(rst.getInt("id"));
            //get edition of book
            l.setEditeur(getEditionByLivre(rst.getInt(2)));
            /**
             * ******************************
             */
            l.setTitre_livre(rst.getString(3));
            l.setLangue(rst.getString(4));
            l.setDescription(rst.getString(5));
            l.setNb_pages(rst.getInt(6));
            l.setAnnee(rst.getString(7));
            l.setPrix(rst.getDouble(8));
            l.setNb_exemplaires(rst.getInt(9));
            //get GENRES of book
            l.setGenres(getGenresByLivre(l));
            //get AUTEUR of book
            l.setAuteurs(getAuteursByLivre(l));
        }

        return l;
    }

    public ArrayList<Livre> getLivreByGenre(Genre genre) throws SQLException {
        ArrayList<Livre> livres = new ArrayList<>();
        String request2 = "SELECT * FROM `genre_livre` WHERE genre_id=" + genre.getId_genre();
        Statement stm2 = cnx.createStatement();
        ResultSet rst2 = stm2.executeQuery(request2);
        while (rst2.next()) {
            livres.add(getLivreById(rst2.getInt(2)));
        }
        return livres;
    }

    public ArrayList<Livre> getLivreByAuteur(Auteur auteur) throws SQLException {
        ArrayList<Livre> livres = new ArrayList<>();
        String request2 = "SELECT * FROM `livre_auteur` WHERE id_auteur=" + auteur.getId_auteur();
        Statement stm2 = cnx.createStatement();
        ResultSet rst2 = stm2.executeQuery(request2);
        while (rst2.next()) {
            livres.add(getLivreById(rst2.getInt(2)));
        }
        return livres;
    }

    public ArrayList<Livre> getLivreByEdition(Edition edition) throws SQLException {
        ArrayList<Livre> livres = new ArrayList<>();
        String request2 = "SELECT * FROM `livre` WHERE edition_id=" + edition.getId_editeur();
        Statement stm2 = cnx.createStatement();
        ResultSet rst2 = stm2.executeQuery(request2);
        while (rst2.next()) {
            Livre l = new Livre();
            l = getLivreById(rst2.getInt(1));
            livres.add(l);
        }
        return livres;
    }

    public void ajouterLivre(Livre livre) throws SQLException {
  
        ArrayList<Livre> livres = new ArrayList<>();
        livres = getLivres();

        if (!livres.contains(livre)) {
            for (Genre gnr : livre.getGenres()) {
                addGenre(gnr);
            }
            for (Auteur aut : livre.getAuteurs()) {
                addAuteur(aut);
            }
            Edition edt = livre.getEditeur();
            addEdition(edt);
            String request = "INSERT INTO `livre`(`edition_id`, `titre_livre`, `langue`, `description`, `nb_pages`, `annee`, `prix`, `nbr_exemplaires`) VALUES ('" + livre.getEditeur().getId_editeur() + "','" + livre.getTitre_livre() + "','" + livre.getLangue() + "','"
                    + livre.getDescription() + "','" + livre.getNb_pages() + "','" + livre.getAnnee() + "','" + livre.getPrix() + "','" + livre.getNb_exemplaires() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(request, Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            livre.setId_livre(generatedKeys.getInt(1));
            for (Genre gnr : livre.getGenres()) {
                linkLivreGenre(livre, gnr);
            }
            for (Auteur aut : livre.getAuteurs()) {
                linkLivreAuteur(livre, aut);
            }
        }

    }

    public void modifierLivre(Livre livre) throws SQLException {
        String request = "UPDATE `livre` SET `nbr_exemplaires`=?"
                + "WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setInt(1, livre.getNb_exemplaires());
        pst.setInt(2, livre.getId_livre());
        pst.executeUpdate();
    }

    public void suprimerLivre(Livre livre) throws SQLException {
        String request = "DELETE FROM `genre_livre` WHERE livre_id =" + livre.getId_livre();
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
        request = "DELETE FROM `livre_auteur` WHERE id_livre =" + livre.getId_livre();
        stm = cnx.createStatement();
        stm.executeUpdate(request);
        request = "DELETE FROM `livre` WHERE id =" + livre.getId_livre();
        stm = cnx.createStatement();
        stm.executeUpdate(request);
    }
}
