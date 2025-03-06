package revature.pro0.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class UserAuthRequestDTO {
//    private int user_id;
    private int account_id;
//    private String first;
//    private String last;
//    private String phone;
//    private int mail;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
//    private LocalDate date;
//    private int credit;
    private String email;
    private String password;

    public int getAccount_id() {return account_id;}
    public void setAccount_id(int account_id) {this.account_id = account_id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    //    public LocalDate getDate() {return date;}
//    public void setDate(LocalDate date) {this.date = date;}
//
//    public int getUser_id() {return user_id;}
//    public void setUser_id(int user_id) {this.user_id = user_id;}
//
//    public int getAccount_id() {return account_id;}
//    public void setAccount_id(int account_id) {this.account_id = account_id;    }
//
//    public String getFirst() {return first;}
//    public void setFirst(String first) {this.first = first;}
//
//    public String getLast() {return last;}
//    public void setLast(String last) {this.last = last;}
//
//    public String getPhone() {return phone;}
//    public void setPhone(String phone) {this.phone = phone;}
//
//    public int getMail() {return mail;}
//    public void setMail(int mail) {this.mail = mail;}
//
//    public int getCredit() {return credit;}
//    public void setCredit(int credit) { this.credit = credit;}
}
