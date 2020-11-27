/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Commande;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface CommandeInterface {
    public void addCommande(Commande c , int quantite ,int livre_id) throws SQLException;
     public Commande getCommandeMaxid() throws SQLException;
     public ArrayList<Commande> lister_Commandes() throws SQLException;
     public void updateCommande(Commande c) throws SQLException;
      public void deleteCommande (int id ) throws SQLException;

}
