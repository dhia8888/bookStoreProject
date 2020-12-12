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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	
	public void addGerant(Gerant g) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException  {		 
		 
	ObservableList<Gerant> gerants = getGerants();
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
            u.setPassword(rst.getString(5));
            u.setEmail(rst.getString(6));
            u.setRole(rst.getString(7));
            
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
        public ObservableList<Gerant> getGerants() throws SQLException {
    		ObservableList<Gerant> gerants = FXCollections.observableArrayList();
    		try {
            String getAllGerantsRequest = "SELECT * FROM `user` WHERE role = 'Gerant' ";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(getAllGerantsRequest);

            //Il r�cup�re tous les champs de la table User
            while (rst.next()) {
                Gerant g = new Gerant();
                g.setId(rst.getInt("id"));
                g.setNom(rst.getString("nom"));
                g.setPrenom(rst.getString("prenom"));
                g.setUsername(rst.getString("username"));
                g.setPassword(rst.getString("password"));
                g.setEmail(rst.getString("email"));
                g.setAdresse(rst.getString("adresse"));
                g.setRole(rst.getString("role"));
                g.setRib(rst.getString("rib"));
                g.setSalaire(rst.getDouble("salaire"));
                g.setMatricule(rst.getString("matricule"));
                
                gerants.add(g);
            }
    		} catch (Exception ex) {
                Logger.getLogger(Gerant.class.getName()).log(Level.SEVERE, null, ex);
    		}
            return gerants;
        }
        public void deleteUser(User u) throws SQLException {
        	PreparedStatement ps;
            try {
                ps = cnx.prepareStatement("DELETE FROM `user` WHERE id = ?");

                ps.setInt(1, u.getId());
                ps.execute();
                
                System.out.println(u.getId());
            } catch (Exception e) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        public void modifierGerant(Gerant gerant) throws SQLException {
            String request = "UPDATE `user` SET `nom`=?,`prenom`=?,`username`=?,`password`=?,`email`=?,`adresse`=?,`rib`=?,`salaire`=?,`matricule`=? "
                    + "WHERE `id` = ?";
            PreparedStatement pst = cnx.prepareStatement(request);

            pst.setString(1, gerant.getNom());
            pst.setString(2, gerant.getPrenom());
            pst.setString(3, gerant.getUsername());
            pst.setString(4, gerant.getPassword());
            pst.setString(5, gerant.getEmail());
            pst.setString(6, gerant.getAdresse());
            pst.setString(7, gerant.getRib());
            pst.setDouble(8, gerant.getSalaire());
            pst.setString(9, gerant.getMatricule());
            pst.setInt(10, gerant.getId());
            
            pst.executeUpdate();
        }
        
        /*public void updateGerant(Gerant gerant) throws SQLException {
        	
        	
        }

        UPDATE `user` SET `numinscrit` = '705047' WHERE `user`.`id` = 4;*/
    
        public boolean Login(String email, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
       
                // Step 2:Create a statement using connection object
        		String loginQuery ="SELECT * FROM user WHERE email =? AND password =?";
		
                PreparedStatement preparedStatement = cnx.prepareStatement(loginQuery) ;
                System.out.println(preparedStatement);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(resultSet);
                if (resultSet.next()) {
                    return true;
                }
            return false;
        }
        
        public String getRole (String email) throws SQLException {
        	String loginQuery ="SELECT role FROM `user` WHERE email=?";
    		String pw = "";
    		PreparedStatement preparedStatement = cnx.prepareStatement(loginQuery) ;
            System.out.println(preparedStatement);
            preparedStatement.setString(1, email);  
            ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
        	   pw = resultSet.getString(1);
           }
        	return pw;
        }
        
        public boolean UserExist(String email)throws SQLException {
            
            // Step 2:Create a statement using connection object
    		String loginQuery ="SELECT * FROM user WHERE email =?";
    		
            PreparedStatement preparedStatement = cnx.prepareStatement(loginQuery) ;
            System.out.println(preparedStatement);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            if (resultSet.next()) {
                return true;
            }
        return false;
    }
        
        public void resetPassword (String email) throws SQLException {
        	
        	String loginQuery ="SELECT password FROM `user` WHERE email=?";
    		ArrayList<String> pw = new ArrayList<String>();
        	
            PreparedStatement preparedStatement = cnx.prepareStatement(loginQuery) ;
            System.out.println(preparedStatement);
            preparedStatement.setString(1, email);  
            ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
        	   pw.add(resultSet.getString(1));
           }
            System.out.println(" rst " + pw );  
            
           String host="smtp.gmail.com";  
      	  final String user="mohamedamine.dhaouadi@esprit.tn";//change accordingly  
      	  final String password="203JMT0678";//change accordingly  
      	    
      	  String to=email;//change accordingly  
      	  
      	   //Get the session object  
      	   Properties properties = new Properties();
      	   properties.put("mail.smtp.host", host);
      	   properties.put("mail.smtp.port", 587);
      	   properties.put("mail.smtp.auth", true);
      	   properties.put("mail.smtp.starttls.enable",true);
      	   properties.put("mail.smtp.username", user);
      	   properties.put("mail.smtp.password", password);
      	   properties.put("mail.smtp.setTLS", true);
      	   properties.put("mail.smtp.ssl", true);
 
      	   Session session = Session.getDefaultInstance(properties,  
      	    new javax.mail.Authenticator() {  
      	      protected PasswordAuthentication getPasswordAuthentication() {  
      	    return new PasswordAuthentication(user,password);  
      	      }  
      	    });  
      	  
      	   //Compose the message  
      	    try {  
      	     MimeMessage message = new MimeMessage(session);  
      	     message.setFrom(new InternetAddress(user));  
      	     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
      	     message.setSubject("Password Reset");  
      	     message.setText("Hello dear Manager, \n \n Your password is :\n"+ pw);  
      	       
      	    //send the message  
      	     Transport.send(message);  
      	  
      	     System.out.println("message sent successfully..." );  
      	   
      	     } catch (MessagingException e) {e.printStackTrace();}  
      	 }  
         

        }
        


