/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Member;

import Controller.Admin.AdminViewBooksController;
import Model.Auteur;
import Model.Genre;
import Model.Livre;
import Model.Panier;
import Services.AuteurService;
import Services.GenreService;
import Services.LivreServices;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ClientsViewBooksController implements Initializable {

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
    private ImageView panierId;
    private ArrayList<Livre> lLivresByAuteur;
    private ArrayList<Livre> lLivresByGenre;
    private ArrayList<Livre> lLivres;
    private ArrayList<Livre> filtredLivres;
    private LivreServices ls;
    int x = 0;
    int y = 0;
    public static Livre livreToView;
    public static Panier p;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p = new Panier();
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
                ArrayList<Genre> gg = listGenres.stream().filter(g -> g.getNom_genre().toLowerCase()
                        .equals(cbGenre.getValue().toString().toLowerCase())).collect(Collectors
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
                lLivres = listLivre;
                remplirHbox(lLivres);
                x = 0;
                y = 0;
                cbAuteur.setValue(null);
                cbGenre.setValue(null);
                rechercherNomL.setText("");
            });

        } catch (SQLException ex) {
            Logger.getLogger(AdminViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        panierId.setOnMouseClicked((event) ->{
            try {
                windowsmouse("/view/cart.fxml", "menu", event);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        });


    }

    public HBox convertLivre(Livre l) {

        Label lbTitre = new Label("  " + l.getTitre_livre() + "  ");
        Label lblangue = new Label("  " + l.getLangue() + "  ");
        Label lbNbEx = new Label("  " + Integer.toString(l.getNb_exemplaires()) + "  ");



        JFXButton BtnView = new JFXButton();
        BtnView.setText("View");
        BtnView.setId(Integer.toString(l.getId_livre()));
        BtnView.setStyle("-fx-background-color: #5858FA; -fx-background-radius: 0px;");


        HBox.setMargin(lbTitre, new Insets(20, 20, 20, 50));
        HBox.setMargin(lblangue, new Insets(20, 20, 20, 50));
        HBox.setMargin(lbNbEx, new Insets(20, 20, 20, 50));
        HBox.setMargin(BtnView, new Insets(20, 20, 20, 50));
        //HBox.setMargin(lbEd, new Insets(20, 20, 20, 50));
        //BtnAction.setOnAction(this::handleButtonAction);
        lbTitre.setPrefWidth(300);
        lblangue.setPrefWidth(300);
        lbNbEx.setPrefWidth(300);
        BtnView.setPrefWidth(300);
        HBox.setMargin(lbTitre, new Insets(20, 100, 20, 20));
        HBox.setMargin(lblangue, new Insets(20, 100, 20, 20));
        HBox.setMargin(lbNbEx, new Insets(20, 100, 20, 20));
        HBox.setMargin(BtnView, new Insets(20, 100, 20, 20));

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

        LivreGraphique.getChildren().add(BtnView);
        BtnView.setOnAction((event) -> {
            livreToView = l;
            try {
                System.out.println("here");
                windows("/view/Book.fxml", "book", event);
            } catch (Exception ex) {
                System.out.println("exception here");
                System.out.println(ex.getMessage());
                Logger.getLogger(ClientsViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        LivreGraphique.setOnMouseEntered(event -> {
            LivreGraphique.setStyle("-fx-background-color : #9A2EFE");

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
    public void menu(ActionEvent event) throws Exception {
        windows("/view/dashboard.fxml", "menu", event);
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

    private void windowsClose(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        theStage.hide();
    }
    private void windowsmouse(String path, String title, MouseEvent event) throws Exception {

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
