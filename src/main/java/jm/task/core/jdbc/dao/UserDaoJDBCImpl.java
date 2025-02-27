package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
//        try (Statement statement = connection.createStatement()){
//        statement.execute("INSERT INTO base (ID,NAME,LAST_NAME, AGE) VALUES (id, name, lastName,age)");
//        } catch (SQLException e) {
//          e.printStackTrace();
//        }
        PreparedStatement preparedStatement = null;
                String sql = "INSERT INTO base (ID,NAME,LAST_NAME, AGE) VALUES (id, name, lastName, age)";
        try {
            preparedStatement = connection.prepareStatement(sql);
        
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {
//        User user = new User();

        String sql = "INSERT INTO base (ID,NAME,LAST_NAME, AGE) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setInt(1,user.setId(0L););
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,lastName);
            preparedStatement.setByte(4,age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List <User> userList = new ArrayList<>();
        String sql = "SELECT NAME, LAST_NAME,AGE FROM base";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet =statement.executeQuery(sql);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {

    }

    public static void main(String[] args) {
        UserDaoJDBCImpl u = new UserDaoJDBCImpl();
        u.createUsersTable();
    }
}
