/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bhk
 */
public class MyConnection {

    private String url = "jdbc:mysql://127.0.0.1:3306/bookstore";
    private String login = "root";
    private String password = "";

    private Connection connection;

    static MyConnection instance;

    private MyConnection() {
        try {
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

}
