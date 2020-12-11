package Services;
import Interfaces.PanierInterface;
import Model.Panier;
import utils.MyConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class PanierService implements  PanierInterface{
    private Connection cnx;


    public PanierService()
    {
        cnx = MyConnection.getInstance().getConnection();

    }
    public void addPanier(Panier p) throws SQLException
    {
        PreparedStatement preparedStmt = cnx.prepareStatement("INSERT INTO `panier` (`id`, `titreLiv`, `nbrArticle`, `total`, `client_id`) VALUES (NULL, ?,?,?,?);");
        preparedStmt.setString(1,p.getTitreLiv());
        preparedStmt.setInt(2,p.getNbrArticle());
        preparedStmt.setFloat(3,p.getTotal());
        preparedStmt.setInt(4,p.getClient_id());
        preparedStmt.executeUpdate();
        System.out.println( "add avec Succes Panier");
    }


    public ArrayList<Panier> getSales(int user_id) throws SQLException
    {
        PreparedStatement stm = cnx.prepareStatement("SELECT * FROM panier where client_id =?");
        stm.setInt(1,user_id);
        ResultSet rst= stm.executeQuery();
        ArrayList<Panier> results = new ArrayList<>();
        while (rst.next())
        {
            Panier p = new Panier();
            p.setId(rst.getInt(1));
            p.setTitreLiv(rst.getString(2));
            p.setNbrArticle(rst.getInt(3));
            p.setTotal(rst.getFloat(4));
            p.setClient_id(rst.getInt(5));
            results.add(p);
        }
        return  results;
    }

    public double SommePanier (int user_id) throws SQLException {

        PreparedStatement stm = cnx.prepareStatement("SELECT sum(total) FROM `panier` WHERE client_id = ?");
        stm.setInt(1,3);
        ResultSet rst= stm.executeQuery();
        ArrayList<String> liste = new ArrayList<>();
        while (rst.next())
        {
            return rst.getDouble(1);
        }
    return 0.0;
    }


    public void updatePanier(Panier p) throws SQLException
    {
        PreparedStatement preparedStmt = cnx.prepareStatement("UPDATE panier SET nbrArticle =?,total=? WHERE id= ?");
        preparedStmt.setInt(1,p.getNbrArticle());
        preparedStmt.setFloat(2,p.getTotal());
        preparedStmt.setInt(3,p.getId());
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