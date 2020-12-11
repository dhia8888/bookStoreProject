package Controller.Admin;
import Services.StatistiquesServiceStock;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DashboardController  implements Initializable {
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;
    @FXML
    private Label nbLivres;
    @FXML
    private Label nbAuteurs;
    @FXML
    private Label nbCmd;
    @FXML
    private Label ca;
    @FXML
    private Label nbEdition;
    @FXML
    private Button btnExit;
    @FXML
    private AnchorPane content;
    @FXML
    private Button ventes;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {


        int livrenb=0;
        StatistiquesServiceStock ss= new StatistiquesServiceStock();
        try {
            livrenb=ss.getNbrBook();
            nbLivres.setText(Integer.toString(livrenb));
        } catch (SQLException ex)
        {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int auteurnb=0;
        try {
            auteurnb=ss.getNbrAuteur();
            nbAuteurs.setText(Integer.toString(auteurnb));
        } catch (SQLException ex) {

            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int cmdn=0;
        try {
            cmdn=ss.getNbrCommande();
            nbCmd.setText(Integer.toString(cmdn));
        } catch (SQLException ex)
        {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        int editionnb=0;
        try {
            editionnb=ss.getNbrEdition();
            nbEdition.setText(Integer.toString(editionnb));
        } catch (SQLException ex) {

            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ventes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    windows("/view/Admin.fxml", "Sales", event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @FXML
    private void LoadPiechart(ActionEvent event) throws IOException {
        try
        {
            windows("/view/PieChartLivresGen.fxml", "menu", event);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }@FXML
    private void LoadPiechartinContent(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("/view/PieChartLivresGen.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }
    @FXML
    private void LoadBookInfoContent(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/ListeLivres.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }
    @FXML
    private void LoadLivreEditionContent(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/LivreEdition.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }

    @FXML
    private void LoadLivreAuteurContent(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/LivreAuteur.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }

    @FXML
    private void ExitWindow(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }


    @FXML
    private void handleClicks(ActionEvent event) throws IOException {

    }
    @FXML
    private void ReturnToOverview(ActionEvent event) throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("/view/ManagerView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
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
    }@FXML
    private void genPDF(ActionEvent event) throws FileNotFoundException, DocumentException {
            // 1. Create document
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            // 2. Create PdfWriter
            PdfWriter.getInstance(document, new FileOutputStream("mwalem.pdf"));
            // 3. Open document
            document.open();
            // 4. Add content
            document.add(new Paragraph("ayoub lebes alih ayoub lebes alih"));
            // 5. Close document
            document.close();
        }
}
