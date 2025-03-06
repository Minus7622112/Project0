package revature.pro0.service;

import revature.pro0.dao.UserDao;
import revature.pro0.model.User;
import java.time.LocalDate;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userdao) {this.userDao = userdao;}

    public User registerUser(int account_id, String email, String password){

        //Hashing password using Bcrypt
        String hashed = hashPassword(password);

        if(userDao.getUserByUserId(account_id) != null){
            return null;
        }

        User newUser = new User();
//        newUser.setUser_id(user_id);
//        newUser.setAccount_id(account_id);
        newUser.setMail(email);
        newUser.setPassword(hashed);
//        newUser.setFirst(first);
//        newUser.setLast(last);
//        newUser.setPhone(phone);
//        newUser.setMail(mail);
//        newUser.setDate(date);
//        newUser.setCredit(credit);

        return userDao.createUser(newUser);
    }
    //method for hashing password
    public static String hashPassword(String pass){
        return BCrypt.hashpw(pass, BCrypt.gensalt(12));
    }

    //method for checking password
    public static boolean checkPass(String pass, String hashed){
        return BCrypt.checkpw(pass, hashed);
    }

    public boolean loginUser(int account_id, String email, String password){
        User existing = userDao.getUserByUserId(account_id);
        if(existing == null){
            return false;
        }

        //String hashed = "HASHED_" + password;
       return checkPass(password, existing.getPassword());

    }

    public List<User> getAllUsers() {return userDao.getAllUsers();}
}