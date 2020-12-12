package Controller.Admin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Gerant;
import Services.UserService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class UserManagementController implements Initializable{
	
	@FXML
    private TextField txt_nom;
	@FXML
    private TextField txt_prenom;
	@FXML
    private TextField txt_username;
	@FXML
    private TextField txt_password;
	@FXML
    private TextField txt_email;
	@FXML
    private TextField txt_adresse;
	@FXML
    private TextField txt_rib;
	@FXML
    private TextField txt_salaire;
	@FXML
    private TextField txt_matricule;


    @FXML
    private Button updateButton;

    @FXML
    private Button addManager;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Gerant> TableView;
    
    @FXML
    private TableColumn<Gerant, String> nomColumn;
    
    @FXML
    private TableColumn<Gerant, String> prenomColumn;
    
    @FXML
    private TableColumn<Gerant, String> usernameColumn;
    
    @FXML
    private TableColumn<Gerant, String> passwordColumn;
    
    @FXML
    private TableColumn<Gerant, String> emailColumn;
    
    @FXML
    private TableColumn<Gerant, String> adresseColumn;
    
    @FXML
    private TableColumn<Gerant, String> ribColumn;
    
    @FXML
    private TableColumn<Gerant, String> salaireColumn;
    
    @FXML
    private TableColumn<Gerant, String> matriculeColumn;
    
    @FXML
    private TableColumn<Gerant, String> roleColumn;
    
    @FXML
    private TableColumn colAction;
    
    private String statusCode,statusClick;

    UserService us = new UserService();
    
    @Override
    public void initialize(URL location, ResourceBundle resources)  {
    	try {
    	showUsers();
    	} catch (SQLException e ) {



    		
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
    
    private void clear(){
    	txt_nom.clear();
    	txt_prenom.clear();
    	txt_username.clear();
    	txt_password.clear();
    	txt_email.clear();
    	txt_adresse.clear();
    	txt_password.clear();
    	txt_rib.clear();
    	txt_salaire.clear();
    	txt_matricule.clear();
        statusCode = "0";
    }
    
    public void showUsers() throws SQLException{
    	ObservableList<Gerant> list = us.getGerants();
    	System.out.println(us.getGerants());    	
    	
    	nomColumn.setCellValueFactory(new PropertyValueFactory<Gerant,String>("nom"));
    	prenomColumn.setCellValueFactory(new PropertyValueFactory<Gerant,String>("prenom"));
    	usernameColumn.setCellValueFactory(new PropertyValueFactory<Gerant,String>("username"));
    	passwordColumn.setCellValueFactory(new PropertyValueFactory<Gerant,String>("password"));
    	emailColumn.setCellValueFactory(new PropertyValueFactory<Gerant,String>("email"));
    	adresseColumn.setCellValueFactory(new PropertyValueFactory<Gerant,String>("adresse"));
    	ribColumn.setCellValueFactory(new PropertyValueFactory<Gerant,String>("rib"));
    	salaireColumn.setCellValueFactory(new PropertyValueFactory<Gerant,String>("salaire"));
    	matriculeColumn.setCellValueFactory(new PropertyValueFactory<Gerant,String>("matricule"));
    	roleColumn.setCellValueFactory(new PropertyValueFactory<Gerant,String>("role"));
	
    	TableView.setItems(list);
    }
    
    @FXML
    private void tableDataClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            statusCode = "1";
            try {
                Gerant click = TableView.getSelectionModel().getSelectedItems().get(0);
                txt_nom.setText(click.getNom());
                txt_prenom.setText(click.getPrenom());
                txt_username.setText(click.getUsername());
                txt_password.setText(click.getPassword());
                txt_email.setText(click.getEmail());
                txt_adresse.setText(click.getAdresse());
                txt_rib.setText(click.getRib());
                txt_salaire.setText(click.getSalaire()+"" );
                //System.out.println(click.getSalaire());
                txt_matricule.setText(click.getMatricule());
               
            } catch (Exception e) {
            }
        }  
    }





    
    @FXML
    private void DeleteGerant() throws SQLException{
        Window owner = deleteButton.getScene().getWindow();

    	Gerant click = TableView.getSelectionModel().getSelectedItems().get(0);
    	if (click == null) {
     		 showAlert(Alert.AlertType.ERROR, owner,"Error", "Select a manager first!");
   	}else {
    	Gerant g = new Gerant (click.getId(), click.getNom(), click.getPrenom(), click.getUsername(),click.getPassword(),
    			click.getEmail(),click.getAdresse(),click.getRole(), click.getRib(),click.getSalaire(),click.getMatricule());
    	
    	us.deleteUser(g);
    	showUsers();
    	clear();      	
    	 showAlert(Alert.AlertType.CONFIRMATION, owner,"Sucess", "Manager" +txt_nom.getText()+" deleted successfully!");
   	}
 
    }
    
    @FXML
    private void UpdateGerant() throws SQLException {
    	
    	 Window owner = deleteButton.getScene().getWindow();

     	Gerant click = TableView.getSelectionModel().getSelectedItems().get(0);
     	if (click == null) {
      		 showAlert(Alert.AlertType.ERROR, owner,"Error", "Select a manager first!");
    	}else {
     	Gerant g = new Gerant (click.getId(), txt_nom.getText(),txt_prenom.getText(), txt_username.getText(),txt_password.getText(),
     			txt_email.getText(),txt_adresse.getText(),click.getRole(), txt_rib.getText(),click.getSalaire(),txt_matricule.getText());
     	
     	us.modifierGerant(g);
     	showUsers();
     	clear();      	
     	 showAlert(Alert.AlertType.CONFIRMATION, owner,"Sucess", "Manager" +txt_nom.getText()+" Updated successfully!");
    	}
  
    	
    }

    private void windowsClose(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        theStage.hide();
    }

    public void AddGerant(ActionEvent actionEvent) {
        try {
            Node source = (Node) actionEvent.getSource();
            Window theStage = source.getScene().getWindow();
            theStage.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUp.fxml"));
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

    public void backToMenu(ActionEvent actionEvent) {

        try {
            Node source = (Node) actionEvent.getSource();
            Window theStage = source.getScene().getWindow();
            theStage.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ManagerView.fxml"));
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
