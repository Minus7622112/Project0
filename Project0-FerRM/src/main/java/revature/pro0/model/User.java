package revature.pro0.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class User {
    private int account_id;
//    private int account_id;
//    private String first;
//    private String last;
//    private String phone;
    private String email;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
//    private LocalDate date;
//    private int credit;
    private String password;

    public User(int account_id, String email, String password){
        this.account_id = account_id;
//        this.account_id = account_id;
//        this.first = first;
//        this.last = last;
//        this.phone = phone;
        this.email = email;
//        this.date = date;
//        this.credit = credit;
        this.password = password;
    }

    public User(){

    }

    //
    public int getUser_id() {return account_id;}
    public void setUser_id(int user_id) {this.account_id = user_id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}


    @Override
    public String toString(){
        return "User{" + "user id =" + account_id + ", email =" + email + "}";
    }
}