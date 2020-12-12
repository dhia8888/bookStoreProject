package Controller.Admin;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.Member.PaiementController;
import Controller.Member.UpdatePanierController;
import Model.Gerant;

import Services.UserService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.SaltedMD5Example;

public class SignUpController  implements Initializable {
	
	@FXML
    private TextField txt_name;
	@FXML
    private TextField txt_lastname;
	@FXML
    private TextField txt_email;
	@FXML
    private TextField txt_address;		
	@FXML
    private TextField txt_rib;
	@FXML
    private TextField txt_matricule;
	@FXML
    private TextField txt_salaire;
	@FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button addUser_button;
    
    private String statusCode,statusClick;


    @FXML
    public void addGerant(ActionEvent event) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException  {

        Window owner = addUser_button.getScene().getWindow();

        System.out.println(txt_name.getText());
        System.out.println(txt_email.getText());
        System.out.println(txt_address.getText());
        
        if (txt_name.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter the name");
            return;
        }
        if (!(validate1(txt_name.getText()))){
      	  showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Name must contain only letters");
                return;
      }

        if (txt_lastname.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter the lastname");
            return;
        }
        
        if (!(validate1(txt_lastname.getText()))){
        	  showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                      "LastName must contain only letters");
                  return;
        }
        if (txt_email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter the Email");
            return;
        }
        if (!(validate(txt_email.getText()))){
        	  showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                      "Email Incorrect");
                  return;
        }
        
        if (txt_address.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter the adress");
            return;
        }
        
        if (txt_rib.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter the rib");
            return;
        }
        if (!(validate3(txt_rib.getText()))){
      	  showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Rib must containt only numbers");
                return;
      }
        if (txt_matricule.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter the matricule");
            return;
        }
        if (txt_salaire.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a salary");
            return;
        }
        if (!(validate4(txt_salaire.getText()))){
        	  showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                      "Salary must containt only numbers");
                  return;
        }
        if (txt_password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }
        
        if (txt_username.getText().isEmpty() || txt_username.getText().length() < 5) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Username must be at least 5 characters");
            return;
        }
        
        if (!(validate2(txt_username.getText()))){
      	  showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Username can only contain, letters, numbers, @, - or _");
                return;
      }

        txt_salaire.setOnKeyTyped(e -> {
            char input = e.getCharacter().charAt(0);
            if (Character.isDigit(input) != true) {
                e.consume();
            }
        });
        
        String salt = SaltedMD5Example.getSalt();
        String prenom = txt_name.getText();
        String nom = txt_lastname.getText();
        String username = txt_username.getText();
        String email = txt_email.getText();
        String adresse = txt_address.getText();
        String rib = txt_rib.getText();
        String matricule = txt_matricule.getText();
        Double salaire = Double.parseDouble(txt_salaire.getText());


        String password = SaltedMD5Example.getSecurePassword(txt_password.getText(), salt);
        String role = "";

        Gerant g = new Gerant (nom, prenom,username, password, email, adresse,role, rib,salaire,matricule);
        
        UserService us = new UserService();
        if (us.UserExist(email) == false) {
        us.addGerant(g);
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
            "Welcome " + txt_name.getText());
        clear();
        try {

            windowsClose(event);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserManagement.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e)
        {

        }

        }else {
        	showAlert(Alert.AlertType.ERROR, owner, "User Already exist with that Email!","Try another email");
        }
    }
    @FXML
    private  void navBack (ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserManagement.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e)
        {
            System.out.println("problem");
        }
    }


    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    public static final Pattern VALIDEMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALIDNAMELASTNAME = Pattern.compile("^[A-Z]", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALIDUSERNAME = Pattern.compile("^[A-Z0-9._-]", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALIDRIB = Pattern.compile("^[0-9]", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALIDSALAIRE = Pattern.compile("^[0-9]", Pattern.CASE_INSENSITIVE);

        public static boolean validate(String txt) {
                Matcher matcher = VALIDEMAIL.matcher(txt);
                return matcher.find();
        }       
        public static boolean validate1(String txt) {
            Matcher matcher = VALIDNAMELASTNAME.matcher(txt);
            return matcher.find();
        }        
        public static boolean validate2(String txt) {
            Matcher matcher = VALIDUSERNAME.matcher(txt);
            return matcher.find();
        }        
        public static boolean validate3(String txt) {
            Matcher matcher = VALIDRIB.matcher(txt);
            return matcher.find();
        }            
        public static boolean validate4(String txt) {
            Matcher matcher = VALIDSALAIRE.matcher(txt);
            return matcher.find();
        }    

        private void clear(){
        	txt_name.clear();
        	txt_lastname.clear();
        	txt_username.clear();
        	txt_password.clear();
        	txt_email.clear();
        	txt_address.clear();
        	txt_password.clear();
        	txt_rib.clear();
        	txt_salaire.clear();
        	txt_matricule.clear();
            statusCode = "0";
        }

    private void windowsClose(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        theStage.hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void navback(ActionEvent actionEvent) {

        try {
            Node source = (Node) actionEvent.getSource();
            Window theStage = source.getScene().getWindow();
            theStage.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserManagement.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e)
        {
            System.out.println("problem");
        }


    }
}



