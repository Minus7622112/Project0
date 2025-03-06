package revature.pro0.controller;

import revature.pro0.dto.LoanRequestDTO;
import revature.pro0.model.Loan;
import revature.pro0.service.LoanService;
import io.javalin.http.Context;
import java.util.List;

public class LoanControl {

    private final LoanService loanService;

    public LoanControl(LoanService loanService){this.loanService = loanService;}

    /*Create loan
    * POST /loans
    * JSON
    * {
    *   "borrower_id": 1,
    *   "loan_name": "Car purchase",
    *   "term_length": 1,
    *   "principal_balance": 50,000,
    *   "interest" : 12,
    *   "total_balance": 53,309
    *   "application_date": 14-02-2025
    *
    * }
    * */
    public void createLoan(Context cont){
        LoanRequestDTO req = cont.bodyAsClass(LoanRequestDTO.class);

        if(req.getLoan_name() == null || req.getLoan_name().trim().isEmpty()){
            cont.status(400).json("{\"error\":\"Missing Loan name\"}");
        return;
        }

        Loan loan = new Loan();
        loan.setBorrower_id(req.getBorrowerId());
        loan.setLoan_name(req.getLoan_name());
        loan.setTerm(req.getTerm());
        loan.setPBalance(req.getPBalance());
        loan.setInterest(req.getInterest());
        loan.setTBalance(req.getTBalance());
        loan.setApp_date(req.getAppDate());
        loanService.addLoan(loan);

        cont.status(201).json(loan);
    }

    /*GET /loans?userId=1
    * using userId if present, otherwise shows all loans */
    public void getLoans(Context cont){
        String borrowerIDP = cont.queryParam("borrowerId");
        if(borrowerIDP != null){
            int userId = Integer.parseInt(borrowerIDP);
            List<Loan> userLoans = loanService.getUserLoans(userId);
            cont.json(userLoans);
        }else{
            List<Loan> allLoans = loanService.getAllLoans();
            cont.json(allLoans);
        }
    }


    /*PUT /loans/:id
    *
    * {
    *   "loan_name": "New Title",
    *   "principal_balance": 0
    *
    *  */
    public void updateLoan(Context cont){
        int loanId = Integer.parseInt(cont.pathParam("id"));
        LoanRequestDTO req = cont.bodyAsClass(LoanRequestDTO.class);

        Loan loan = new Loan();
        loan.setLoan_id(loanId);
        loan.setLoan_name(req.getLoan_name());
        loan.setPBalance(req.getPBalance());

        loanService.updateLoan(loan);
        cont.status(200).json("{\"msg:\":\"Loan updated\"}");
    }

    /*DELETE /loans/id:  */

    public void deleteLoan(Context cont){
        int loanId = Integer.parseInt(cont.pathParam("id"));
        loanService.deleteLoan(loanId);
        cont.status(200).json("{\"msg:\":\"Loan has been deleted\"}");
    }
    /*GET /loans   */
    public void getAllLoans(Context cont){
        List<Loan> allLoans = loanService.getAllLoans();
        cont.json(allLoans);

    }
    //GET /users/:id/loans
    public void getUserLoans(Context cont){
        int userId = Integer.parseInt(cont.pathParam("id"));
        List<Loan> userLoans = loanService.getUserLoans(userId);
        cont.json(userLoans);
    }
}
