import Model.*;
import Services.*;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.sun.jndi.toolkit.url.Uri;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.chart.XYChart;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;
import utils.EmailUtil;
import utils.MyConnection;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;



public class main {

    public static void main(String[] args) {

//       Livre l = new Livre("You", "Anglais","iot",102, 34, "2020", 180.0);
//       //l.setId_livre(1);
//       Auteur a = new Auteur("Ben Gaid Hassine", "mohamed", "young author", "04/10/1998");
//       HashSet<Auteur> auts = new HashSet<>();
//        auts.add(a);
//        Genre g = new Genre("Action");
//        Genre g1 = new Genre("Romance");
//        HashSet<Genre> gns = new HashSet<>();
//        gns.add(g);
//        gns.add(g1);
//        Edition e = new Edition("Tunisie", "Charguia");
//        l.setAuteurs(auts);
//        l.setGenres(gns);
//        l.setEditeur(e);

//         Livre l = new Livre("Blade", "Anglais","iot",152, 14, "2020", 190.0);
//       //l.setId_livre(1);
//       Auteur a = new Auteur("Kefi", "Aymen", "Big author", "10/06/1997");
//       HashSet<Auteur> auts = new HashSet<>();
//        auts.add(a);
//        Genre g = new Genre("Drama");
//        Genre g1 = new Genre("Horror");
//        HashSet<Genre> gns = new HashSet<>();
//        gns.add(g);
//        gns.add(g1);
//        Edition e = new Edition("Tunisie", "Bardo");
//        l.setAuteurs(auts);
//        l.setGenres(gns);
//        l.setEditeur(e);
//
//          LivreServices ls = new LivreServices();
//        try {
//            ls.ajouterLivre(l);
//        } catch (SQLException exc) {
//            System.out.println(exc.getMessage());
//        }
//        try {
//            ls.suprimerLivre(l);
//        } catch (SQLException exc1) {
//             System.out.println(exc1.getMessage());
//        }


//        CommandeService ser_com = new CommandeService();
//        Commande c =new Commande(3, "22/11/2020", 0);
//        try{
//            ser_com.addCommande(c,2,2);
//        }
//        catch(Exception e){
//            System.out.println("Exception");
//        }

//        try{
//            ser_com.deleteCommande(16);
//        }
//        catch(Exception e){
//            System.out.println("Exception");
//        }
// 
//          Panier p = new Panier(7, 347.00f, 15, 3);
//          PanierService service_panier = new PanierService();
//          try
//        {
//          service_panier.addPanier(p);
//
//          //  service_panier.deletePanier(1);
//        }
//          catch (Exception e)
//        {
//            System.out.println("Exception");
//        }

//          FactureService Facs = new FactureService();
//          
//          Facture f = new Facture("22/11/2021", 130.0f, 15); 
//          try{
//              Facs.addFacture(f);
//          }
//          catch(Exception e){
//              System.out.println("Exception");
//          }


//        try {
//            Notification n =new Notification("Nouvelle commande", "Une nouvelle commande a été ajoutée",0);
//           
        //sn.addNotification(n);
//            System.out.println(sn.getNotifications());

////           sn.addNotification(new Notification ("Nouveau gérant", "Un nouveau gérant a été ajouté",0));
//                  sn.deleteNotification(5);
//            sn.deleteNotification(4);
//            System.out.println(sn.getNotifications());
//            Notification n = new Notification("Nouvelle facture", "Une nouvelle  facture a été ajoutée",0);
//            sn.addNotification(n);
//            System.out.println(sn.getNotifications());
        // n.setVu(1);
        // n.setId_notif(7);
        //   sn.updateNotification(n);

//             System.out.println(fn.getFeedbacksByUser(1));
        // System.out.println(sn.getNotificationWithNoView());
        // System.out.println(fn.getFeedbacks());
        // System.out.println(fn.getFeedbacksByUser(3));


//        } catch (SQLException e) {
//            System.out.println("Exception");
//    }

        //  UserService us = new UserService();


//       try {   	  
//    	   p1.setId(6);
//    	   us.modifierUser(p1);
//       } catch (SQLException exc) {
//	       System.out.println(exc.getMessage());
//	   }


//       try {
//        	us.addProprietaire(p2);
//                
//        } catch (SQLException exc) {
//            System.out.println(exc.getMessage());
//        }
       
       /*try {
       	us.addGerant(g1);
       } catch (SQLException exc) {
           System.out.println(exc.getMessage());
       }*/
       
       /*try {
       	us.addMember(m1);
       } catch (SQLException exc) {
           System.out.println(exc.getMessage());
     /*  }
       
       try {
        	System.out.println(us.getMembers());
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
       try {
       	System.out.println(us.getGerants());
       } catch (SQLException exc) {
           System.out.println(exc.getMessage());
       }
       

        ArrayList<String> arrayList = new ArrayList<>();
        PanierService panierService = new PanierService();
        try
        {
            arrayList = panierService.getPanierByUser(3);
            for(String a : arrayList)
            {
                System.out.println(a.toString());
            }

        }catch (Exception e)
        {
            System.out.println(" problem");
        }


        /*


        NexmoClient client = new NexmoClient.Builder()
                .apiKey("6b51eab7")
                .apiSecret("BZRt4mITEzHld7hQ")
                .build();

        String messageText = "SMS API";
        TextMessage message = new TextMessage("Vonage APIs", "21655960870", messageText);

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
            System.out.println(responseMessage);
        }

*/


        try {


        }catch (Exception e)
        {
            System.out.println("------------");
        }


    }
}






