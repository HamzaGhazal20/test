/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField user;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private Label labe;
    Statement statement;
 Stage primaryStage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project?serverTimezone=UTC","root","");
            
            this.statement = connection.createStatement();
            // TODO
        } catch (Exception ex) {
            System.out.println(ex);
        }
          }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
          ResultSet rs = this.statement.executeQuery("Select * From admin");
          while (rs.next()) {
             String usern=rs.getString("username");
             String passw=rs.getString("Password");
             if(user.getText().equals(usern) && pass.getText().equals(passw)){
                Pane  pane = FXMLLoader.load(getClass().getResource("/project/main/main.fxml"));
                   Scene scene=new Scene(pane);
                      primaryStage =new Stage();
                primaryStage.setTitle("Hello World!");
           
                        ((Stage)user.getScene().getWindow()).close();
              
                primaryStage.setScene(scene);
               primaryStage.show();
             }else{
                 labe.setText("Username or passwor invalid ");
             }
            
        }
    }
    
}
