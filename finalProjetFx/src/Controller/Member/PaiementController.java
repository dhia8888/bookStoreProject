/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Member;

import Model.*;
import Services.CommandeService;
import Services.FactureService;
import Services.LivreServices;
import Services.NotificationService;
import Services.PanierService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.stripe.exception.StripeException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.Paiement;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class PaiementController implements Initializable {

    @FXML
    private BorderPane bpEffectuerPaiement;
    @FXML
    private Label labelTitre;
    @FXML
    private Label labelNomClient;
    @FXML
    private Button boutonAdd;
    @FXML
    private TextField tfNumCarte;
    @FXML
    private TextField tfCVC;
    @FXML
    private Button boutonCancel;
    @FXML
    private Label labelNumeroCarte;
    @FXML
    private Label labelDateExp;
    @FXML
    private Label labelCodeCVC;
    @FXML
    private ToolBar tbEffectuerPaiement;
    @FXML
    private Pane paneEP;
    @FXML
    private Pane paneEFP;
    @FXML
    private TextField tfMailClient;
    @FXML
    private String customerId;
    @FXML
    private String paiementId;
    @FXML
    private Label Montant;
    @FXML
    private Label lbErreur;
    @FXML
    private Label lbDateExpMois;
    @FXML
    private ComboBox<?> tfDateExpMois;
    @FXML
    private ComboBox<?> tfDateExpAnnee;

    public static void setSales(ArrayList<Panier> paniers) {
        PaiementController.paniers = paniers;
    }

    public static void setTotale(Double totale) {
        PaiementController.totale = totale;
    }

    private static Double totale;
    private static ArrayList<Panier> paniers;
    @FXML
    private Button btncmd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommandeService com  = new CommandeService();
        PanierService panierService = new PanierService();

        tfNumCarte.setOnKeyTyped(e -> {
            char input = e.getCharacter().charAt(0);
            if (Character.isDigit(input) != true) {
                e.consume();
            }
        });

        tfCVC.setOnKeyTyped(e -> {
            char input = e.getCharacter().charAt(0);
            if (Character.isDigit(input) != true) {
                e.consume();
            }
        });
        boutonAdd.setOnAction((event) -> {
            try {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                Commande comx = new Commande(String.valueOf(dtf.format(now)), 1, 3);
                com.AjouterCommande(paniers,comx);
            }catch (Exception e)
            {
                System.out.println("problem");
            }
            long a = Math.round(totale);
            if (isValidEmailAddress(tfMailClient.getText()) && tfNumCarte.getText().length() != 16 && tfCVC.getText().equals("") || tfCVC.getText().length() != 3) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Valider Dialog");
                alert.setHeaderText("Paiement effectué avec succés");
            } else {
                customerId = Paiement.ajouterCustomer(tfMailClient.getText().toString()
                );
                try {
                    paiementId = Paiement.ajouterMethodePaiement(tfNumCarte.getText().toString(), tfDateExpMois.getValue().toString(), tfDateExpAnnee.getValue().toString(), tfCVC.getText().toString());
                } catch (StripeException ex) {
                    Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Paiement.attacherCostumerPourMethodePaiement(customerId, paiementId);
                } catch (StripeException ex) {
                    Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    System.out.println(paniers);

                    for (Panier s : paniers) {
                        System.out.println("hani houni");
                        LivreServices ls = new LivreServices();
                        ArrayList<Livre> listL = ls.getLivres();
                        listL = listL.stream().filter(livre -> livre
                                .getTitre_livre().toLowerCase().equals(s.getTitreLiv().toLowerCase()))
                                .collect(Collectors
                                        .toCollection(ArrayList::new));
                        System.out.println(listL);
                        Livre l = listL.get(0);
                        l.setNb_exemplaires(l.getNb_exemplaires() - s.getNbrArticle());
                        System.out.println(l.getNb_exemplaires() - s.getNbrArticle());
                        System.out.println(l);
                            ls.updateLivreAttributs(l);
                        System.out.println(" problem update Livre ");
                    }
                    Paiement.effectuerPaiement(String.valueOf(a), "usd", customerId);
                    for (int i = 0; i < paniers.size(); i++) {
                        System.out.println(paniers.get(i).getId());
                        try {
                            panierService.deletePanier(paniers.get(i).getId());

                        } catch (Exception e) {
                            System.out.println("problem  panier delete ");
                        }
                    }
                    System.out.println(paniers);
                    Notification n = new Notification();
                    n.setType("payment en ligne");
                    n.setMessage("Un payement à était effectué enligne\navec un montant de: " + totale + " $");
                    n.setVu(0);
                    NotificationService ns = new NotificationService();
                    ns.addNotification(n);
                    try {
                        windowsClose(event);
                    } catch (Exception e) {
                        System.out.println("---------------------");
                    }

                } catch (StripeException ex) {
                    Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Valider Dialog");
                alert.setHeaderText("Paiement effectué avec succés");
                alert.showAndWait();
                facture(a);
            }
        });

        bpEffectuerPaiement.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Montant.setText("Montant à payer :" + totale + "$");
            }
        });
        boutonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Node source = (Node) event.getSource();
                    Window theStage = source.getScene().getWindow();
                    theStage.hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientsViewBooks.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    System.out.println("problem");
                }
            }
        });
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");
        ObservableList obList = FXCollections.observableList(list);
        tfDateExpMois.getItems().clear();
        tfDateExpMois.setItems(obList);
        List<String> alist = new ArrayList<String>();
        alist.add("2021");
        alist.add("2022");
        alist.add("2023");
        alist.add("2024");
        alist.add("2025");
        ObservableList obaList = FXCollections.observableList(alist);
        tfDateExpAnnee.getItems().clear();
        tfDateExpAnnee.setItems(obaList);
    }

    public void facture(long a) {
        String FactNum = "";
        FactureService factureService = new FactureService();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        CommandeService cs  = new CommandeService();

        try {
            CommandeService com = new CommandeService();
            Facture f = new Facture(String.valueOf(dtf.format(now)), a,cs.getCommanid());
            FactNum = "" + factureService.getFactureid();
           factureService.addFacture(f);
            Notification n = new Notification();
            n.setType("Command sur place");
            n.setMessage("Commande num: " + com.getCommanid() + " est effectuée !");
            n.setVu(0);
            NotificationService ns = new NotificationService();
            ns.addNotification(n);

        } catch (Exception e) {
            System.out.println("problem Facture");
        }
        String Facture = "client.pdf";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        Document document = new Document();
        PdfWriter writer;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(Facture));
            document.open();
            Font f1 = new Font(Font.FontFamily.UNDEFINED, 22, Font.BOLD);
            Font f3 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            Paragraph p1 = new Paragraph("BOOKSTORE BY CANNIBALS", f1);
            Paragraph p2 = new Paragraph("ESPRIT-VERMEG");
            Paragraph p3 = new Paragraph("+216 50 878 583\n\n\n  ");
            Paragraph p4 = new Paragraph("\n\n  Total: " + a + "$", f1);
            Paragraph p5 = new Paragraph("\nMerci pour votre visite !\nRetour ou échange impossible sans facture.\n\n\n");
            p1.setAlignment(Element.ALIGN_CENTER);
            p3.setAlignment(Element.ALIGN_CENTER);
            p2.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            document.add(p2);
            document.add(p3);
            Font f2 = new Font(Font.FontFamily.UNDEFINED, 8, Font.NORMAL);
            Phrase phrase = new Phrase("Date: " + dateFormat.format(date), f2);
            PdfContentByte canvas = writer.getDirectContent();
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, 40, 740, 0);

            Phrase FactNo = new Phrase((String.format("FACTURE N° %s", FactNum)), f2);
            PdfContentByte canv = writer.getDirectContent();
            ColumnText.showTextAligned(canv, Element.ALIGN_LEFT, FactNo, 510, 785, 0);
            Image image;
            try {
                image = Image.getInstance("http://127.0.0.1/logo.png");
                image.setAbsolutePosition(30f, 760f);
                document.add(image);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PdfPTable table = new PdfPTable(4);
            float[] columnWidths = new float[]{15f, 15f, 15f, 15f};
            table.setWidths(columnWidths);
            table.addCell("Produit");
            table.addCell("Prix unitaire");
            table.addCell("Quantité");
            table.addCell("Prix Totale");

            try {
                LivreServices livreServices = new LivreServices();
                for (int i = 0; i < paniers.size(); i++) {

                    for (int x = 0; x < 1; x++) {
                        insertCell(table, paniers.get(i).getTitreLiv(), Element.ALIGN_CENTER, 1, f3);
                    }
                    for (int x = 1; x < 2; x++) {
                        insertCell(table, livreServices.prixlivre(paniers.get(i).getTitreLiv()) + " $", Element.ALIGN_CENTER, 1, f3);
                    }
                    for (int x = 2; x < 3; x++) {
                        insertCell(table, paniers.get(i).getNbrArticle() + "", Element.ALIGN_CENTER, 1, f3);
                    }
                    for (int x = 3; x < 4; x++) {
                        insertCell(table, paniers.get(i).getTotal() + " $", Element.ALIGN_CENTER, 1, f3);
                    }
                }
            } catch (Exception E) {
                System.out.println(" problem facture pdf ");

            }
            document.add(table);
            document.add(p4);
            document.add(p5);
            PdfContentByte cb = writer.getDirectContent();
            Barcode128 code128 = new Barcode128();
            code128.setCodeType(code128.CODE128);
            code128.setCode("0021650878583");
            Image imageEAN = code128.createImageWithBarcode(cb, null, null);
            document.add(new Chunk(imageEAN, 0, 0));
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {

        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private void windowsClose(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        theStage.hide();
    }

}
