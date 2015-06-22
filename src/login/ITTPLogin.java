package login;

import java.sql.*;

public class ITTPLogin {
	private final String U = "userid", P = "pass";
	private String uid = "", pass = "";
	private static String userid = "";

	public enum ClientType {
		student, admin
	};

	public ITTPLogin(String net_id, String password)
			throws ClassNotFoundException, SQLException {
		userid = net_id;
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/STERNITTS", "SternApps",
				"stern_db@nyu");
		Statement statement = con.createStatement();
		String stern_cred_query = "SELECT * from login where userid=" + "\""
				+ net_id + "\"" + " AND " + "pass=" + "\"" + password + "\";";
		ResultSet rquery = statement.executeQuery(stern_cred_query);
		while (rquery.next()) {
			this.uid = rquery.getString(U);
			this.pass = rquery.getString(P);
		}
	}

	public boolean verifyID() {
		return ((this.uid.length() > 0) && (this.pass.length() > 0));

	}

	public ClientType getClientType() {
		if (this.uid.equals("admin"))
			return ClientType.admin;
		return ClientType.student;
	}

	public static String getUserId() {
		return userid;
	}
}