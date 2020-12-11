package Controller.Admin;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.SaltedMD5Example;


public class LoginController {
	
	@FXML
	private TextField emailUser;
	
	@FXML
	private PasswordField txt_password;
	
	@FXML
	private Button login_button;
	
	@FXML
	private Button Reset_button;
	
	@FXML
    public void login(ActionEvent event) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException {

        Window owner = login_button.getScene().getWindow();

        if (emailUser.getText().isEmpty()) {
            showAlert(AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
            return;
        }
        if (txt_password.getText().isEmpty()) {
            showAlert(AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String email = emailUser.getText();
        String password = txt_password.getText();
        System.out.println(password);


        UserService us = new UserService();
        String role = us.getRole(email);

        boolean flag = us.Login(email, password);
        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else if ((flag) && (role.equals("Gerant"))) {
            {
                infoBox("Login Successful!", null, "Failed");

                Node source = (Node) event.getSource();
                Window theStage = source.getScene().getWindow();
                theStage.hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/accueil2.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        } else if ((flag) && (role.equals("Admin"))) {
            infoBox("Login Successful!", null, "Failed");

            Node source = (Node) event.getSource();
            Window theStage = source.getScene().getWindow();
            theStage.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/accueil.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (flag) {

            Node source = (Node) event.getSource();
            Window theStage = source.getScene().getWindow();
            theStage.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientsViewBooks.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    private static void showAlert(AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

	@FXML
	public void ResetPassword(ActionEvent event) throws SQLException {
		
        Window owner = Reset_button.getScene().getWindow();
        
        if (emailUser.getText().isEmpty()) {
            showAlert(AlertType.ERROR, owner, "Form Error!",
                "Please enter your email ");
            return;
        }else {
        infoBox("Your password has been reset, check your email!", null, "Failed");
		String email = emailUser.getText();
        UserService us = new UserService();
        us.resetPassword(email);
        try {
            Node source = (Node) event.getSource();
            Window theStage = source.getScene().getWindow();
            theStage.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e)
        {

        }

        }
	}
    @FXML
    public void ForgetAction(ActionEvent event) {

	    try {


            Node source = (Node) event.getSource();
            Window theStage = source.getScene().getWindow();
            theStage.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ResetPassword.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e)
        {
            System.out.println(" restttt");
        }
    }


    @FXML
    public void back_to_login(ActionEvent event){

    }

}
