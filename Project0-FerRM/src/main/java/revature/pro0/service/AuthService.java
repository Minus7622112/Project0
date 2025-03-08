package revature.pro0.service;

import io.javalin.http.Context;
import revature.pro0.dao.UserDao;
import revature.pro0.model.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;

public class AuthService {
    private final UserDao userDao;

    public AuthService(UserDao userDao){
        this.userDao = userDao;
    }

    public void register(Context cont){
        //try{
            User user = cont.bodyAsClass(User.class);
            if(user.getEmail() == null || user.getPassword() == null){
                cont.status(400).result("Username and password are required");
                return;
            }
            //if(user.g)

            String hashedPwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
            user.setPassword(hashedPwd);

            userDao.createUser(user);
            cont.status(201).result("User registered");
        /*}catch (SQLException e){
            cont.status(500).result("Error: " + e.getMessage());
        }*/
    }

    public void login(Context cont){
        //try{
            User user = cont.bodyAsClass(User.class);
            User userLogin = userDao.getUserByUserId(user.getUser_id());

            if(userLogin == null || !BCrypt.checkpw(user.getPassword(), userLogin.getPassword())){
                cont.status(401).result("Invalid username or password");
                return;
            }
            cont.sessionAttribute("account_id", userLogin);    /**/
            cont.status(200).result("Welcome back");
       /* }catch (SQLException e){
            cont.status(500).result("Error:" );
            e.printStackTrace();
        }*/
    }
    public void logout(Context cont){
        cont.req().getSession().invalidate();
        cont.status(200).result("Logged out...");
    }
}
