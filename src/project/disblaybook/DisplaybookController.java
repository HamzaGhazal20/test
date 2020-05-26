/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.disblaybook;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author LapCity
 */
public class DisplaybookController implements Initializable {

    @FXML
    private TableColumn<databook, Integer> tcid;
    @FXML
    private TableColumn<databook, String> tctitle;
    @FXML
    private TableColumn<databook, String> tcauthor;
    @FXML
    private TableColumn<databook, String> tcdate;
    @FXML
    private TableColumn<databook, String> tcavilabil;
    @FXML
    private TableView<databook> tableviwe;
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
             tctitle.setCellValueFactory(new PropertyValueFactory("title"));
                tcauthor.setCellValueFactory(new PropertyValueFactory("author"));                
                   tcdate.setCellValueFactory(new PropertyValueFactory("date"));
                      tcavilabil.setCellValueFactory(new PropertyValueFactory("avilabel"));
        try {
              Display();
        } catch (Exception ex) {
            System.out.println(ex);
        }
     
    }   
    
    
      
       
        // TODO

    @FXML
    private void delet(ActionEvent event) throws Exception {
          databook selected= tableviwe.getSelectionModel().getSelectedItem();
        if(selected==null){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Selected Item");
            alert.setContentText("No selected any item");
            alert.setHeaderText(null);
            alert.showAndWait();
          
        }else{
            
            int id=selected.getId();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delet book");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want delet the book " + selected.getTitle() + " ? " );

            Optional<ButtonType> x = alert.showAndWait();
            if (x.get() == ButtonType.OK) {
               
                try {
                    String qu = "Delete from databook where id=" + id;
                    this.statement.executeUpdate(qu);
                  Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Delet book");
            alert1.setHeaderText(null);
            alert1.setContentText("sucsses Delet");
             alert1.showAndWait();
                LocalDate date=LocalDate.now();
           
            myWriter.write("\n");
              myWriter.write("The book ID number "+id+" has been deleted In the history of: "+date);
              myWriter.close();
            
            Display();
                } catch (SQLException ex) {
                 Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Delet book");
            alert1.setHeaderText(null);
            alert1.setContentText("fild Delet");
            alert1.showAndWait();
                }
                    
                }
            
        }
    }
     private void Display() throws Exception{
     
      ResultSet rs = this.statement.executeQuery("Select * From databook");
        tableviwe.getItems().clear();
        while(rs.next()){
            databook  dataBook = new databook();
            dataBook.setId(rs.getInt("id"));
            dataBook.setTitle(rs.getString("title"));
            dataBook.setAuthor(rs.getString("author"));
            dataBook.setDate(rs.getString("publisher"));
            dataBook.setAvilabel(rs.getBoolean("isavail"));
            tableviwe.getItems().add(dataBook);
        }
     }
    }    

    

