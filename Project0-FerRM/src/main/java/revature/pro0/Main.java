package revature.pro0;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import revature.pro0.controller.LoanControl;
import revature.pro0.controller.UserControl;
import revature.pro0.dao.LoanDao;
import revature.pro0.dao.UserDao;
import revature.pro0.service.LoanService;
import revature.pro0.service.UserService;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class Main {
    //DROP tables to reset each time
    private static final String DROP_TABLES_SQL = """
            DROP TABLE IF EXISTS loan_apps CASCADE;
            DROP TABLE IF EXISTS accounts CASCADE;
            """;

    //Creating tables
    private static final String CREATE_TABLES_SQL = """
        CREATE TABLE IF NOT EXISTS accounts(account_id serial primary key not null, 
        email varchar(26) not null, password text not null, account_type varchar(10));
        
        CREATE TABLE loan_apps(loan_app_id SERIAL primary key not null, principal_balance money not null,
        approved varchar(10), borrower_id int, application_date date not null, loan_name varchar(50) not null,
        foreign key(borrower_id) references accounts(account_id));
               
        """;

    //Insert sample data
    private static final String INSERT_DATA_SQL = """
            INSERT INTO accounts (email, password, account_type) VALUES ('william@boeing.com', 'pass1', 'admin'), ('gillaumefaury@airbus.com', 'a20n', 'user');
            
            INSERT INTO loan_apps (principal_balance, approved, borrower_id, application_date, loan_name) VALUES ('100', 'pending', '1', NOW(), 'headphones'), ('200', 'pending', '2', CURRENT_TIMESTAMP, 'pc gamer');
            
            """;


    public static void main(String[] args) {
        //DB credentials
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String dbUser = "postgres";
        String dbPassword = "pass787";

        //Localdate config
        ObjectMapper obM = new ObjectMapper();
        obM.registerModule(new JavaTimeModule());

        //Initialize DB
        resetDatabase(jdbcUrl, dbUser, dbPassword);

        //Create DAOs, Services, controllers
        UserDao userDao = new UserDao(jdbcUrl, dbUser, dbPassword);
        LoanDao loanDao = new LoanDao(jdbcUrl, dbUser, dbPassword);

        UserService userService = new UserService(userDao);
        LoanService loanService = new LoanService(loanDao);

        UserControl userControl = new UserControl(userService);
        LoanControl loanControl1 = new LoanControl(loanService);

        //Start Javalin
        Javalin app = Javalin.create(config ->{
            config.jsonMapper(new JavalinJackson(obM));
        }).start(7000);

        //Define routes
        app.post("/register", userControl::register);
        app.post("/login", userControl::login);
        app.get("/users", userControl::getAllUsers);
        app.get("/users/{id}/loans", loanControl1::getUserLoans);
//        app.get("/check", userControl::checkLogin);
//        app.post("/loginuser", userService::loginUser);


        app.post("/loans", loanControl1::createLoan);
        app.put("/loans/{id}", loanControl1::updateLoan);
        app.delete("/loans/{id}", loanControl1::deleteLoan);
        app.get("/loans", loanControl1::getAllLoans);

        System.out.println("Server running on localhost:7000");

        List<String> names = Arrays.asList("Luis", "Niko", "CJ");
        names.sort((a, b) -> a.compareTo(b));
        System.out.println(names);
        }

        //Reset DB
    private static void resetDatabase(String jdbcUrl, String dbUser, String dbPassword){
        runSql(DROP_TABLES_SQL, jdbcUrl, dbUser, dbPassword);
        runSql(CREATE_TABLES_SQL, jdbcUrl, dbUser, dbPassword);
        runSql(INSERT_DATA_SQL, jdbcUrl, dbUser, dbPassword);
    }

    private static void runSql(String sql, String jdbcUrl, String dbUser, String dbPassword){
        try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
        Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Executed SQL: " + sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}