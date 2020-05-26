/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.disblaybook;

/**
 *
 * @author LapCity
 */
public class databook {
    private int id;
    private  String title;
    private  String author;
        private  String  date;
        private  boolean avilabel;

    public databook() {
    }
        

    public databook(int id, String title, String author, String date, boolean avilabel) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.avilabel = avilabel;
    }

        
    public boolean isAvilabel() {
        return avilabel;
    }

    public void setAvilabel(boolean avilabel) {
        this.avilabel = avilabel;
    }
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

 

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
        

      @Override
    public String toString() {
        return String.format("%-5s %-10s %-10s %-5s %-5s", id, title, author, date,avilabel);
    }
    


            
    
}
