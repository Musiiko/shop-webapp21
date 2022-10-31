package org.example.services;

import org.example.DbConnector;
import org.example.domain.User;
import org.example.domain.UserRole;

import java.sql.*;

public class UserService {
    public UserService(){

    }
    public User verifyLoginAndPassword(String login, String password) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT * FROM users o\n" +
                                "WHERE login = ? AND password = ?;");
        statement.setString(1, login);
        statement.setString(2, password);

        User user = null;

        try(ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String login1 = resultSet.getString("login");
                String password1 = resultSet.getString("password");
                UserRole role = UserRole.valueOf(resultSet.getString("role").toUpperCase());
                user = new User(userId, login1, password1, role);
            }
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
        }

        return user;
    }
}
