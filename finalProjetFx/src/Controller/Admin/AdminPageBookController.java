/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Edition;
import Model.Livre;
import Services.LivreServices;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.UploadPhoto;
/**
 * FXML Controller class
 *
 * @author aymen
 */
public class AdminPageBookController implements Initializable {

    @FXML
    private VBox adminVBox;
    @FXML
    private ImageView backToHome;
    @FXML
    private Label labelTitre;
    @FXML
    private TextField trTitre;
    @FXML
    private Label labelPrix;
    @FXML
    private TextField trPrix;
    @FXML
    private Label labeldesc;
    @FXML
    private TextField trDesc;
    @FXML
    private Label labelImage;
    @FXML
    private Button btnBrowse;
    @FXML
    private Label labelUrl;
    @FXML
    private Label labelAnnee;
    @FXML
    private TextField trAnnee;
    @FXML
    private Label labelLangage;
    @FXML
    private TextField trLangue;
    @FXML
    private Label labelNbEx;
    @FXML
    private TextField trNbEx1;
    @FXML
    private Label labelEditionAddress;
    @FXML
    private TextField trAdressEd;
    @FXML
    private Label labelEditionPays;
    @FXML
    private TextField trPaysEd1;
    @FXML
    private Label labelAuteur;
    @FXML
    private Button btnAddAuteur;
    @FXML
    private Label labelGenre;
    @FXML
    private Button btnAddGenre;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Label labelNbPage;
    @FXML
    private TextField trNbPage;
    private String image = "";
    @FXML
    private Label LbErreur;
    @FXML
    private Label labelEditionAddress1;
    @FXML
    private TextField tfLat;
    @FXML
    private Label labelEditionAddress11;
    @FXML
    private TextField tfLong;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        LivreServices ls = new LivreServices();
        trNbEx1.setOnKeyTyped(e -> {
            char input = e.getCharacter().charAt(0);
            if (Character.isDigit(input) != true) {
                e.consume();
            }
        });
        trNbPage.setOnKeyTyped(e -> {
            char input = e.getCharacter().charAt(0);
            if (Character.isDigit(input) != true) {
                e.consume();
            }
        });
        trAnnee.setOnKeyTyped(e -> {
            char input = e.getCharacter().charAt(0);
            if (Character.isDigit(input) != true) {
                e.consume();
            }
        });

        btnAddAuteur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    windows("/view/AuthorsManagment.fxml", "Auteur", event);
                } catch (Exception ex) {
                    Logger.getLogger(AdminPageBookController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        btnAddGenre.setOnAction((event) -> {
            try {
                windows("/view/GenreManagment.fxml", "genre", event);
            } catch (Exception ex) {
                Logger.getLogger(AdminPageBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnValider.setOnAction((event) -> {



            if ((trTitre.getText().equals("")) || (trLangue.getText().equals("")) || (trDesc.getText().equals("")) || (trNbPage.getText().equals("")) || (Integer.parseInt(trNbPage.getText())<=0)||  (trNbEx1.getText().equals(""))|| (Integer.parseInt(trNbEx1.getText())<=0)|| (trAnnee.getText().equals("")) || (Integer.parseInt(trAnnee.getText())>2020)|| (trPrix.getText().equals(""))|| (Float.parseFloat(trPrix.getText())<=0.0) || (image.equals(""))) {
                LbErreur.setText("Vérifiez les champs");

            }




            else {
                LbErreur.setText("");
                Livre l = new Livre(trTitre.getText(), trLangue.getText(), trDesc.getText(), Integer.parseInt(trNbPage.getText()), Integer.parseInt(trNbEx1.getText()), trAnnee.getText(), Double.valueOf(trPrix.getText()), image);
                Edition e = new Edition(trPaysEd1.getText(), trAdressEd.getText(),Double.valueOf(tfLat.getText().toString()),Double.valueOf(tfLong.getText().toString()));
                l.setAuteurs(AuthorsController.auteurs);
                l.setGenres(GenresController.genres);
                l.setEditeur(e);

                alert.setContentText("livre ajouté");
                alert.setTitle("ajout");
                alert.setHeaderText("ajout effectué avec succés");
                alert.showAndWait();
                try {
                    UploadPhoto.uploadPhoto(image,l);

                } catch (Exception ex) {
                    Logger.getLogger(AdminPageBookController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ls.addLivre(l);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminPageBookController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnBrowse.setOnAction((event) -> {
            final FileChooser filechooser = new FileChooser();
            Stage stage = (Stage) btnBrowse.getScene().getWindow();
            File file = filechooser.showOpenDialog(stage);
            if (file != null) {
                labelUrl.setText(file.getPath());
                image = file.getPath().toString();
            }
        });
        btnAnnuler.setOnAction((event) -> {
            try {
                windowsClose(event);
            } catch (Exception ex) {
                Logger.getLogger(GenresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void windows(String path, String title, ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        //stage.initModality(Modality.APPLICATION_MODAL.APPLICATION_MODAL);
        // stage.initStyle(StageStyle.DECORATED.UNDECORATED);
        stage.setTitle(title);
        stage.setScene(new Scene(root1));
        stage.show();

    }

    public void menu(ActionEvent event) throws Exception {
        windows("/view/dashboard.fxml", "menu", event);
    }


    private void windowsClose(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        theStage.hide();
    }
}
