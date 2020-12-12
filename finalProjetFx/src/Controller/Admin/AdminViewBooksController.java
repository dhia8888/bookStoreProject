/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Controller.Member.ClientsViewBooksController;
import Services.AuteurService;
import Services.GenreService;
import Services.LivreServices;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import Model.*;
import com.jfoenix.controls.JFXButton;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class AdminViewBooksController implements Initializable {

    @FXML
    private VBox vbListeL;
    @FXML
    private ComboBox<?> cbGenre;
    @FXML
    private ComboBox<?> cbAuteur;

    @FXML
    private TextField rechercherNomL;
    private ArrayList<Livre> listLivre;
    private ArrayList<Genre> listGenres;
    private ArrayList<Auteur> listAuteur;
    private ObservableList obListg;
    private ObservableList obLista;
    @FXML
    private Button btnReset;
    @FXML
    private Button BtnAjouter;
    private ArrayList<Livre> lLivresByAuteur;
    private ArrayList<Livre> lLivresByGenre;
    private ArrayList<Livre> lLivres;
    private ArrayList<Livre> filtredLivres;
    private LivreServices ls;
    int x = 0;
    int y = 0;
    public static  Livre livreToUpdate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            ls = new LivreServices();
            AuteurService as = new AuteurService();
            GenreService gs = new GenreService();
            listLivre = ls.getLivres();
            lLivres = listLivre;
            listGenres = gs.getGenres();
            listAuteur = as.getAuteurs();
            List<String> lg = new ArrayList<>();
            listGenres.stream().forEach(g -> lg.add(g.getNom_genre()));
            obListg = FXCollections.observableList(lg);
            cbGenre.getItems().clear();
            cbGenre.setItems(obListg);
            List<String> la = new ArrayList<>();
            listAuteur.stream().forEach(a -> la.add(a.getNom()));
            obLista = FXCollections.observableList(la);
            cbAuteur.getItems().clear();
            cbAuteur.setItems(obLista);
            remplirHbox(listLivre);
            rechercherNomL.textProperty().addListener((event) -> {
                String nom = rechercherNomL.getText();
                System.out.println(nom);
                if ((x == 0) && (y == 0)) {
                    lLivres = listLivre.stream().filter(livre -> livre
                            .getTitre_livre().toLowerCase().startsWith(nom))
                            .collect(Collectors
                                    .toCollection(ArrayList::new));
                    vbListeL.getChildren().clear();

                    remplirHbox(lLivres);
                } else if ((x != 0) && (y == 0)) {
                    lLivres = lLivresByAuteur.stream().filter(livre -> livre
                            .getTitre_livre().toLowerCase().startsWith(nom))
                            .collect(Collectors
                                    .toCollection(ArrayList::new));
                    vbListeL.getChildren().clear();

                    remplirHbox(lLivres);
                } else if ((x == 0) && (y != 0)) {
                    lLivres = lLivresByGenre.stream().filter(livre -> livre
                            .getTitre_livre().toLowerCase().startsWith(nom))
                            .collect(Collectors
                                    .toCollection(ArrayList::new));
                    vbListeL.getChildren().clear();

                    remplirHbox(lLivres);
                } else {
                    lLivres = filtredLivres.stream().filter(livre -> livre
                            .getTitre_livre().toLowerCase().startsWith(nom))
                            .collect(Collectors
                                    .toCollection(ArrayList::new));
                    vbListeL.getChildren().clear();

                    remplirHbox(lLivres);
                }

            });
            //recherche avec aut
            cbAuteur.setOnAction((event) -> {
                ArrayList<Auteur> aa = listAuteur.stream().filter(a -> a.getNom().toLowerCase().equals(cbAuteur.getValue().toString().toLowerCase())).collect(Collectors
                        .toCollection(ArrayList::new));
                x = 1;
                if (y == 0) {
                    lLivresByAuteur = listLivre.stream().filter(livre -> livre
                            .getAuteurs().contains(aa.get(0)))
                            .collect(Collectors
                                    .toCollection(ArrayList::new));
                    vbListeL.getChildren().clear();
                    remplirHbox(lLivresByAuteur);
                } else {
                    lLivresByAuteur = listLivre.stream().filter(livre -> livre
                            .getAuteurs().contains(aa.get(0)))
                            .collect(Collectors
                                    .toCollection(ArrayList::new));

                    filtredLivres = lLivresByGenre.stream().filter(livre -> livre
                            .getAuteurs().contains(aa.get(0)))
                            .collect(Collectors
                                    .toCollection(ArrayList::new));
                    vbListeL.getChildren().clear();
                    remplirHbox(filtredLivres);
                }
            });
            //recherche avec genre
            cbGenre.setOnAction((event) -> {
                ArrayList<Genre> gg = listGenres.stream().filter(g -> g.getNom_genre().toLowerCase().equals(cbGenre.getValue().toString().toLowerCase())).collect(Collectors
                        .toCollection(ArrayList::new));
                y = 1;
                if (x == 0) {
                    lLivresByGenre = listLivre.stream().filter(livre -> livre
                            .getGenres().contains(gg.get(0)))
                            .collect(Collectors
                                    .toCollection(ArrayList::new));
                    vbListeL.getChildren().clear();
                    remplirHbox(lLivresByGenre);
                } else {
                    lLivresByGenre = listLivre.stream().filter(livre -> livre
                            .getGenres().contains(gg.get(0)))
                            .collect(Collectors
                                    .toCollection(ArrayList::new));
                    filtredLivres = lLivresByAuteur.stream().filter(livre -> livre
                            .getGenres().contains(gg.get(0)))
                            .collect(Collectors
                                    .toCollection(ArrayList::new));
                    vbListeL.getChildren().clear();
                    remplirHbox(filtredLivres);
                }

            });
            btnReset.setOnAction((event) -> {
                vbListeL.getChildren().clear();

                try{
                    listLivre=ls.getLivres();
                } catch(SQLException e){
                    System.out.println("exception :"+e);
                }
                remplirHbox(listLivre);
                x = 0;
                y = 0;
                cbAuteur.setValue(null);
                cbGenre.setValue(null);
                rechercherNomL.setText("");

            });
        } catch (SQLException ex) {
            Logger.getLogger(AdminViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BtnAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    windows("/view/AdminPageBook.fxml", "book", event);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }


            }
        });

    }

    public HBox convertLivre(Livre l) {
        Label lbTitre = new Label("  " + l.getTitre_livre() + "  ");
        Label lblangue = new Label("  " + l.getLangue() + "  ");
        Label lbNbEx = new Label("  " + Integer.toString(l.getNb_exemplaires()) + "  ");
        // Label lbEd = new Label("  " + l.getEditeur().getAdresse() + "  ");


        JFXButton BtnUpdate = new JFXButton();
        BtnUpdate.setText("Update");
        BtnUpdate.setId(Integer.toString(l.getId_livre()));
        BtnUpdate.setStyle("-fx-background-color: #5858FA; -fx-background-radius: 0px;");
        JFXButton BtnDelete = new JFXButton();
        BtnDelete.setText("Delete");
        // BtnDelete.setId(Integer.toString(l.getId_livre()));
        BtnDelete.setStyle("-fx-background-color: #FF0040; -fx-background-radius: 0px;");

        HBox.setMargin(lbTitre, new Insets(20, 20, 20, 50));
        HBox.setMargin(lblangue, new Insets(20, 20, 20, 50));
        HBox.setMargin(lbNbEx, new Insets(20, 20, 20, 50));
        // HBox.setMargin(lbEd, new Insets(20, 20, 20, 50));
        //BtnAction.setOnAction(this::handleButtonAction);
        lbTitre.setPrefWidth(300);
        lblangue.setPrefWidth(300);
        lbNbEx.setPrefWidth(300);
        BtnDelete.setPrefWidth(200);
        BtnUpdate.setPrefWidth(200);
        HBox.setMargin(lbTitre, new Insets(20, 20, 20, 20));
        HBox.setMargin(lblangue, new Insets(20, 20, 20, 20));
        HBox.setMargin(lbNbEx, new Insets(20, 20, 20, 20));
        HBox.setMargin(BtnDelete, new Insets(20, 20, 20, 20));
        HBox.setMargin(BtnUpdate, new Insets(20, 20, 20, 20));
        HBox LivreGraphique = new HBox();
        LivreGraphique.setAlignment(Pos.CENTER);
        LivreGraphique.setMaxHeight(Integer.MAX_VALUE);
        LivreGraphique.setMaxWidth(Integer.MAX_VALUE);
        LivreGraphique.setStyle("-fx-background-color: #ffffff;");
        LivreGraphique.getChildren().add(lbTitre);
        //LivreGraphique.getChildren().add(new Label("   "));
        LivreGraphique.getChildren().add(lblangue);
        //LivreGraphique.getChildren().add(new Label("   "));
        LivreGraphique.getChildren().add(lbNbEx);
        // LivreGraphique.getChildren().add(new Label("   "));
        //LivreGraphique.getChildren().add(lbEd);
        // LivreGraphique.getChildren().add(new Label("   "));

        LivreGraphique.getChildren().add(BtnDelete);
        LivreGraphique.getChildren().add(BtnUpdate);
        BtnUpdate.setOnAction((event) -> {
            try {

                livreToUpdate=l;
                windows("/view/ModifBook.fxml", "book", event);
            }
            catch (Exception ex){
                System.out.println("exception here");
                System.out.println(ex.getMessage());
                Logger.getLogger(ClientsViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        BtnDelete.setOnAction((event) -> {
            try {
                ls.deleteLivre(l);
                vbListeL.getChildren().clear();
                listLivre=ls.getLivres();
                remplirHbox(listLivre);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("livre supprimé");
                alert.setTitle("suppression ");
                alert.setHeaderText("suppression effectué avec succés");
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(AdminViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        LivreGraphique.setOnMouseEntered(event -> {
            LivreGraphique.setStyle("-fx-background-color : #F4D504");
        });
        LivreGraphique.setOnMouseExited(event -> {
            LivreGraphique.setStyle("-fx-background-color : #ffffff");
        });
        return LivreGraphique;
    }

    public void remplirHbox(Collection<Livre> livres) {
        System.out.println("here");
        for (Livre l : livres) {
            vbListeL.getChildren().add(convertLivre(l));
        }
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

}