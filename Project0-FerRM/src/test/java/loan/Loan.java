package loan;

public class Loan {
    private String loanName;
    private String approved;

    public Loan(String loanName, String approved){
        this.loanName = loanName;
        this.approved = approved;
    }

    public String getLoanName(){
        return loanName;
    }

    public String getApproved(){
        return approved;
    }
}
