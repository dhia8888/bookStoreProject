package Services;

import Interfaces.PanierInterface;
import Model.Panier;
import utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class PanierService implements PanierInterface{
    private Connection cnx;

    public PanierService()
    {
        cnx = MyConnection.getInstance().getConnection();

    }

    public void addPanier(Panier p) throws SQLException
    {
        PreparedStatement preparedStmt = cnx.prepareStatement("INSERT INTO `panier` (`id`, `nbrArticle`, `total`, `commande_id`, `client_id`) VALUES (NULL, ?,?,?,?);");
        preparedStmt.setInt(1,p.getNbrArticle());
        preparedStmt.setFloat(2,p.getTotal());
        preparedStmt.setInt(3,p.getCommande_id());
        preparedStmt.setInt(4,p.getClient_id());
        preparedStmt.executeUpdate();
        System.out.println( "add avec Succes Panier");
    }


    public ResultSet lister_Panier() throws SQLException
    {
        return cnx.createStatement().executeQuery("SELECT * FROM panier ");
    }


    public void updatePanier(Panier p) throws SQLException {
        PreparedStatement preparedStmt = cnx.prepareStatement("UPDATE panier SET nbrArticle =?,total=?,commande_id=?,client_id=? WHERE id= ?");
        preparedStmt.setInt(1,p.getNbrArticle());
        preparedStmt.setFloat(2,p.getTotal());
        preparedStmt.setInt(3,p.getCommande_id());
        preparedStmt.setInt(4,p.getClient_id());
        preparedStmt.setInt(5,p.getId());
        preparedStmt.executeUpdate();
        System.out.println( "UPDATTE  avec Succes Panier");

    }
    public void deletePanier (int id ) throws SQLException {

        PreparedStatement preparedStmt = cnx.prepareStatement(" DELETE FROM panier WHERE id = ?; ");
        preparedStmt.setInt(1,id);
        preparedStmt.executeUpdate();
        System.out.println( "DELETE  avec Succes Panier");
    }

}