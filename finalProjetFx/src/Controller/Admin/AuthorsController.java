/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Auteur;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import utils.UploadPhoto;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class AuthorsController implements Initializable {

    @FXML
    private Button boutonClose;
    @FXML
    private TextField tfNomA;
    @FXML
    private TextField tfPrenomA;
    @FXML
    private TextArea tfBioA;
    @FXML
    private DatePicker dateNA;
    public static HashSet<Auteur> auteurs;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelDate;
    @FXML
    private Button boutonAdd1;
    @FXML
    private Label labelBio;
    @FXML
    private Label labelPrenom;
    @FXML
    private Label LbErreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        auteurs = new HashSet<>();
        this.boutonAdd1.setOnAction((event) -> {
            if ((tfNomA.getText().equals("")) || (tfPrenomA.getText().equals("")) || (tfBioA.getText().equals("")) || (dateNA.getValue().toString().equals(""))) {
                LbErreur.setText("Vérifiez les champs");

            } else {
                LbErreur.setText("");
                Auteur a = new Auteur(tfNomA.getText(), tfPrenomA.getText(), tfBioA.getText(), dateNA.getValue().toString());
                System.out.println(a);
                auteurs.add(a);
                tfNomA.setText("");
                tfPrenomA.setText("");
                tfBioA.setText("");
                dateNA.setValue(null);
                alert.setContentText("auteur ajouté");
                alert.setTitle("ajout");
                alert.setHeaderText("ajout effectué avec succés");
                alert.showAndWait();
                
            }
        });

        boutonClose.setOnAction((event) -> {
            try {
                windowsClose(event);
            } catch (Exception ex) {
                Logger.getLogger(GenresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void windowsClose(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        theStage.hide();
    }
}
