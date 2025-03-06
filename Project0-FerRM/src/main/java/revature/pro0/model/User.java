package revature.pro0.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class User {
    private int user_id;
//    private int account_id;
//    private String first;
//    private String last;
//    private String phone;
    private String mail;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
//    private LocalDate date;
//    private int credit;
    private String password;

    public User(int user_id, String mail, String password){
        this.user_id = user_id;
//        this.account_id = account_id;
//        this.first = first;
//        this.last = last;
//        this.phone = phone;
        this.mail = mail;
//        this.date = date;
//        this.credit = credit;
        this.password = password;
    }

    public User(){

    }

    //

    public int getUser_id() {return user_id;}
    public void setUser_id(int user_id) {this.user_id = user_id;}
//
//    public int getAccount_id() {return account_id;}
//    public void setAccount_id(int account_id) {this.account_id = account_id;}
//
//    public String getFirst() {return first;}
//    public void setFirst(String first) {this.first = first;}
//
//    public String getLast() {return last;}
//    public void setLast(String last) {this.last = last;}
//
//    public String getPhone() {return phone;}
//    public void setPhone(String phone) {this.phone = phone;}

    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
//    public LocalDate getDate() {return date;}
//    public void setDate(LocalDate date) {this.date = date;}
//
//    public int getCredit() {return credit;}
//    public void setCredit(int credit) {this.credit = credit;}

    @Override
    public String toString(){
        return "User{" + "user id =" + user_id + ", email =" + mail + "}";
    }
}