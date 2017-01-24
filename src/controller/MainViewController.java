/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Client;


/**
 *
 * @author maxime
 */
public class MainViewController implements Initializable {
    
    @FXML
    private JFXTextField URL;
    
    @FXML
    private WebView webView;
    
    @FXML
    private JFXTextArea content;
    
    private WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = webView.getEngine();
        webEngine.locationProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            URL.setText(newValue);
            String requete = URL.getText().startsWith("http://") || URL.getText().startsWith("https://") ? URL.getText() : "http://" + URL.getText();
            Client c = Client.getInstance();
            String msg = c.connexion(requete);
            content.setText(msg);
        });
        URL.setText("http://www.google.ca");
        this.onSearch();
    }
    
    
    @FXML
    private void onSearch () {
        String requete = URL.getText().startsWith("http://") || URL.getText().startsWith("https://") ? URL.getText() : "http://" + URL.getText();
        Client c = Client.getInstance();
        String msg = c.connexion(requete);
        
        content.setText(msg);
        webEngine.load(requete);
    }
    
}
