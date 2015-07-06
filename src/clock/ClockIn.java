package clock;

import java.sql.*;

import sqlconnect.SternQuery;

public class ClockIn {
	
	private String sternID;
	
	/**
	 * Maps unique stern id to current server time at the start of the work day. 
	 * 
	 * @param sternID  typically the stern links user id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ClockIn(String sternID) throws ClassNotFoundException, SQLException {

		this.sternID=sternID;
		Statement statement = SternQuery.connect();

		if (!clockedIn(statement))
			timeIn(statement);
	}
	
	/**
	 * Records current server time to database denoting beginning of shift
	 * @param statement tasks assigned to the time logging database
	 * @throws SQLException
	 */
	private void timeIn(Statement statement) throws SQLException {
		SternQuery sq=SternQuery.getInstance();

		@SuppressWarnings("static-access")
		String time_entry = "INSERT INTO "+ sq.TTABLE+ " ("+sq.TCOL2+","+sq.TCOL3+","+sq.TCOL4+") "
				+ "VALUES(\"" + sternID + "\",CURRENT_TIMESTAMP,NULL);";

		statement.executeUpdate(time_entry);
	}
	
	/**
	 * check if the user clocked in to  prevent new entries before clocking out 
	 * 
	 * @param statement tasks assigned to time logging database
	 * @return true if the user clocked into work but did not clock out
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	private boolean clockedIn(Statement statement) throws SQLException {
		SternQuery sq=SternQuery.getInstance();
		String clockedin = "SELECT COUNT(*) AS "+sq.TCOL2+ " from " + sq.TTABLE
				+ " where "+ sq.TCOL2+"=" + "\"" + sternID + "\" AND "+ sq.TCOL4 +" IS NULL";

		ResultSet rs = statement.executeQuery(clockedin);

		while (rs.next()) {
			if (rs.getInt("userid") != 0)
				return true;
		}
		return false;
	}
}