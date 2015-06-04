package clock;

import java.sql.*;


public class ClockIn {

	public ClockIn(String userid) throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/STERNITTS","SternStudent","studentlogin@1");
		Statement statement=con.createStatement();

		if(!clockedIn(statement,userid))
			timeIn(statement, userid);
	}

	private void timeIn(Statement statement,String userid) throws SQLException{

		String time_entry="INSERT INTO time_login (userid,time_in,time_out) "
				+ "VALUES(\""+userid+"\",CURRENT_TIMESTAMP,NULL);";

		statement.executeUpdate(time_entry);
	}
	private boolean clockedIn(Statement statement,String userid) throws SQLException{
		String clockedin="SELECT COUNT(*) AS userid from time_login "
				+ "where userid="+"\""+userid+"\" AND time_out IS NULL";

		ResultSet rs=statement.executeQuery(clockedin);

		while(rs.next()){
			if(rs.getInt("userid") != 0)
				return true;
		}return false;
	}
}