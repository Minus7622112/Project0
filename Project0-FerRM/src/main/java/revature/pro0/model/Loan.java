package revature.pro0.model;

public class Loan {
    private int loan_id;
    private int PBalance;
    private String approved;
    private int app_status;
    private int borrower_id;
    private Object app_date;
    private String loan_name;

    public Loan() {}

    public Loan(int loan_id,  int PBalance, String approved, int borrower_id, Object app_date, String loan_name){
        this.loan_id = loan_id;
        //this.loan_type = loan_type;
        this.PBalance = PBalance;
        this.approved = approved;
        //this.app_status = app_status;
        this.borrower_id = borrower_id;
        this.app_date = app_date;
        this.loan_name = loan_name;
    }

    public int getLoan_id() {return loan_id;}
    public void setLoan_id(int loan_id) {this.loan_id = loan_id;}

    public int getPBalance() {return PBalance;}

    public void setPBalance(int PBalance) {this.PBalance = PBalance;}

    public String getApproved() {return approved;}
    public void setApproved(String approved) {this.approved = approved;}

    public int getBorrower_id() {return borrower_id;}
    public void setBorrower_id(int borrower_id) {this.borrower_id = borrower_id;}

    public Object getApp_date() {return app_date;}
    public void setApp_date(Object app_date) {this.app_date = app_date;}

    public String getLoan_name() {return loan_name;}
    public void setLoan_name(String loan_name) {this.loan_name = loan_name;}
}
