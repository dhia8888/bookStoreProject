package Controller.Admin;
import Model.Commande;
import Services.CommandeService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UpdateBookController implements Initializable
{
    @FXML
    private Button back;
    @FXML
    private Button confirm;
    @FXML
    private TextField id_com;
    @FXML
    private DatePicker date_com;
    @FXML
    private Spinner<Integer>  livraison;
    @FXML
    private BorderPane bdpane;

    public Commande getCommande()
    {
        return commande;
    }
    public void setCommande(Commande commande)
    {
        this.commande = commande;
    }
   public static  Commande  commande;

    CommandeService commandeService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        commandeService = new CommandeService();

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage = (Stage) back.getScene().getWindow();
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {




                Commande c = new Commande(Integer.valueOf(id_com.getText()),date(date_com.getValue()),livraison.getValue());
                try {
                    commandeService.updateCommande(c);
                } catch (SQLException e) {
                    System.out.println("or");
                }
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });


        bdpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id_com.setText(commande.getId_com() + "");
                updateSpinnerValue(commande.getLivraison());
                try {
                   // date_com.setValue(LOCAL_DATE(commande.getDate_com()));
                } catch (NullPointerException e) {
                }
            }
        });

    }
    public static final LocalDate LOCAL_DATE (String dateString)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    String date(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return date.format(formatter);
    }
    public void updateSpinnerValue(Integer newValue) {
        livraison.getValueFactory().setValue(newValue);
    }

}
