/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Model.Commande;
import Model.Facture;
import Model.Livre;

import utils.MyConnection;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatistiquesServiceStock
{
        private Connection cnx;

        public StatistiquesServiceStock()
        {
            cnx = MyConnection.getInstance().getConnection();

        }

    public int getNbrLivreAction() throws SQLException {
        String request = "SELECT COUNT(livre_id) FROM genre_livre where genre_id=3";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        rst.next();
        return rst.getInt(1);
    }
    public int getNbrLivreRomance() throws SQLException {
        String request = "SELECT COUNT(livre_id) FROM genre_livre where genre_id=2";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        rst.next();
        return rst.getInt(1);
    }
    public int getNbrLivreDrama() throws SQLException {
        String request = "SELECT COUNT(livre_id) FROM genre_livre where genre_id=4";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        rst.next();
        return rst.getInt(1);
    }
    public int getNbrLivreHorror() throws SQLException {
        String request = "SELECT COUNT(livre_id) FROM genre_livre where genre_id=5";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        rst.next();
        return rst.getInt(1);
    }
        public int getNbrUser() throws SQLException {
            String request = "SELECT COUNT(*) FROM `user`";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(request);
            rst.next();
            return rst.getInt(1);
        }

        public int getNbrBook() throws SQLException {
            String request = "SELECT COUNT(*) FROM `livre`";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(request);
            rst.next();
            return rst.getInt(1);
        }

        public int getNbrAuteur() throws SQLException {
            String request = "SELECT COUNT(*) FROM `auteur`";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(request);
            rst.next();
            return rst.getInt(1);
        }

        public int getNbrEdition() throws SQLException {
            String request = "SELECT COUNT(*) FROM `edition`";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(request);
            rst.next();
            return rst.getInt(1);
        }

        public int getNbrCommande() throws SQLException {
            String request = "SELECT COUNT(*) FROM `commande`";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(request);
            rst.next();
            return rst.getInt(1);
        }
        
        public ArrayList<Livre> getLivresInfo() throws SQLException {
        ArrayList<Livre> livres = new ArrayList<>();
        String request = "SELECT id ,titre_livre, langue , nb_pages, annee, prix ,nbr_exemplaires FROM livre";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        while (rst.next()) {
            Livre l = new Livre();
            l.setId_livre(rst.getInt("id"));
            l.setTitre_livre(rst.getString("titre_livre"));
            l.setLangue(rst.getString("langue"));
            l.setNb_pages(rst.getInt("nb_pages"));
            l.setAnnee(rst.getString("annee"));
            l.setPrix(rst.getDouble("prix"));
            l.setNb_exemplaires(rst.getInt("nbr_exemplaires"));
            livres.add(l);
        }

        return livres;
    }
        public ArrayList<Commande> getComInfo() throws SQLException {
        ArrayList<Commande> commande = new ArrayList<>();
        String request = "SELECT id, date_com, livraison ,client_id FROM commande";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        while (rst.next()) {
            Commande c = new Commande();
            c.setId_com(rst.getInt("id"));
            c.setDate_com(rst.getString("date_com"));
            c.setLivraison(rst.getInt("livraison"));
            c.setId_client(rst.getInt("client_id"));
            
            commande.add(c);
        }

        return commande;
    }
    public static ArrayList<String> getLivreEditionInfo() throws SQLException
    {   Connection cnx = MyConnection.getInstance().getConnection();
        int sum = 0;
        Map<Integer, String> stat = new HashMap<Integer, String>();
        PreparedStatement stm = cnx.prepareStatement("SELECT nom_edition FROM `livre` , `edition` WHERE livre.edition_id=edition.id;");
        ResultSet rst= stm.executeQuery();
        ArrayList<String> liste = new ArrayList<>();
        while (rst.next())
        {
            liste.add(rst.getString(1));
        }
        return  liste;
    }
    public static ArrayList<String> getLivreAuteurInfo() throws SQLException
    {
        Connection cnx = MyConnection.getInstance().getConnection();

        Map<Integer, String> stat = new HashMap<Integer, String>();
        PreparedStatement stm = cnx.prepareStatement("SELECT auteur.prenom from facture " +
                "INNER JOIN ligne_commande on ligne_commande.commande_id=facture.commande_id INNER JOIN livre_auteur on ligne_commande.livre_id=livre_auteur.id_livre " +
                "INNER JOIN auteur on auteur.id=livre_auteur.id_auteur;");
        ResultSet rst= stm.executeQuery();
        ArrayList<String> liste = new ArrayList<>();
        while (rst.next())
        {
            liste.add(rst.getString(1));

        }
        return  liste;
    }


    }



