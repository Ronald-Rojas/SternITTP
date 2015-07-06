package clock;

import java.sql.SQLException;

import sqlconnect.SternQuery;

public class ClockOut {
	
	/**
	 * Records current server time to database denoting the end of shift
	 * 
	 * @param sternID unique user id typically the stern links id 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public ClockOut(String sternID) throws ClassNotFoundException, SQLException {
				
				SternQuery sq=SternQuery.getInstance();
                String time_out = "UPDATE "+ sq.TTABLE+ " SET "+sq.TCOL4+"=CURRENT_TIMESTAMP  where "
                                        + sq.TCOL2+"=\"" + sternID + "\" AND " +sq.TCOL4+ " IS NULL;";
                        
                SternQuery.connect().executeUpdate(time_out);
	}
}
