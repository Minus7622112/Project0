package revature.pro0.dto;

public class LoanRequestDTO {
    private int loanID;
    private int PBalance;
    private String approved;
    private int borrowerId;
    private Object AppDate;
    private String loan_name;

    public String getLoan_name() {return loan_name;}
    public void setLoan_type(String loan_type) {this.loan_name = loan_name;}

    public int getLoanID() { return loanID;}
    public void setLoanID(int loanID) {this.loanID = loanID;}


    public int getPBalance() {return PBalance;}
    public void setPBalance(int PBalance) {this.PBalance = PBalance;}

    public String getApproved() { return approved;}
    public void setApproved(String approved) {this.approved = approved;}

    public int getBorrowerId() {return borrowerId;}
    public void setBorrowerId(int borrowerId) {this.borrowerId = borrowerId;}

    public Object getAppDate() {return AppDate;}
    public void setAppDate(Object AppDate) {this.AppDate = AppDate;}
}
