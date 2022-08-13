package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection mineConnection() {

        try {
            return DriverManager
                    .getConnection("jdbc:mysql://localhost/mine_schema", "root", "Lroberon_75*Kata");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
