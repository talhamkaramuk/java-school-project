package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	String user = "root";
	String password = "1234";
	String url = "jdbc:mysql://localhost:3306/ariza";

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
