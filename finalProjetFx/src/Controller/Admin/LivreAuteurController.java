package Controller.Admin;

import Services.StatistiquesServiceStock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class LivreAuteurController implements Initializable {
    @FXML
    private LineChart linechart;
    @FXML
    CategoryAxis nomauteur;
    @FXML
    NumberAxis nblivre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StatistiquesServiceStock ss = new StatistiquesServiceStock();
        loadLivreBarChart();
    }
    private void loadLivreBarChart()
    {
        ObservableList lists = FXCollections.observableArrayList();
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        ArrayList<String> liste = new ArrayList<>();
        try {
            liste = StatistiquesServiceStock.getLivreAuteurInfo();
            while (liste.size() != 0)
            {
                String word = liste.get(0);
                int frequency = Collections.frequency(liste, word);
                liste.removeAll(Collections.singleton(word));
                series.getData().add(new XYChart.Data(word,frequency));
                lists.add(word);
            }

            nomauteur.setCategories(lists);
            linechart.getData().add(series);
        }catch (Exception e)
        {
            System.out.println(" problem line chart ");
        }
    }
}