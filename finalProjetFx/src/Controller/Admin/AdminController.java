package Controller.Admin;

import Model.Facture;
import Services.StatistiquesServiceVente;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

public class AdminController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Button menu;
    @FXML
    private VBox drawer;
    @FXML
    private LineChart<String, Number> invoiceChart;
    @FXML
    CategoryAxis ixAxis;
    @FXML
    private BarChart<String, Double> productsChart;
    @FXML
    CategoryAxis pxAxis;
    @FXML
    private PieChart stockChart;
    StatistiquesServiceVente serviceAnalytique;
    @FXML
    Label Chiffre;
    @FXML
    Label livresNonVendu;
    @FXML
    Label LivresVendu;
    @FXML
    Label GnRapport;
    @FXML
    StackPane stackpane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceAnalytique = new StatistiquesServiceVente();
        loadStockChart();
        loadProductsChart();
        loadInvoiceChart();
        actionReport();
    }

    public void actionReport() {
        GnRapport.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String messageText = "";
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("do you want to send Report");
                alert.setHeaderText("Look,do you  send report ");
                alert.setContentText("Are you ok with this?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        NexmoClient client = new NexmoClient.Builder()
                                .apiKey("b959fe51")
                                .apiSecret("mIF5MMb0OkKeshPb")
                                .build();
                        messageText+="Rapport best sales Book store canibales  \n"+"Chiffre d'affaires  :"+serviceAnalytique.getRevenue()+"$ \n "+"Livres non vendu  : "+ serviceAnalytique.getLivreNonVendu() + " \n Livres vendu   " +serviceAnalytique.getLivreVendu();

                        TextMessage message = new TextMessage("Vonage APIs", "21699561896", messageText);
                        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
                        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
                            System.out.println(responseMessage);
                        }
                    } catch (Exception ex) {
                        System.out.println("probem  ");
                    }
                }
            }
        });
    }

    private void loadStockChart() {
        ObservableList<PieChart.Data> lists = FXCollections.observableArrayList();
        ArrayList<String> liste = new ArrayList<>();
        try {
            liste = serviceAnalytique.getStatCommandeByGenreLivre();
            while (liste.size() != 0) {
                String word = liste.get(0);
                int frequency = Collections.frequency(liste, word);
                liste.removeAll(Collections.singleton(word));
                lists.add(new PieChart.Data(word, frequency));
            }
            stockChart.getData().addAll(lists);
            Chiffre.setText(serviceAnalytique.getRevenue() + "" + "$");
            livresNonVendu.setText(serviceAnalytique.getLivreNonVendu() + "");
            LivresVendu.setText(serviceAnalytique.getLivreVendu() + "");
            System.out.println(livresNonVendu.getText());
            System.out.println(LivresVendu.getText());

        } catch (Exception e) {
            System.out.println(" problem pie chart ");
        }
    }

    private void loadProductsChart() {
        ObservableList lists = FXCollections.observableArrayList();
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        ArrayList<String> liste = new ArrayList<>();
        try {
            liste = serviceAnalytique.getStatCommandeByAuteurLivre();
            while (liste.size() != 0) {
                String word = liste.get(0);
                int frequency = Collections.frequency(liste, word);
                liste.removeAll(Collections.singleton(word));
                series.getData().add(new XYChart.Data(word, frequency));
                lists.add(word);
            }
            series.setName("Auteurs");
            pxAxis.setCategories(lists);
            productsChart.getData().add(series);
        } catch (Exception e) {
            System.out.println(" problem bar chart ");
        }
    }

    private void loadInvoiceChart() {
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        ObservableList lists = FXCollections.observableArrayList(months);
        XYChart.Series series = new XYChart.Series();
        ArrayList<Facture> factures = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("u/M/d");

        try {
            factures = serviceAnalytique.getSales();
            for (Facture f : factures) {
                LocalDate date = LocalDate.parse(f.getDate_fact(), dateFormatter);
                String monthName = date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
                series.getData().add(new XYChart.Data(monthName, f.getMontant()));
            }
            series.setName("Sales");
            ixAxis.setCategories(lists);
            invoiceChart.getData().add(series);
        } catch (Exception e) {
            System.out.println("problem  chart ");
        }
    }

    @FXML
    public void menu(ActionEvent event) throws Exception {
        windows("/view/dashboard.fxml", "menu", event);
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
    }
}
