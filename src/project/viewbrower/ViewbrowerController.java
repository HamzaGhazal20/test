/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.viewbrower;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class ViewbrowerController implements Initializable {

     @FXML
    private TableView<databorrow> tableview;
    @FXML
    private TableColumn<databorrow, Integer> tcbookid;
    @FXML
    private TableColumn<databorrow, Integer> tcmemberid;
    @FXML
    private TableColumn<databorrow, String> tcdate;
    @FXML
    private TableColumn<databorrow, String> tctime;
Statement statement;
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
            // TODO
        } catch (Exception ex) {
            System.out.println(ex);
        }
             tcbookid.setCellValueFactory(new PropertyValueFactory("bookid"));
             tcmemberid.setCellValueFactory(new PropertyValueFactory("memberid"));
                tcdate.setCellValueFactory(new PropertyValueFactory("dateborrow"));                
                   tctime.setCellValueFactory(new PropertyValueFactory("retunetime"));
        try {
              Display();
        } catch (Exception ex) {
            System.out.println(ex);
        }
     
    }   
    
     private void Display() throws Exception{
     
      ResultSet rs = this.statement.executeQuery("Select * From borrow");
        tableview.getItems().clear();
        while(rs.next()){
            databorrow Databorrow=new databorrow();
            Databorrow.setBookid(rs.getInt("bookid"));
            Databorrow.setMemberid(rs.getInt("memberid"));
            Databorrow.setDateborrow(rs.getString("da"));
            Databorrow.setRetunetime(rs.getString("returntime"));

            tableview.getItems().add(Databorrow);
        }
    }    
    

}
