package revature.pro0.dao;

import java.io.PipedReader;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import revature.pro0.model.User;

public class UserDao {
    private final String url;
    private final String dbUser;
    private final String dbPassword;

    public UserDao(String url, String dbUser, String dbPassword){
        this.url = url;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public User createUser(User newUser){
        String sql = "INSERT INTO accounts(email, password) VALUES (?, ?)";

        try(Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, newUser.getEmail());
            stmt.setString(2, newUser.getPassword());
            stmt.executeUpdate();
            try(ResultSet genKeys = stmt.getGeneratedKeys()){
                if(genKeys.next()){
                    int newId = genKeys.getInt(1);
                    newUser.setUser_id(newId);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  newUser;
    }

    public  User getUserByUserId(int userId){
        String sql ="SELECT * FROM accounts WHERE account_id = ?";
        try(Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, userId);
            try(ResultSet rS = stmt.executeQuery()){
                if(rS.next()){
                    return new User(
                            rS.getInt("account_id"),
                            rS.getString("email"),
                            rS.getString("password")
                            /*rS.getString("first_name"),
                            rS.getString("last_name"),
                            rS.getString("phone_number"),
                            rS.getInt("mailing_address_id"),
                            rS.getObject("date_of_birth", LocalDate.class),
                            rS.getInt("credit_score"*/
                    );
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try(Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rS = stmt.executeQuery()){

            while (rS.next()){
                users.add(new User(
                        /*rS.getInt("user_profile_id"),*/
                        rS.getInt("account_id"),
                        rS.getString("email"),
                        rS.getString("password")
                        /*rS.getString("first_name"),
                        rS.getString("last_name"),
                        rS.getString("phone_number"),
                        rS.getInt("mailing_address_id"),
                        rS.getObject("date_of_birth", LocalDate.class),
                        rS.getInt("credit_score")*/
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
}