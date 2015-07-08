package login;

import java.sql.*;

public class ITTPLogin {
	
	private final String U = "userid", P = "pass";
	
	private String sternID = "", passPhrase = "";
	
	/**
	 *  user privilege levels
	 */
	public enum ClientType {
		/**
		 * student worker
		 */
		student, 
		/**
		 * supervisor role
		 */
		admin
	};
	
	/**
	 * Gateway to login to either the client page for student workers
	 * or Administrator panel to view hours per user and generate reports
	 * 
	 * @param sternID a unique user id typically the stern links id
	 * @param passPhrase  the password to login
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ITTPLogin(String sternID, String passPhrase) throws ClassNotFoundException, SQLException {
		/*
		 * connect to database
		 */
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/STERNITTS", "SternApps",
				"stern_db@nyu");
		Statement statement = con.createStatement();
		
		/*
		 * query database search for stern id and password match
		 */
		String stern_cred_query = "SELECT * from login where userid=" + "\""
				+ sternID + "\"" + " AND " + "pass=" + "\"" + passPhrase + "\";";
		ResultSet rquery = statement.executeQuery(stern_cred_query);
		while (rquery.next()) {
			this.sternID = rquery.getString(U);
			this.passPhrase = rquery.getString(P);
		}
	}
	
	/**
	 * checks if the login credentials are superficially valid.
	 * @return true if the sternID and passPhrase are non empty strings
	 */
	public boolean verifyID() {
		return ((this.sternID.length() > 0) && (this.passPhrase.length() > 0));
	}
	
	/**
	 * Returns the user privilege level to redirect the user to the client
	 * or administrator page
	 * 
	 * @return enumeration type denoting the privilege level of the user.
	 */
	public ClientType getClientType() {
		if (this.sternID.equals("admin"))
			return ClientType.admin;
		return ClientType.student;
	}
}