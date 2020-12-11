package Controller.Admin;

import Model.Commande;
import Services.StatistiquesServiceStock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class ListeCommandesController implements Initializable {
    @FXML
    private TableView<Commande> infocom;
    @FXML
    private TableColumn<Commande , Integer> col_id_lc;
    @FXML
    private TableColumn<Commande,String> col_date_lc;
    @FXML
    private TableColumn<Commande,Integer> col_livraison_lc;
    @FXML
    private TableColumn<Commande,Integer> col_client_lc;
    
    
   ArrayList<Commande> ComInfo = new ArrayList<>();
   
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try {
            StatistiquesServiceStock ss = new StatistiquesServiceStock();
            ComInfo = ss.getComInfo();
               
        }catch (Exception e)
        {
            System.out.println("problem");
        }
        col_id_lc.setCellValueFactory(new PropertyValueFactory<>("id_com"));
        col_date_lc.setCellValueFactory(new PropertyValueFactory<>("date_com"));
        col_livraison_lc.setCellValueFactory(new PropertyValueFactory<>("livraison"));
        col_client_lc.setCellValueFactory(new PropertyValueFactory<>("id_client"));
      
       
        infocom.getItems().setAll(ComInfo);

    }


}
