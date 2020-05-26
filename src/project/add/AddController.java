/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author LapCity
 */
public class AddController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField author;
  
    @FXML
    private Button save;
    @FXML
    private Button cancel;
        Statement statement;
    @FXML
    private JFXDatePicker data;
    FileWriter myWriter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project","root","");
            
            this.statement = connection.createStatement();
            myWriter = new FileWriter("filename.txt",true);
           
            // TODO
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }    
    @FXML
    private void saveHandel(ActionEvent event) throws IOException{
      try {
        if(id.getText().equals("")||title.getText().equals("")|| author.getText().equals("")||data.getValue()==null){
                Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("full data");
            alert.showAndWait();
            
        }else{
            int ID = Integer.parseInt(id.getText());
            String Title = title.getText();
            String Author = author.getText();
            String  Publisher =  data.getValue()+"";
            String sql = "Insert Into databook(id,title,author,publisher,isavail) values(" + ID + ",'" +Title + "','"
                    + Author + "','" + Publisher +"','" + 1 + "')";
            this.statement.executeUpdate(sql);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("ADD sucsess");
           LocalDate date=LocalDate.now();
            alert.showAndWait();
            myWriter.write("\n");
              myWriter.write("A data owner's book has been added ID: "+ ID+" Title: "+ Title +" Author: "+Author +" Publisher: "+ Publisher +" In the history of: "+date);
             
      myWriter.close();
            reset();
                 }
        } catch (SQLException ex) {
             Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Erorr");
            alert.showAndWait();
           
        }
      
    }

    @FXML
    private void cancelHandel(ActionEvent event) {
        System.exit(0);
       
    }
    
    private  void reset(){
        id.setText("");
          title.setText("");
        author.setText("");
      

    }
}
