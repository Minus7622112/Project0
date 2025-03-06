package revature.pro0.service;

import revature.pro0.dao.LoanDao;
import revature.pro0.model.Loan;
import java.util.List;

public class LoanService {
    private final LoanDao loanDao;

    public LoanService(LoanDao loanDao) {this.loanDao = loanDao;}

    //Create a new loan application

    public void addLoan(Loan loan){
        loanDao.createLoan(loan);
    }

    //Get all Loans
    public List<Loan> getAllLoans(){return loanDao.getAllLoans(); }

    //Get all loans from specific user
    public List<Loan> getUserLoans(int userId) {return  loanDao.getLoansByUserId(userId);}

    //Update an existing loan application
    public void updateLoan(Loan loan) {loanDao.updateLoan(loan); }

    //Delete a loan application by id
    public void deleteLoan(int loanId) {loanDao.deleteLoan(loanId);}
}
