package revature.pro0.model;

public class Loan {
    private int loan_id;
    private int loan_type;
    private int PBalance;
    private int term;
    private int interest;
    private int TBalance;
    private int app_status;
    private int borrower_id;
    private Object app_date;
    private String loan_name;

    public Loan() {}

    public Loan(int loan_id,  int PBalance, int term, int interest, int TBalance, int borrower_id, Object app_date, String loan_name){
        this.loan_id = loan_id;
        //this.loan_type = loan_type;
        this.PBalance = PBalance;
        this.term = term;
        this.interest = interest;
        this.TBalance = TBalance;
        //this.app_status = app_status;
        this.borrower_id = borrower_id;
        this.app_date = app_date;
        this.loan_name = loan_name;
    }

    public int getLoan_id() {return loan_id;}
    public void setLoan_id(int loan_id) {this.loan_id = loan_id;}

    public int getLoan_type() {return loan_type;}
    public void setLoan_type(int loan_type) {this.loan_type = loan_type;}

    public int getPBalance() {return PBalance;}

    public void setPBalance(int PBalance) {this.PBalance = PBalance;}

    public int getTerm() {return term;}
    public void setTerm(int term) {this.term = term;}

    public int getInterest() {return interest;}
    public void setInterest(int interest) {this.interest = interest;}

    public int getTBalance() {return TBalance;}
    public void setTBalance(int TBalance) {this.TBalance = TBalance;}

    public int getApp_status() {return app_status;}
    public void setApp_status(int app_status) {this.app_status = app_status;}

    public int getBorrower_id() {return borrower_id;}
    public void setBorrower_id(int borrower_id) {this.borrower_id = borrower_id;}

    public Object getApp_date() {return app_date;}
    public void setApp_date(Object app_date) {this.app_date = app_date;}

    public String getLoan_name() {return loan_name;}
    public void setLoan_name(String loan_name) {this.loan_name = loan_name;}
}
