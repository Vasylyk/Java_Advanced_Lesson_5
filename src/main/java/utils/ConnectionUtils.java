package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static String USER_NAME = "root";
    private static String PASSWORD = "dfl1997199756";
    private static String URL = "jdbc:mysql://localhost/my_shop?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

    public static Connection openConnection () throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName ("com.mysql.cj.jdbc.Driver").newInstance ();
        return DriverManager.getConnection (URL, USER_NAME, PASSWORD);
    }


}
