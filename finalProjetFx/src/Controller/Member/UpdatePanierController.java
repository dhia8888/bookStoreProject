package Controller.Member;
import Services.LivreServices;
import Services.PanierService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Model.*;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UpdatePanierController implements Initializable {

    public Panier getSa()
    {
        return sa;
    }
    public void setSa(Panier sa)
    {
        this.sa = sa;
    }


    public static Panier sa;




    @FXML
    TextField totale;
    @FXML
    Spinner<Integer> nbrArticleItem;
    @FXML
    TextField titre;
    @FXML
    BorderPane bdpane;
    @FXML
    Button confirm,back;
    private PanierService panierService;
    private LivreServices livreServices;

    static float  a = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        panierService = new PanierService();
        livreServices = new LivreServices();

        bdpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                totale.setText(getSa().getTotal()+"");
                updateSpinnerValue(getSa().getNbrArticle());
                titre.setText(getSa().getTitreLiv());
            }
        });
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                int nbex = 0,nbA = 0;
             try {
                 nbex =livreServices.Nbexemplivre(titre.getText());
                 nbA=nbrArticleItem.getValue();
             }catch (Exception e)
             {
                 System.out.println("problem");
             }
                if(nbex - nbA <0)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Look,nb exemplaire indisponible");
                    alert.setContentText(" Vérifier a nouveau !");
                    alert.showAndWait();
                }else
                {
                    try
                    {
                        PanierService panierService = new PanierService();
                        Panier p = new Panier(getSa().getId(),getSa().getTitreLiv(),nbrArticleItem.getValue(),Float.parseFloat(totale.getText()),3);
                        System.out.println(p.toString());
                        panierService.updatePanier(p);
                        final Node source = (Node) event.getSource();
                        final Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();
                    } catch (Exception e) {
                        System.out.println("poblem ______________");
                    }
                }
            }
        });
        nbrArticleItem.valueProperty().addListener((obs, oldValue, newValue) ->
        {
            updateSpinnerValue(newValue);
            try {
                LivreServices livreServices = new LivreServices();
                totale.setText((""+livreServices.prixlivre(titre.getText())*newValue));
                a=livreServices.prixlivre(titre.getText())*nbrArticleItem.getValue();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        back.setOnAction(e->{
            final Node source = (Node) e.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });
    }
    public void updateSpinnerValue(Integer newValue) {
        nbrArticleItem.getValueFactory().setValue(newValue);
    }
}
