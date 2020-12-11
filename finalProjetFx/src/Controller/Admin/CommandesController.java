package Controller.Admin;
import Model.Commande;
import Services.CommandeService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CommandesController implements Initializable
{
    @FXML
    private TableView<Commande> ordersTable;
    @FXML
    private TableColumn<Commande, Integer> idx;
    @FXML
    private TableColumn<Commande, String> datecom;
    @FXML
    private TableColumn<Commande, Integer> livraison;
    @FXML
    private TableColumn<Commande, Integer> client_id;
    @FXML
    Button deleteItem;
    @FXML
    Button updateItem;
    @FXML
    Button referchitem;
    CommandeService commandeService;
    ArrayList<Commande> commandes;
 public static Commande commande;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        commandeService= new CommandeService();
        commandes = new ArrayList<>();
        try
        {
            commandes = commandeService.lister_Commandes();
        }catch (Exception e)
        {
            System.out.println("problem");
        }

        idx.setCellValueFactory(new PropertyValueFactory<>("id_com"));
        datecom.setCellValueFactory(new PropertyValueFactory<>("date_com"));
        livraison.setCellValueFactory(new PropertyValueFactory<>("livraison"));
        client_id.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        ordersTable.getItems().setAll(commandes);
        ordersTable.refresh();
        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("do you want to delete this order");
                alert.setHeaderText("Look, a Confirmation Dialog");
                alert.setContentText("Are you ok with this?");
                Optional<ButtonType> result = alert.showAndWait();
                Commande commande = ordersTable.getSelectionModel().getSelectedItem();

                if (result.get() == ButtonType.OK && commande != null)
                {
                    try
                    {
                        commandeService.deleteCommande(commande.getId_com());
                        ordersTable.getItems().setAll(commandeService.lister_Commandes());
                    }
                    catch (Exception e)
                    {
                        System.out.println("probem sql ");
                    }
                } else
                    {
                    System.out.println("cliquer sur un element SVP");
                }
            }
        });
        updateItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Commande com = ordersTable.getSelectionModel().getSelectedItem();

                    if(com == null)

                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText("Look,selectionner un element avant ");
                        alert.setContentText(" Vérifier a nouveau !");
                        alert.showAndWait();
                    }else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateBook.fxml"));
                        Parent root = loader.load();
                        UpdateBookController updateBookController = new UpdateBookController();
                        System.out.println(com);
                        updateBookController.setCommande(com);
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        referchitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                ordersTable.getItems().clear();
               load();
            }
        });
    }

    public void setCommande(Commande com)
    {
        this.commande = com;
    }

    public Commande getCommande() {
        return commande;
    }

    public void  load ()
    {
        commandeService= new CommandeService();
        commandes = new ArrayList<>();
        try
        {
            commandes = commandeService.lister_Commandes();
        }catch (Exception e)
        {
            System.out.println("problem");
        }
        ordersTable.getItems().setAll(commandes);
    }

}
