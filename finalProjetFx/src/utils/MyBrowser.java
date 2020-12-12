package utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

import java.net.URL;

public class MyBrowser extends Pane {
    double lat;
    double lon;
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();


    public MyBrowser(Double a,Double b) {


        final URL urlGoogleMaps = getClass().getResource("map.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> e) {
                System.out.println(e.toString());
            }
        });

        getChildren().add(webView);

        //final TextField latitude = new TextField("" + 35.857908 * 1.00007);
        //final TextField longitude = new TextField("" + 10.598997 * 1.00007);

        Button update = new Button("View");
        update.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                lat = a * 1.00007;
                lon = b * 1.00007;

                System.out.printf("%.2f %.2f%n", lat, lon);

                webEngine.executeScript("" +
                        "window.lat = " + lat + ";" +
                        "window.lon = " + lon + ";" +
                        "document.goToLocation(window.lat, window.lon);"
                );
            }
        });

        HBox toolbar = new HBox();
        toolbar.getChildren().addAll(update);

        getChildren().addAll(toolbar);

    }
}
