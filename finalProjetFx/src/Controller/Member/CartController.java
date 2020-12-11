package Controller.Member;
import Model.Panier;
import Services.PanierService;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.crypto.dom.DOMCryptoContext;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    public void setCartTable(ArrayList<Panier> sales) {
        this.cartTable.getItems().setAll(paniers);
    }
    @FXML
    private TableView<Panier> cartTable;

    @FXML
    private TableColumn<Panier, Integer> id;

    @FXML
    private TableColumn<Panier, String> titreLiv;

    @FXML
    private TableColumn<Panier, Integer> nbrArticle;

    @FXML
    private TableColumn<Panier, Float> total;

    @FXML
    private TableColumn<Panier, Integer> client_id ;




    @FXML
    JFXButton proceedToCheckout;
    @FXML
    JFXButton itemRemove;
    @FXML
    JFXButton itemUpdate;
    @FXML
    JFXButton itemRefresh;

    @FXML
    JFXButton itemCheckout;


    @FXML
    private Label subtotalLabel;

    private PanierService panierService;

    private Alert.AlertType type = Alert.AlertType.INFORMATION;

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    ArrayList<Panier> paniers = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {




        load();


        id.setCellValueFactory(new PropertyValueFactory<>("id"));


        titreLiv.setCellValueFactory(new PropertyValueFactory<>("titreLiv"));


        nbrArticle.setCellValueFactory(new PropertyValueFactory<>("nbrArticle"));


       total.setCellValueFactory(new PropertyValueFactory<>("total"));


        cartTable.getItems().setAll(paniers);


        itemRemove.setOnAction(e-> {
            Panier s = cartTable.getSelectionModel().getSelectedItem();
            if (s != null) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("do you want to delete this order");
                    alert.setHeaderText("Look,do you want to delete this order");
                    alert.setContentText("Are you ok with this?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        try
                        {
                            System.out.println(s.getId());

                            panierService.deletePanier(s.getId());

                            cartTable.getItems().setAll(panierService.getSales(3));

                            subtotalLabel.setText(String.valueOf(panierService.SommePanier(3)));
                        }
                        catch (Exception ex)
                        {
                            System.out.println("probem sql Panier ");
                        }
                    }
                }else if(s==null) {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Look, Problem remove item");
                    alert.setContentText(" Sélectionner un élément avant  !");

                    alert.showAndWait(); }





        });
        itemCheckout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                try {
                  if (panierService.getSales(3).isEmpty())
                  {
                      Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle("Warning Dialog");
                      alert.setHeaderText("Look, Item vide  ");
                      alert.setContentText("Acheter un element avant !");
                      alert.showAndWait();
                  }else {
                      windowsClose(event);
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Paiement.fxml"));
                      PaiementController paiementController = new PaiementController();
                      paiementController.setSales(panierService.getSales(3));

                      paiementController.setTotale(Double.parseDouble(subtotalLabel.getText()));
                      System.out.println(subtotalLabel.getText());
                      Parent root = loader.load();
                      Stage stage = new Stage();
                      Scene scene = new Scene(root);
                      stage.setScene(scene);
                      stage.show();
                  }
                }catch (Exception e)
                {

                    System.out.println(" problem payment view ");

                }


            }
        });

  itemUpdate.setOnAction(e-> {
      Panier s = cartTable.getSelectionModel().getSelectedItem();
      if (s != null) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("do you want to update this order");
          alert.setHeaderText("Look,do you want to update this order");
          alert.setContentText("Are you ok with this?");
          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK) {
              try {

                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdatePanier.fxml"));
                  Parent root = loader.load();
                  UpdatePanierController update = new UpdatePanierController();
                  update.setSa(s);

                  Stage stage = new Stage();
                  Scene scene = new Scene(root);
                  stage.setScene(scene);
                  stage.show();

              } catch (Exception ex) {
                  System.out.println("probem sql Panier ");
              }
          }
      } else if (s == null) {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Warning Dialog");
          alert.setHeaderText("Look, Problem update item");
          alert.setContentText(" Sélectionner un élément avant  !");
          alert.showAndWait();
      }
  });

        itemRefresh.setOnAction(e->{
                load();
                subtotalLabel.setText("");
                try
                {
                       subtotalLabel.setText(panierService.SommePanier(3)+"");
                } catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }
            });

    }

    public void  load (){
        try
        {
            panierService = new PanierService();
            paniers = panierService.getSales(3);
            subtotalLabel.setText(String.valueOf(panierService.SommePanier(3)));
            cartTable.getItems().setAll(paniers);
        } catch (Exception e) {
            System.out.println("problem");

        }
    }

    private void windows(String path, String title, ActionEvent event,ArrayList<Panier> paniers) throws Exception {

        double width = ((Node) event.getSource()).getScene().getWidth();
        double height = ((Node) event.getSource()).getScene().getHeight();
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root, width, height);
        PaiementController paiementController = new PaiementController();
        paiementController.setSales(paniers);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    private void windowsClose(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        theStage.hide();
    }
}



