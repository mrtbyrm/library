package denemeee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class islemler{

	public static Connection baglantiAc() throws SQLException{
		Connection c = null;
				c = DriverManager
				           .getConnection("jdbc:postgresql://localhost:5432/postgres",
				           "postgres", "1510");
				c.setAutoCommit(false);
		return c;
	}
	

}
