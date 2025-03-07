package revature.pro0.controller;

import revature.pro0.dto.LoanRequestDTO;
import revature.pro0.model.Loan;
import revature.pro0.service.LoanService;
import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoanControl {
    private static final Logger lg = LoggerFactory.getLogger(LoanControl.class);

    private final LoanService loanService;

    public LoanControl(LoanService loanService){this.loanService = loanService;}

    /*Create loan
    * POST /loans
    * JSON
    * {
*       "principal_balance": 50,000,
    *   "approved": "pending", //auto
    *   "borrower_id": 1,
    *   "application_date": 14-02-2025, //auto
    *   "loan_name": "Car purchase"
    *
    * }
    * */
    public void createLoan(Context cont){
        LoanRequestDTO req = cont.bodyAsClass(LoanRequestDTO.class);
        lg.info("Creating a loan...");
        if(req.getLoan_name() == null || req.getLoan_name().trim().isEmpty()){
            cont.status(400).json("{\"error\":\"Missing Loan name\"}");
            lg.error("Error at creating a loan");
        return;
        }

        Loan loan = new Loan();
        loan.setBorrower_id(req.getBorrowerId());
        loan.setLoan_name(req.getLoan_name());
        loan.setApproved(req.getApproved());
        loan.setPBalance(req.getPBalance());
        loan.setApp_date(LocalDate.now());
        loanService.addLoan(loan);

        cont.status(201).json(loan);
    }

    /*GET /loans?userId=1
    * using userId if present, otherwise shows all loans */
    public void getLoans(Context cont){
        lg.info("getting loans from a user");
        String borrowerIDP = cont.queryParam("borrowerId");
        if(borrowerIDP != null){
            int userId = Integer.parseInt(borrowerIDP);
            List<Loan> userLoans = loanService.getUserLoans(userId);
            cont.json(userLoans);
            lg.info("got loans");
        }else{
            List<Loan> allLoans = loanService.getAllLoans();
            cont.json(allLoans);
            lg.info("didnt find user, get all loans");
        }
    }


    /*PUT /loans/:id
    *
    * {
    *   "loan_name": "New Title",
    *   "principal_balance": 10000
    *
    *  */
    public void updateLoan(Context cont){
        int loanId = Integer.parseInt(cont.pathParam("id"));
        LoanRequestDTO req = cont.bodyAsClass(LoanRequestDTO.class);
        lg.info("updating a loan");
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
        lg.info("Deleting a loan...");
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
