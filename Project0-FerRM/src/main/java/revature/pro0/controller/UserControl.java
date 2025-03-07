package revature.pro0.controller;

import io.javalin.http.Context;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import revature.pro0.dto.UserAuthRequestDTO;
import revature.pro0.model.User;
import revature.pro0.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserControl {
    private static final Logger lg = LoggerFactory.getLogger(UserControl.class);

    private final UserService userService;

    public UserControl(UserService userService){this.userService = userService;}

    /* Register
    * POST /register
    * {
    *   "email": "name@mail.com",
    *   "password": "pass1"
    * }
    *  * */
    public void register(Context cont){
        User user = cont.bodyAsClass(User.class);
        lg.info("Attempting to register a user");

        //basic validation
        if(user.getEmail() == null || user.getPassword() == null){
            cont.status(400).json("{\"Error\":\"Missing email or password\"}");
            lg.error("Didnt work. Missing a field");
            return;
        }else{
            cont.status(409).json("{\"Error\":\"Can't register email as it already exists\"}");
            lg.error("Didnt work. Email already exists");
        }
        User newUser = userService.registerUser(user.getUser_id(), user.getEmail(), user.getPassword());
        //checking if email already exists
//        if(userExists(user.getEmail())){
//            cont.status(409).json("{\"Error\":\"Email already exists. Please login\"}");
//            return;
//        }

        //Insert new user
        boolean created = createUserDB(newUser);
        if(created){
            cont.status(201).json(user);
            lg.info("User registered");
        }else{
            cont.status(500).json("{\"Error\":\"Failed to register user. Try again\"}");
            lg.error("Something went wrong :/");
        }
    }

    /* POST /login
    * {
    *   "email": "name@mail.com",
    *   "password": "pass1"
    * }
    * */
    public void login(Context cont){
        UserAuthRequestDTO user = cont.bodyAsClass(UserAuthRequestDTO.class);
        lg.info("Attempting to login");
        if(user.getEmail() == null || user.getPassword() == null){
            cont.status(400).json("{\"Error\":\"Email or password not typed in\"}");
            lg.error("Missing fields");
            return;
        }

        /*boolean success = userService.loginUser(user.getAccount_id(), user.getEmail(), user.getPassword());
        if(success){
            //TODO session token
            cont.status(200).json("{\"msg\":\"Login was succesful\"}");
        }else{
            cont.status(401).json("{\"Error\":\"Incorrect email or password\"}");
        }*/

        //check credentials
        User dbUser = getUserDB(user.getEmail());
        if(dbUser == null){
            cont.status(401).json("{\"Error\":\"Invalid email or password\"}");
            lg.error("Invalid credential: email");
            return;
        }
        //compare password
        if(!dbUser.getPassword().equals(user.getPassword())){
            cont.status(401).json("{\"Error\":\"Invalid email or password\"}");
            lg.error("Invalid credential: password");
            return;
        }

        //when valid we start a session
        HttpSession session = cont.req().getSession(true);
        session.setAttribute("user", dbUser);
        cont.status(200).json(dbUser);
        lg.info("All went good, we can start a session");
    }

    /*
    * GET /check
    * */
    //check if user is logged in
    public static void checkLogin(Context cont){
        UserAuthRequestDTO user = cont.bodyAsClass(UserAuthRequestDTO.class);
        HttpSession session = cont.req().getSession(false);
        if(session != null && session.getAttribute("user") != null){
            User userr = (User) session.getAttribute("user");
            cont.status(200).json(userr);
        }else{
            cont.status(401).json("\"Error\":\"Not logged in\"}");
        }
    }

    /*
    * POST /logout
    *  */
    public static void logout(Context cont){
        HttpSession session = cont.req().getSession();
        if(session != null){
            session.invalidate();
        }
        cont.status(200).json("{\"message\":\"Logged out\"}");
    }

    /*GET /users */
    public void getAllUsers(Context cont){
        List<User> users = userService.getAllUsers();
        cont.json(users);
        lg.info("Getting all users...");
    }

    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
    private static final String dbUser = "postgres";
    private static final String dbPassword = "pass787";

    private static boolean userExists(String email){
        String sql = "SELECT 1 FROM accounts WHERE email = ?";
        try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            ResultSet rS = stmt.executeQuery();
            return rS.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    private static boolean createUserDB(User user){
        String sql ="INSERT INTO accounts(email, password) VALUES (?, ?)";
        try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            int rows = stmt.executeUpdate();
            return rows > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private static User getUserDB(String email){
        String sql = "SELECT * FROM accounts WHERE email = ?";
        try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            ResultSet rS = stmt.executeQuery();
            if(rS.next()){
                User user = new User();
                user.setUser_id(rS.getInt("account_id"));
                user.setEmail(rS.getString("email"));
                user.setPassword(rS.getString("password"));
                return user;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}