package sqlconnect;

import java.sql.*;

public class SternQuery {
	
	private static SternQuery sternquery=new SternQuery();
	private final static String TDATABASE="STERNITTS",TUSER="SternStudent",TPASS="studentlogin@1";
	
	/**
	 * name of time stamp database table
	 */
	public final static String TTABLE="time_login",
	/**
	 * primary ascending integer key column of time stamp table 
	 */
	
	TCOL1="id",
	/**
	 * stern id column		
	 */

	TCOL2="userid",
	/**
	 * start of shift time stamp column
	 */
	TCOL3="time_in",
	
	/**
	 * end of shift time stamp column 
	 */
	TCOL4="time_out";
	
	/**
	 * returns statement object permitting queries on the time stamp database 
	 * @return SQL statement object linked to time stamp database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Statement connect() throws ClassNotFoundException, SQLException{

		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/"+TDATABASE, TUSER,TPASS);
		
		return con.createStatement();
	}
	
	/**
	 * returns a static instantiation of SternQuery (singleton class) 
	 * @return instance of SternQuery Object
	 */
	public static SternQuery getInstance(){
		return sternquery;
	}
}