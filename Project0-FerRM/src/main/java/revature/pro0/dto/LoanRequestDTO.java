package revature.pro0.dto;

public class LoanRequestDTO {
    private int loanID;
    private int loanType;
    private int PBalance;
    private int Term;
    private int Interest;
    private int TBalance;
    private int appStatusId;
    private int borrowerId;
    private Object AppDate;
    private String loan_name;

    public String getLoan_name() {return loan_name;}
    public void setLoan_type(String loan_type) {this.loan_name = loan_name;}

    public int getLoanID() { return loanID;}
    public void setLoanID(int loanID) {this.loanID = loanID;}

    public int getLoanType(){return loanType;}
    public void setLoanType(int loanType) {this.loanType = loanType;}

    public int getPBalance() {return PBalance;}
    public void setPBalance(int PBalance) {this.PBalance = PBalance;}

    public int getTerm() { return Term;}
    public void setTerm(int Term) {this.Term = Term;}

    public int getInterest() {return Interest;}
    public void setInterest(int Interest) {this.Interest = Interest;}

    public int getTBalance() {return TBalance;}
    public void setTBalance(int TBalance) {this.TBalance = TBalance;}

    public int getAppStatusId() {return appStatusId;}
    public void setAppStatusId(int appStatusId) {this.appStatusId = appStatusId;}

    public int getBorrowerId() {return borrowerId;}
    public void setBorrowerId(int borrowerId) {this.borrowerId = borrowerId;}

    public Object getAppDate() {return AppDate;}
    public void setAppDate(Object AppDate) {this.AppDate = AppDate;}
}
