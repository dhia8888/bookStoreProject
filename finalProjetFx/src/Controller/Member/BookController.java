/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Member;


import Model.Auteur;
import Model.Commande;
import Model.Genre;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Model.Panier;
import Services.CommandeService;
import Services.PanierService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.MyBrowser;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class BookController implements Initializable {

    @FXML
    private ImageView imageLivre;
    @FXML
    private VBox vbAuteurs;
    @FXML
    private Label lbPrix;
    @FXML
    private Label taDesc;
    @FXML
    private Button btnAddPanier;
    @FXML
    private Label lbTitre;
    @FXML
    private Label lbAnnee;
    @FXML
    private VBox vbGenres;
    @FXML
    private Label lbQ;
    @FXML
    private TextField tfQ;
    @FXML
    private Button btnp;
    @FXML
    private Button btnMap;
    @FXML
    private Button btnM;
    int q = 0;
    @FXML
    private AnchorPane apMap;

    MyBrowser myBrowser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbPrix.setText(String.valueOf(ClientsViewBooksController.livreToView.getPrix()));
        taDesc.setText(ClientsViewBooksController.livreToView.getDescription());
        lbAnnee.setText(ClientsViewBooksController.livreToView.getAnnee());
        lbTitre.setText(ClientsViewBooksController.livreToView.getTitre_livre());
        lbQ.setText(String.valueOf(ClientsViewBooksController.livreToView.getNb_exemplaires()));
btnMap.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        Stage stage = (Stage) btnMap.getScene().getWindow();
        myBrowser = new MyBrowser(ClientsViewBooksController.livreToView.getEditeur().getLatitude(), ClientsViewBooksController.livreToView.getEditeur().getLongitude());
        Scene scene = new Scene(myBrowser);
        stage.setScene(scene);
        stage.setWidth(900);
        stage.setHeight(500);
        stage.show();
    }
});

        imageLivre.setImage(new Image("http://127.0.0.1/" + ClientsViewBooksController.livreToView.getImage()));
        tfQ.setDisable(true);
        tfQ.setText(String.valueOf(q));
        btnp.setOnAction((event) -> {
            if (q < ClientsViewBooksController.livreToView.getNb_exemplaires()) {
                q++;
                tfQ.setText(String.valueOf(q));
            }
        });
        btnM.setOnAction((event) -> {
            if (q > 0) {
                q--;
                tfQ.setText(String.valueOf(q));
            }
        });
        btnAddPanier.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                try {

                    PanierService panierService = new PanierService();
                    CommandeService commandeService = new CommandeService();
                    int quantie = Integer.parseInt(tfQ.getText().toString());
                    float totale =(float)(ClientsViewBooksController.livreToView.getPrix() * quantie);
                    System.out.println(totale);
                    int livre_id = ClientsViewBooksController.livreToView.getId_livre();

                    Panier p = new Panier(ClientsViewBooksController.livreToView.getTitre_livre(),quantie,totale,3);
                    panierService.addPanier(p);

                }catch (Exception e)
                {
                    System.out.println("problem ");
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Look, add panier ");
                alert.setContentText(" add panier  !");
                alert.showAndWait();

            }
        });
        for (Auteur a : ClientsViewBooksController.livreToView.getAuteurs()) {
            Label lbaut = new Label();
            lbaut.setText(a.getNom() + " " + a.getPrenom());
            vbAuteurs.getChildren().add(lbaut);
        }
        for (Genre g : ClientsViewBooksController.livreToView.getGenres()) {
            Label lbgenre = new Label();
            lbgenre.setText(g.getNom_genre());
            vbGenres.getChildren().add(lbgenre);
        }
        btnAddPanier.setOnAction((event) -> {





            //ClientsViewBooksController.p.addToPanier(ClientsViewBooksController.livreToView);
//            try {
//                windows("/view/ClientsViewBooks.fxml", "book", event);
//            } catch (Exception ex) {
//                Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
//            }



        });
    }

    private void windows(String path, String title, ActionEvent event) throws Exception {

        double width = ((Node) event.getSource()).getScene().getWidth();
        double height = ((Node) event.getSource()).getScene().getHeight();
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
