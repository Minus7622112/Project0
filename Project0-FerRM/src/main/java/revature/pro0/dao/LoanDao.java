package revature.pro0.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import revature.pro0.dto.LoanRequestDTO;
import revature.pro0.model.Loan;

public class LoanDao {
    private final String url;
    private final String dbUser;
    private final String dbPassword;

    public LoanDao(String url, String dbUser, String dbPassword){
        this.url = url;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Loan createLoan(Loan loan){
        String sql = "INSERT INTO loan_apps (loan_app_id, principal_balance, term_length, interest, total_balance, borrower_id, application_date, loan_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, loan.getLoan_id());
//            stmt.setInt(2, loan.getLoanType());
            stmt.setInt(2, loan.getPBalance());
            stmt.setInt(3, loan.getTerm());
            stmt.setInt(4, loan.getInterest());
            stmt.setInt(5, loan.getTBalance());
//            stmt.setInt(7, getAppStatusId());
            stmt.setInt(6, loan.getBorrower_id());
            stmt.setObject(7, loan.getApp_date());
            stmt.setString(8, loan.getLoan_name());
            stmt.executeUpdate();

            try(ResultSet genKeys = stmt.getGeneratedKeys()){
                if(genKeys.next()){
                    int newId = genKeys.getInt(1);
                    loan.setLoan_id(newId);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return loan;
    }

    /*Get all loans */

    public List<Loan> getAllLoans(){
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loan_apps";

        try(Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rS = stmt.executeQuery()){

            while(rS.next()){
                Loan l = new Loan(
                        rS.getInt("loan_app_id"),
                        /*rS.getInt("loan_type_id"),*/
                        rS.getInt("principal_balance"),
                        rS.getInt("term_length"),
                        rS.getInt("interest"),
                        rS.getInt("total_balance"),
                        /*rS.getInt("application_status_id"),*/
                        rS.getInt("borrower_id"),
                        rS.getObject("application_date"),
                        rS.getString("loan_name")
                );
                loans.add(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return loans;
    }

    /*Get Loans by user */

    public List<Loan> getLoansByUserId(int userId){
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loan_apps WHERE borrower_id = ?";

        try(Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, userId);
            try(ResultSet rS = stmt.executeQuery()){
                while(rS.next()){
                    Loan l = new Loan(
                            rS.getInt("loan_app_id"),
                            /*rS.getInt("loan_type_id"),*/
                            rS.getInt("principal_balance"),
                            rS.getInt("term_length"),
                            rS.getInt("interest"),
                            rS.getInt("total_balance"),
                            /*rS.getInt("application_status_id"),*/
                            rS.getInt("borrower_id"),
                            rS.getObject("application_date"),
                            rS.getString("loan_name")
                    );
                    loans.add(l);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return loans;
    }

    /*Update an existing loan */
    public void updateLoan(Loan loan){
        String sql = "UPDATE loan_apps SET loan_app_id = ?, principal_balance = ?, term_length = ?, interest = ?, total_balance = ?, application_date = ?, loan_name = ?  WHERE borrower_id = ?";
        try(Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, loan.getLoan_id());
            //stmt.setInt(2, loan.getLoanType());
            stmt.setInt(2, loan.getPBalance());
            stmt.setInt(3, loan.getTerm());
            stmt.setInt(4, loan.getInterest());
            stmt.setInt(5, loan.getTBalance());
            //stmt.setInt(7, getAppStatusId());
            stmt.setInt(6, loan.getBorrower_id());
            stmt.setObject(7, loan.getApp_date());
            stmt.setString(8, loan.getLoan_name());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /* Delete a specific loan */

    public void deleteLoan(int loanId){
        String sql = "DELETE FROM loan_apps WHERE loan_app_id = ?";
        try(Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, loanId);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}