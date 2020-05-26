/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.display_member;

import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import project.disblaybook.databook;

/**
 * FXML Controller class
 *
 * @author LapCity
 */
public class Display_memberController implements Initializable {

    @FXML
    private TableView<datamember> tableview;
    @FXML
    private TableColumn<datamember, Integer> tcid;
    @FXML
    private TableColumn<datamember, String> tcname;
    @FXML
    private TableColumn<datamember ,String> tcmobile;
    @FXML
    private TableColumn<datamember, String> tcemail;
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
             tcid.setCellValueFactory(new PropertyValueFactory("id"));
             tcname.setCellValueFactory(new PropertyValueFactory("name"));
                tcmobile.setCellValueFactory(new PropertyValueFactory("mobile"));                
                   tcemail.setCellValueFactory(new PropertyValueFactory("email"));
        try {
              Display();
        } catch (Exception ex) {
            System.out.println(ex);
        }
     
    }   
    
     private void Display() throws Exception{
     
      ResultSet rs = this.statement.executeQuery("Select * From datamember");
        tableview.getItems().clear();
        while(rs.next()){
            datamember  dataMember = new datamember();
            dataMember.setId(rs.getInt("id"));
            dataMember.setName(rs.getString("name"));
            dataMember.setMobile(rs.getString("mobile"));
            dataMember.setEmail(rs.getString("email"));

            tableview.getItems().add(dataMember);
        }
     }

    @FXML
    private void Delet(ActionEvent event) throws Exception {
          datamember selected= tableview.getSelectionModel().getSelectedItem();
        if(selected==null){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Selected member");
            alert.setContentText("No selected any item");
            alert.setHeaderText(null);
            alert.showAndWait();
          
        }else{
            
            int id=selected.getId();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delet member");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want delet the member " + selected.name + " ? " );

            Optional<ButtonType> x = alert.showAndWait();
            if (x.get() == ButtonType.OK) {
               
                try {
                    String qu = "Delete from datamember where id=" + id;
                    this.statement.executeUpdate(qu);
                  Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Delet member");
            alert1.setHeaderText(null);
            alert1.setContentText("sucsses Delet");
                LocalDate date=LocalDate.now();
           
            myWriter.write("\n");
                          myWriter.write("The member ID number "+id+" has been deleted In the history of: "+date);
           myWriter.close();
            alert1.showAndWait();
              Display();
                } catch (SQLException ex) {
                 Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Delet member");
            alert1.setHeaderText(null);
            alert1.setContentText("fild Delet");
            alert1.showAndWait();
                }
                    
                }
            
        }
    }
      
    }    
    

