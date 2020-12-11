package Controller.Admin;

import Model.Notification;
import Services.NotificationService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

public class AccueilController implements Initializable {

    @FXML
    private Button BtnExit;
    @FXML
    private AnchorPane content;
    @FXML
    private Button Guser;
    @FXML
    private Button Glivres;
    @FXML
    private Button Grapport;
    @FXML
    private Button Gcom;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button btnNotif;
    public static ArrayList<Notification> listn;
    public static ArrayList<Notification> listViewedn;
    public static ArrayList<Notification> listNotViewedn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NotificationService ns = new NotificationService();
        try {
            listn = ns.getNotifications();
            listNotViewedn = listn.stream().filter((n) -> n.getVu() == 0).collect(Collectors
                    .toCollection(ArrayList::new));

            int notViewed = listNotViewedn.size();
            btnNotif.setText(notViewed + "");

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnNotif.setOnAction((event) -> {
            try {
                LoadNotif(event);
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        listn = ns.getNotifications();
                        listNotViewedn = listn.stream().filter((n) -> n.getVu() == 0).collect(Collectors
                                .toCollection(ArrayList::new));

                        int notViewed = listNotViewedn.size();
                        btnNotif.setText(notViewed + "");
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

            }
        }, 0, 2000);
    }

    @FXML
    private void LoadGUContent(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/Usermanagement.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void LoadGLContent(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/AdminViewBooks.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void LoadGRContent(ActionEvent event) throws IOException {
        //Parent fxml = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));

        // content.getChildren().removeAll();
        //content.getChildren().setAll(fxml);
        try {
            windowsClose(event);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void LoadGCContent(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/AdminGestionCommande.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void ExitWindow(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    private void windowsClose(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        theStage.hide();
    }

    @FXML
    private void LoadNotif(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/notifications.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

}
