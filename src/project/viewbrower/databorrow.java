/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.viewbrower;

/**
 *
 * @author Omar
 */
public class databorrow {
    int bookid;
    int memberid;
    String dateborrow;
    String retunetime;

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public String getDateborrow() {
        return dateborrow;
    }

    public void setDateborrow(String dateborrow) {
        this.dateborrow = dateborrow;
    }

    public String getRetunetime() {
        return retunetime;
    }

    public void setRetunetime(String retunetime) {
        this.retunetime = retunetime;
    }

    @Override
    public String toString() {
        return "databorrow{" + "bookid=" + bookid + ", memberid=" + memberid + ", dateborrow=" + dateborrow + ", retunetime=" + retunetime + '}';
    }
    
    
}

