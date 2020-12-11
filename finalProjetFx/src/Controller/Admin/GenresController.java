/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;
import Model.Genre;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class GenresController implements Initializable {

    @FXML
    private BorderPane bpAjouterGenre;
    @FXML
    private ToolBar tbAjouterG;
    @FXML
    private Pane paneAG;
    @FXML
    private Label labelTitre;
    @FXML
    private Pane pane2AG;
    @FXML
    private Label labelNomGenre;
    @FXML
    private Button boutonClose;
    @FXML
    private Button boutonAdd;
    @FXML
    private TextField tfNomG;
    public static HashSet<Genre> genres;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        genres= new HashSet<>();
        this.boutonAdd.setOnAction((event) -> {
            Genre g =new Genre(tfNomG.getText());
            System.out.println(g);
            genres.add(g);
            tfNomG.setText("");
            alert.setContentText("genre ajouté");
            alert.setTitle("ajout");
            alert.setHeaderText("ajout effectué avec succés");
            alert.showAndWait();
            
        });
        
        boutonClose.setOnAction((event) -> {
            try {
                windowsClose(event);
            } catch (Exception ex) {
                Logger.getLogger(GenresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

       private void windowsClose( ActionEvent event) throws Exception {
                Node source = (Node) event.getSource();
                Window theStage = source.getScene().getWindow();
                theStage.hide();
    }
    
    
}
