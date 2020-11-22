
import BookStore.utils.MyConnection;
import Model.Auteur;
import Model.Edition;
import Model.Genre;
import Model.Livre;
import Services.LivreServices;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class main {

    public static void main(String[] args) {
       
       Livre l = new Livre("You", "Anglais","iot",102, 34, "2020", 180.0);
       //l.setId_livre(1);
       Auteur a = new Auteur("Ben Gaid Hassine", "mohamed", "young author", "04/10/1998");
       HashSet<Auteur> auts = new HashSet<>();
        auts.add(a);
        Genre g = new Genre("Action");
        Genre g1 = new Genre("Romance");
        HashSet<Genre> gns = new HashSet<>();
        gns.add(g);
        gns.add(g1);
        Edition e = new Edition("Tunisie", "Charguia");
        l.setAuteurs(auts);
        l.setGenres(gns);
        l.setEditeur(e);
        
//         Livre l = new Livre("Blade", "Anglais","iot",102, 34, "2020", 180.0);
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
//        l1.setAuteurs(auts);
//        l1.setGenres(gns);
//        l1.setEditeur(e);

          LivreServices ls = new LivreServices();
        try {
            ls.ajouterLivre(l);
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
//        try {
//            ls.suprimerLivre(l);
//        } catch (SQLException exc1) {
//             System.out.println(exc1.getMessage());
//        }
    }
}
