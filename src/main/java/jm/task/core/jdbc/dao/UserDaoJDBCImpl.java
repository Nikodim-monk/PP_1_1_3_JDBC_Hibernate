package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.mineConnection();

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(UserID INT PRIMARY KEY AUTO_INCREMENT, FirstName varchar(15),LastName varchar(15), Age int)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT users(FirstName, LastName, Age) VALUES ('"
                    + name + "','" + lastName + "'," + age + ")");
            System.out.println("User с именем Ц " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement pS = connection.prepareStatement("DELETE FROM users WHERE UserID = ?")) {
            pS.setLong(1, id);
            pS.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet rS = preparedStatement.executeQuery();
            while (rS.next()) {
                users.add(new User(rS.getLong(1),rS.getString(2), rS.getString(3),
                        (byte) rS.getInt(4)));
            }
            System.out.println(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void closeConn(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
