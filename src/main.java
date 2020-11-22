
import BookStore.utils.MyConnection;
import Model.Auteur;
import Model.Commande;
import Model.Edition;
import Model.Facture;
import Model.Genre;
import Model.Livre;
import Model.Panier;
import Services.CommandeService;
import Services.FactureService;
import Services.LivreServices;
import Services.PanierService;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

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


        CommandeService ser_com = new CommandeService();
        Commande c =new Commande(3, "22/11/2020", 0);
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




       /*
        try
        {
            ligne_commande ligne_commande = new ligne_commande(1, 1, 23);

            service_ligne_commande service_ligne_commande = new service_ligne_commande();

            service_ligne_commande.add(ligne_commande);

        }catch (Exception e)
        {
            System.out.println("Exception");
        }

        */
        //  Panier p = new Panier(2, 34f, 2, 1);
        //  service_panier service_panier = new service_panier();
        //  service_panier.addPanier(p);

        /*
        try {

            Facture facture = new Facture("12/12/1234",344f,2);

            service_Facture service_facture = new service_Facture();

          //  service_facture.addFacture(facture);


            service_facture.deleteFacture(2);

        }catch (Exception e)
        {

            System.out.println("Exception");
        }*/
    }
}

