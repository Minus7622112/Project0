package loan;

import java.util.ArrayList;
import java.util.List;

public class LoanService {
    private List <Loan> loans = new ArrayList<>();

    public void addLoan(String loanName, String approved){
        loans.add(new Loan(loanName, approved));
    }

    public List<Loan> getAllLoans(){
        return loans;
    }

}
