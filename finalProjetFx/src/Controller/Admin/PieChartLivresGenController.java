/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Services.StatistiquesServiceStock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class PieChartLivresGenController implements Initializable {
    @FXML
    private PieChart PiechartLivres;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int rm= 0, ra= 0, rd= 0, rh = 0;
        StatistiquesServiceStock ss= new StatistiquesServiceStock();
        try {
            rm = ss.getNbrLivreRomance();
            ra = ss.getNbrLivreAction();
            rd = ss.getNbrLivreDrama();
            rh = ss.getNbrLivreHorror();
        } catch (SQLException ex) {

            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<PieChart.Data> pieChartData=FXCollections.observableArrayList(
                new PieChart.Data("Romance",rm),
                new PieChart.Data("Action",ra),
                new PieChart.Data("Drama",rd),
                new PieChart.Data("Horror",rh));
        PiechartLivres.setData(pieChartData);


    }

}




