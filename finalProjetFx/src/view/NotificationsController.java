/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Admin.AccueilController;
import static Controller.Admin.AccueilController.listn;
import static Controller.Admin.AdminViewBooksController.livreToUpdate;
import Controller.Member.ClientsViewBooksController;
import Model.*;
import Services.NotificationService;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class NotificationsController implements Initializable {

    @FXML
    private VBox vbNotif;
    @FXML
    private Button btnViewed;
    @FXML
    private Button btnNotViewed;
    @FXML
    private Button btnAll;
    NotificationService ns;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ns = new NotificationService();
        remplirHbox(AccueilController.listn);
        AccueilController.listNotViewedn = AccueilController.listn.stream().filter((n) -> n.getVu() == 0).collect(Collectors
                .toCollection(ArrayList::new));
        AccueilController.listViewedn = AccueilController.listn.stream().filter((n) -> n.getVu() != 0).collect(Collectors
                .toCollection(ArrayList::new));
        btnAll.setOnAction((event) -> {
            vbNotif.getChildren().clear();
            remplirHbox(AccueilController.listn);
        });
        btnNotViewed.setOnAction((event) -> {
            vbNotif.getChildren().clear();
            remplirHbox(AccueilController.listNotViewedn);
        });
        btnViewed.setOnAction((event) -> {
            vbNotif.getChildren().clear();
            remplirHbox(AccueilController.listViewedn);
        });
    }

    public HBox convertNotif(Notification n) {
        Label lbType = new Label("" + n.getType() + "");
        Label lbmsg = new Label("" + n.getMessage() + "");

        HBox.setMargin(lbType, new Insets(20, 20, 20, 50));
        HBox.setMargin(lbmsg, new Insets(20, 20, 20, 50));

        lbType.setPrefWidth(300);
        lbmsg.setPrefWidth(600);

        HBox NotifGraphique = new HBox();
        NotifGraphique.setAlignment(Pos.CENTER);
        NotifGraphique.setMaxHeight(Integer.MAX_VALUE);
        NotifGraphique.setMaxWidth(Integer.MAX_VALUE);
        NotifGraphique.setStyle("-fx-background-color: #ffffff;");
        NotifGraphique.getChildren().add(lbType);

        NotifGraphique.getChildren().add(lbmsg);

        if (n.getVu() == 0) {
            JFXButton BtnUpdate = new JFXButton();

            BtnUpdate.setText("Checked");
            BtnUpdate.setId(Integer.toString(n.getId_notif()));
            BtnUpdate.setStyle("-fx-background-color: #5858FA; -fx-background-radius: 0px;");
            BtnUpdate.setPrefWidth(200);

            HBox.setMargin(BtnUpdate, new Insets(20, 20, 20, 20));
            NotifGraphique.getChildren().add(BtnUpdate);
            BtnUpdate.setOnAction((event) -> {
                try {
                    ns.updateNotification(n);
                    AccueilController.listn = ns.getNotifications();
                    AccueilController.listNotViewedn = AccueilController.listn.stream().filter((notif) -> notif.getVu() == 0).collect(Collectors
                            .toCollection(ArrayList::new));
                    AccueilController.listViewedn = AccueilController.listn.stream().filter((notif) -> notif.getVu() != 0).collect(Collectors
                            .toCollection(ArrayList::new));
                    vbNotif.getChildren().clear();
                    remplirHbox(AccueilController.listn);
                } catch (Exception ex) {
                    System.out.println("exception here");
                    System.out.println(ex.getMessage());
                    Logger.getLogger(ClientsViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        return NotifGraphique;
    }

    public void remplirHbox(Collection<Notification> notifs) {
        System.out.println("here");
        for (Notification n : notifs) {
            vbNotif.getChildren().add(convertNotif(n));
        }
    }

}
