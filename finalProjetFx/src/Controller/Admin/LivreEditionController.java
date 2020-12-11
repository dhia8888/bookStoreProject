package Controller.Admin;

import Services.StatistiquesServiceStock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class LivreEditionController implements Initializable {

    @FXML
    private BarChart<String, Double> barchart;
    @FXML
    private CategoryAxis editionax;
    @FXML
    private NumberAxis livresax;
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
            liste = StatistiquesServiceStock.getLivreEditionInfo();
            while (liste.size() != 0)
            {
                String word = liste.get(0);
                int frequency = Collections.frequency(liste, word);
                liste.removeAll(Collections.singleton(word));
                series.getData().add(new XYChart.Data(word,frequency));
                lists.add(word);
            }

            editionax.setCategories(lists);
            barchart.getData().add(series);
        }catch (Exception e)
        {
            System.out.println(" problem barchart ");
        }
    }
}
