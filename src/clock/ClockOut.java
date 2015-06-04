package clock;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ClockOut {

	public ClockOut(String userid) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/STERNITTS","SternStudent","studentlogin@1");
		Statement statement=con.createStatement();	
		String time_out="UPDATE time_login SET time_out=CURRENT_TIMESTAMP  where "
				+ "userid=\""+userid+"\" AND time_out IS NULL;";
		statement.executeUpdate(time_out);
	}
}
