/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.LivreInterface;
import Model.Auteur;
import Model.Edition;
import Model.Genre;
import Model.Livre;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author aymen
 */
public class LivreServices implements LivreInterface {

    private Connection cnx;
    private AuteurService auteurservice;
    private GenreService genreservice;
    private EditionService editionservice;

    public LivreServices() {
        cnx = MyConnection.getInstance().getConnection();
        editionservice = new EditionService();
        genreservice = new GenreService();
        auteurservice = new AuteurService();
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

    public ArrayList<Livre> getLivres() throws SQLException {
        ArrayList<Livre> livres = new ArrayList<>();
        String request = "SELECT * FROM `livre`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Livre l = new Livre();
            l.setId_livre(rst.getInt("id"));
            //get edition of book
            l.setEditeur(editionservice.getEditionByLivre(rst.getInt(2)));

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
            l.setImage(rst.getString(10));
            //get GENRES of book
            l.setGenres(genreservice.getGenresByLivre(l));

            //get AUTEUR of book
            l.setAuteurs(auteurservice.getAuteursByLivre(l));

            livres.add(l);
        }

        return livres;
    }
    public Livre getLivreById(int id) throws SQLException {
        Livre l = null;
        String request = "SELECT * FROM `livre` Where   and id =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        if (rst.next()) {
            l = new Livre();
            l.setId_livre(rst.getInt("id"));
            //get edition of book
            l.setEditeur(editionservice.getEditionByLivre(rst.getInt(2)));
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
            l.setImage(rst.getString(10));
            //get GENRES of book
            l.setGenres(genreservice.getGenresByLivre(l));
            //get AUTEUR of book
            l.setAuteurs(auteurservice.getAuteursByLivre(l));
        }

        return l;
    }

    public ArrayList<Livre> getLivresByGenre(Genre genre) throws SQLException {
        ArrayList<Livre> livres = new ArrayList<>();
        String request2 = "SELECT * FROM `genre_livre` WHERE genre_id=" + genre.getId_genre();
        Statement stm2 = cnx.createStatement();
        ResultSet rst2 = stm2.executeQuery(request2);
        while (rst2.next()) {
            livres.add(getLivreById(rst2.getInt(2)));
        }
        return livres;
    }

    public ArrayList<Livre> getLivresByAuteur(Auteur auteur) throws SQLException {
        ArrayList<Livre> livres = new ArrayList<>();
        String request2 = "SELECT * FROM `livre_auteur` WHERE id_auteur=" + auteur.getId_auteur();
        Statement stm2 = cnx.createStatement();
        ResultSet rst2 = stm2.executeQuery(request2);
        while (rst2.next()) {
            livres.add(getLivreById(rst2.getInt(2)));
        }
        return livres;
    }

    public ArrayList<Livre> getLivresByEdition(Edition edition) throws SQLException {
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

    public void addLivre(Livre livre) throws SQLException {

        ArrayList<Livre> livres = new ArrayList<>();
        livres = getLivres();

        if (!livres.contains(livre)) {
            for (Genre gnr : livre.getGenres()) {
                genreservice.addGenre(gnr);
            }
            for (Auteur aut : livre.getAuteurs()) {
                auteurservice.addAuteur(aut);
            }
            Edition edt = livre.getEditeur();
            editionservice.addEdition(edt);
            String request = "INSERT INTO `livre`(`edition_id`, `titre_livre`, `langue`, `description`, `nb_pages`, `annee`, `prix`, `nbr_exemplaires`, `image`) VALUES ('" + livre.getEditeur().getId_editeur() + "','" + livre.getTitre_livre() + "','" + livre.getLangue() + "','"
                    + livre.getDescription() + "','" + livre.getNb_pages() + "','" + livre.getAnnee() + "','" + livre.getPrix() + "','" + livre.getNb_exemplaires() + "','" + livre.getImage() + "')";
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

    public void updateLivre(Livre livre) throws SQLException {
        String request = "UPDATE `livre` SET `nbr_exemplaires`=?"
                + "WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setInt(1, livre.getNb_exemplaires());
        pst.setInt(2, livre.getId_livre());
        pst.executeUpdate();
    }

    public void updateLivreAttributs(Livre livre) throws SQLException {
        String request = "UPDATE `livre` SET `titre_livre`= ?,`langue`=?,`description`=?,`nb_pages`=?,`annee`=?,`prix`=?,`nbr_exemplaires`=?,`image`=? WHERE id = ?";

        PreparedStatement pst = cnx.prepareStatement(request);
        pst.setString(1, livre.getTitre_livre());
        pst.setString(2, livre.getLangue());
        pst.setString(3, livre.getDescription());
        pst.setInt(4, livre.getNb_pages());
        pst.setString(5, livre.getAnnee());
        pst.setDouble(6,livre.getPrix());
        pst.setInt(7, livre.getNb_exemplaires());
        if (livre.getImage()==null) pst.setString(8, " ");
       else pst.setString(8, livre.getImage());
        pst.setInt(9, livre.getId_livre());
        
        pst.executeUpdate();
    }
    public void deleteLivre(Livre livre) throws SQLException {
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
    public float prixlivre (String titre) throws SQLException
    {
        PreparedStatement stm = cnx.prepareStatement("SELECT prix FROM `livre` WHERE livre.titre_livre=?");
        stm.setString(1,titre);
        ResultSet rst= stm.executeQuery();
        ArrayList<String> liste = new ArrayList<>();
        while (rst.next())
        {
            liste.add(rst.getString(1));
        }
        return Float.parseFloat(liste.get(0));
    }
    public int idlivre (String titre) throws SQLException
    {
        PreparedStatement stm = cnx.prepareStatement("SELECT id FROM `livre` WHERE livre.titre_livre=?");
        stm.setString(1,titre);
        ResultSet rst= stm.executeQuery();
        ArrayList<String> liste = new ArrayList<>();
        while (rst.next())
        {
            liste.add(rst.getString(1));
        }
        return Integer.parseInt(liste.get(0));
    }



    public int Nbexemplivre (String titre) throws SQLException
    {
        PreparedStatement stm = cnx.prepareStatement("SELECT livre.nbr_exemplaires FROM `livre` WHERE livre.titre_livre=?");
        stm.setString(1,titre);
        ResultSet rst= stm.executeQuery();
        ArrayList<String> liste = new ArrayList<>();
        while (rst.next())
        {
            liste.add(rst.getString(1));
        }
        return Integer.parseInt(liste.get(0));
    }

}
