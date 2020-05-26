/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.main;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LapCity
 */
public class MainController implements Initializable {

    @FXML
    private HBox bookinfo;
    @FXML
    private HBox memberinfo;
    @FXML
    private Text bookname;
    @FXML
    private Text author;
    @FXML
    private Text membername;
    @FXML
    private Text contact;
    @FXML
    private Text stutas;
    
      Statement statement;
    @FXML
    private TextField id;
    @FXML
    private TextField memberid;
    @FXML
    private TextField idinfo;
    @FXML
    private ListView<String> view;
    
    
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
        JFXDepthManager.setDepth(bookinfo, 1);
     JFXDepthManager.setDepth(memberinfo, 1);

        // TODO
    }    

    @FXML
    private void loadAddMember(ActionEvent event) throws IOException {
          loadwindow("/project/add_member/add_member.fxml","Add new Member" );
    }

    @FXML
    private void loadAddBook(ActionEvent event) throws IOException {
                   loadwindow("/project/add/add.fxml","Add new Book" );

    }

    @FXML
    private void loadDisplayMember(ActionEvent event) throws IOException {
                  loadwindow("/project/display_member/display_member.fxml","view Member" );

    }

    @FXML
    private void loadDisplayBook(ActionEvent event) throws IOException {
                  loadwindow("/project/disblaybook/disblaybook.fxml","View Book" );

    }
    
    private void loaaupdateanddelet(ActionEvent event) throws IOException {

    }
    private void updatebook(ActionEvent event) throws IOException {
                                  loadwindow("/project/updatebook/updatebook.fxml","update and Dlet Book" );

    }
     @FXML
    private void viewbrow(ActionEvent event) throws IOException {
                                          loadwindow("/project/viewbrower/viewbrower.fxml","view borrower" );

    }
    
    void loadwindow(String window ,String title) throws IOException{
          Pane pane= FXMLLoader.load(getClass().getResource(window));
        
        Scene scene = new Scene(pane);
        Stage  primaryStage= new Stage(StageStyle.DECORATED);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    

    @FXML
    private void showinfobook(ActionEvent event) throws SQLException {
        clearbook();
          boolean found =false;
        String Id =this.id.getText();
         ResultSet rs = this.statement.executeQuery("Select * From databook where id ="+Id);
       
           
           while(rs.next()){
             String name=rs.getString("title");
             String Author=rs.getString("author");
             boolean av=rs.getBoolean("isavail");
             
             bookname.setText(name);
             author.setText(Author);
             String stu= (av)? "availabel":"No available";
             stutas.setText(stu);
                 found=true;
           }
           if(!found){
               bookname.setText("No Such Book Avilable");
           }
    
    }

    @FXML
    private void showinfomember(ActionEvent event) throws SQLException {
        clearmember();
           boolean found =false;
        String Id =this.memberid.getText();
         ResultSet rs = this.statement.executeQuery("Select * From datamember where id ="+Id);
       
           
           while(rs.next()){
             String name=rs.getString("name");
             String con=rs.getString("mobile");
             
             
             membername.setText(name);
            
             contact.setText(con);
                 found=true;
           }
           if(!found){
               membername.setText("No Such Member Avilable");
           }
    }
    public  void clearbook(){
        bookname.setText("");
        author.setText("");
        stutas.setText("");
    }
      public  void clearmember(){
        membername.setText("");
        contact.setText("");
      
    }

    @FXML
    private void borrowbook(ActionEvent event) throws SQLException, IOException {
       if (memberid.getText().equals("") || id.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Borrow");
            alert.setContentText("full data");
            alert.showAndWait();
        } else {

            int Memberid = Integer.parseInt(this.memberid.getText());
            int bookid = Integer.parseInt(id.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Borrow Operation ");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want to Borrow the book " + bookname.getText() + " to " + membername.getText());

            Optional<ButtonType> x = alert.showAndWait();
            if (x.get() == ButtonType.OK) {
                LocalDateTime today = LocalDateTime.now();
                LocalDateTime newtime = today.plusDays(5);
                String sql = "Insert Into borrow(bookid,memberid ,returntime) values(" + bookid + ",'" + Memberid + "','" + newtime + "')";
                String sgl = "UPDATE databook SET isavail =false WHERE id =" + bookid;
                try {
                    this.statement.executeUpdate(sql);
                    this.statement.executeUpdate(sgl);
                    Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
                    aler.setHeaderText(null);
                    aler.setContentText("borrow sucsess");
                    aler.showAndWait();
                    
                       LocalDate date=LocalDate.now();
                    myWriter.write("\n");
                    myWriter.write("The book carrying the ID number: "+ bookid+" was borrowed for the member with the ID number:"+ Memberid+" In the history of: "+date );
                    myWriter.close();
                    
                } catch (SQLException ex) {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setHeaderText(null);
                    alert2.setContentText(" Borrow");
                    alert2.showAndWait();

                }

            }
        }
    
    
    }
 boolean sumb=false;
    @FXML
   
    private void loadinfobook(ActionEvent event) throws SQLException {
         
        ObservableList<String> list =FXCollections.observableArrayList();
          int ID= Integer.parseInt(idinfo.getText());
        String qu="select * from borrow where bookid="+ID;
         ResultSet rs;
              rs=this.statement.executeQuery(qu);
                
                      while (rs.next()) {
                   int BOOKID= ID;
                  Timestamp time=rs.getTimestamp("da");
                   int MemberID=rs.getInt("memberid");
                   System.out.println(time);
                String retime=rs.getDate("returntime").toString();
                   
                   list.add("Borrw Date and time: "+ time.toString());
                  list.add("Reneu Count"+ retime);
                   
                   list.add("Book informations :");
                   qu="select * from databook where id ="+BOOKID;
               rs=this.statement.executeQuery(qu);
                   
                   while (rs.next()) {
                       
                      list.add("Book name: "+ rs.getString("title"));
                      list.add("Book author: "+ rs.getString("author"));
                      list.add("Date of publication: "+ rs.getString("publisher"));
                   }
                     qu="select * from datamember where id ="+MemberID;
                    rs=this.statement.executeQuery(qu);
                   list.add("Member informations :");
                     while (rs.next()) {
                         list.add("Name: "+rs.getString("name"));
                         list.add("Member ID: "+rs.getString("id"));

                         list.add("Mobile: "+rs.getString("mobile"));
                        list.add("Email: "+rs.getString("email"));
                        sumb=true;
                         
                       
                   }
                       
                       
                  }
                  view.getItems().setAll(list);
                   
               }

    @FXML
    private void submissionHandel(ActionEvent event) throws IOException  {
        if(!sumb){
            return;
        }
        String ID=idinfo.getText();
       String qu="Delete from borrow where Bookid="+ID;
        String qu2="UPDATE databook SET isavail=true WHERE id="+ID ; 
        try {
            this.statement.executeUpdate(qu);
             this.statement.executeUpdate(qu2);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Submission");
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
             LocalDate date=LocalDate.now();
                    myWriter.write("The process of returning a book with an ID number "+ID+" has been completed"+" In the history of: "+date );
                    myWriter.close();
        } catch (SQLException ex) {
             Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Faild");
            alert.showAndWait();
            System.out.println(ex);
            
        }
         
           
            
            
       

        
           
    }    

   

    

        
        
    }
   

   

   
    

   

