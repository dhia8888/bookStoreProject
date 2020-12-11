/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Livre;
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
public class ListeLivresController implements Initializable {
    @FXML
    private TableView<Livre> infoliv;

    @FXML
    private TableColumn<Livre , Integer> col_id;
    @FXML
    private TableColumn<Livre,String> col_titre;
    @FXML
    private TableColumn<Livre,String> col_langue;
    @FXML
    private TableColumn<Livre,Integer> col_nbpages;
    @FXML
    private TableColumn<Livre,String> col_annee;
    @FXML
    private TableColumn<Livre,Double> col_prix;
    @FXML
    private TableColumn<Livre,Integer> col_nbexemplaires;
    
    
   ArrayList<Livre> LivInfo = new ArrayList<>();
    @FXML
    private TableColumn<?, ?> col_id_lc;
    @FXML
    private TableColumn<?, ?> col_date_lc;
    @FXML
    private TableColumn<?, ?> col_livraison_lc;
    @FXML
    private TableColumn<?, ?> col_client_lc;
    @Override
    public void initialize(URL location, ResourceBundle resources)

    {
        try {
            StatistiquesServiceStock ss = new StatistiquesServiceStock();
            LivInfo = ss.getLivresInfo();
               
        }catch (Exception e)
        {
            System.out.println("problem");
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_livre"));
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre_livre"));
        col_langue.setCellValueFactory(new PropertyValueFactory<>("langue"));
        col_nbpages.setCellValueFactory(new PropertyValueFactory<>("nb_pages"));
        col_annee.setCellValueFactory(new PropertyValueFactory<>("annee"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_nbexemplaires.setCellValueFactory(new PropertyValueFactory<>("nb_exemplaires"));
       
        infoliv.getItems().setAll(LivInfo);

    }


}
