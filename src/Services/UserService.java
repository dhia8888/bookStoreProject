/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.UserInterface;
import Model.Gerant;
import Model.Membre;
import Model.Proprietaire;
import Model.User;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class UserService  implements UserInterface {
    private Connection cnx;
	
	public UserService() {
		cnx = MyConnection.getInstance().getConnection();
	}
	
	public void addProprietaire(Proprietaire p) throws SQLException {
		
		String addUserRequest = "INSERT INTO user (nom,prenom,username,email,adresse,role,password,immatriculation) VALUES ('"
		+ p.getNom()+ "','" + p.getPrenom() +"','"+ p.getUsername() + "','" + p.getEmail()+ "','" + p.getAdresse() + "','" + "Admin" + "','" + p.getPassword() + "','" +
		p.getImmatriculation()+ "')";
		Statement stm = cnx.createStatement();
		stm.executeUpdate(addUserRequest, Statement.RETURN_GENERATED_KEYS);
	    ResultSet generatedKeys = stm.getGeneratedKeys();
	    generatedKeys.next();
	    p.setId(generatedKeys.getInt(1));
		
		}
	
	
	public void addMember(Membre m) throws SQLException {
	ArrayList<User> users = getUsers();
	if (!users.contains(m)) {
	String addUserRequest = "INSERT INTO user (nom, prenom, username, password ,email, adresse, role, numinscrit) VALUES ('" + m.getNom()+ "','" + m.getPrenom() 
	+"','"+ m.getUsername() + "','" + m.getPassword()+ "','" + m.getEmail() + "','" + m.getAdresse() + "','" + "Membre" + "','" +
	m.getNuminscrit()+ "')";
	Statement stm = cnx.createStatement();
	stm.executeUpdate(addUserRequest, Statement.RETURN_GENERATED_KEYS);
    ResultSet generatedKeys = stm.getGeneratedKeys();
    generatedKeys.next();
    m.setId(generatedKeys.getInt(1));
	} else {
		System.out.println("Membre existe déjà");
	}
	}
	
	public void addGerant(Gerant g) throws SQLException {
	ArrayList<Gerant> gerants = getGerants();
	if (!gerants.contains(g)) {
	String addGerantRequest = "INSERT INTO user (nom, prenom, username, password ,email,adresse, role,rib, salaire, matricule) VALUES ('"
	+ g.getNom()+ "','" + g.getPrenom() + "','" + g.getUsername() + "','" + g.getPassword()+ "','" + g.getEmail() + "','" + g.getAdresse() + "','" + "Gerant" + "','" + g.getRib() + "','" +
	g.getSalaire()+ "','" + g.getMatricule() + "')";
	Statement stm = cnx.createStatement();
	stm.executeUpdate(addGerantRequest, Statement.RETURN_GENERATED_KEYS);
    ResultSet generatedKeys = stm.getGeneratedKeys();
    generatedKeys.next();
    g.setId(generatedKeys.getInt(1));
	} else {
		System.out.println("Gerant existe déjà");
	}
	}



	public ArrayList<User> getUsers() throws SQLException {
		ArrayList<User> users = new ArrayList<>();
        String getAllUsersRequest = "SELECT * FROM `user`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(getAllUsersRequest);

        while (rst.next()) {
            User u = new User();
            u.setId(rst.getInt(1));
            u.setNom(rst.getString(2));
            u.setPrenom(rst.getString(3));
            u.setUsername(rst.getString(4));
            u.setEmail(rst.getString(5));
            u.setRole(rst.getString(6));
  
            users.add(u);
        }
        return users;
	}
	
	public ArrayList<Membre> getMembers() throws SQLException {
		ArrayList<Membre> membres = new ArrayList<>();
        String getAllmembersRequest = "SELECT * FROM `user` WHERE role = 'Membre' ";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(getAllmembersRequest);

        while (rst.next()) {
            Membre m = new Membre();
            m.setId(rst.getInt(1));
            m.setNom(rst.getString(2));
            m.setPrenom(rst.getString(3));
            m.setUsername(rst.getString(4));
            m.setPassword(rst.getString(5));
            m.setEmail(rst.getString(6));
            m.setAdresse(rst.getString(7));
            m.setRole(rst.getString(8));
            m.setNuminscrit(rst.getInt(9));
  
            membres.add(m);
        }
        return membres;
	} 
        public ArrayList<Gerant> getGerants() throws SQLException {
    		ArrayList<Gerant> gerants = new ArrayList<>();
            String getAllGerantsRequest = "SELECT * FROM `user` WHERE role = 'Gerant' ";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(getAllGerantsRequest);

            while (rst.next()) {
                Gerant g = new Gerant();
                g.setId(rst.getInt(1));
                g.setNom(rst.getString(2));
                g.setPrenom(rst.getString(3));
                g.setUsername(rst.getString(4));
                g.setEmail(rst.getString(5));
                g.setRole(rst.getString(6));
                g.setPassword(rst.getString(7));
                g.setRib(rst.getString(8));
                g.setMatricule(rst.getString(9));
                g.setSalaire(rst.getInt(10));

      
                gerants.add(g);
            }
            return gerants;
	}
        
        public void deleteUser(int id) throws SQLException {
            String requestDeleteUser = "DELETE FROM `user` WHERE id =" + id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(requestDeleteUser);
        }
        
        public void modifierUser(User user) throws SQLException {
            String request = "UPDATE `user` SET `username`=?"
                    + "WHERE `id` = ?";
            PreparedStatement pst = cnx.prepareStatement(request);

            pst.setString(1, user.getUsername());
            pst.setInt(2,user.getId() );
            pst.executeUpdate();
        }
        
        /*public void updateGerant(Gerant gerant) throws SQLException {
        	
        	
        }

        UPDATE `user` SET `numinscrit` = '705047' WHERE `user`.`id` = 4;*/
    
}
