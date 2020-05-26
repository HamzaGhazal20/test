/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.add_member;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author LapCity
 */
public class Add_memberController implements Initializable {

   
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField mobile;
    @FXML
      private JFXTextField id;
    @FXML
    private JFXTextField email;
    @FXML
     private JFXButton save;
    @FXML
    private JFXButton cancel;
  Statement statement;
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
    private void saveHandel(ActionEvent event)throws  Exception{
          try {
               
               if(id.getText().equals("")||email.getText().equals("")|| name.getText().equals("")||mobile.getText().equals("")){
                       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("full data");
            alert.showAndWait();
               }
               else{
               
                   int ID = Integer.parseInt(id.getText());
            String Name = name.getText();
            String Mobile = mobile.getText();
             String  Email =  email.getText();          
                   String sql = "Insert Into datamember(id ,name ,mobile ,email) values(" + ID + ",'" +Name + "','" 
                + Mobile + "','" + Email + "')";
                      this.statement.executeUpdate(sql);
                         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("ADD sucsess");
             
            alert.showAndWait();
             LocalDate date=LocalDate.now();
            myWriter.write("\n");
            myWriter.write("A member with data has been added ID: "+ID +" Name: "+Name +" Mobile: "+Mobile +" Email: "+Email+" In the history of: "+date);
            myWriter.close();
            reset();
            
               }
         
        } catch (SQLException ex) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Erorr");
            alert.showAndWait();
        
        }
       
    }


    
   
    

        
      private  void reset(){
        id.setText("");
          name.setText("");
        mobile.setText("");
        email.setText("");

    }

    @FXML
    private void cancelHandel(ActionEvent event) {
    }
}
