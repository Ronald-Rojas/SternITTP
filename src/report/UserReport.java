package report;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sqlconnect.SternQuery;

public class UserReport{
	
	private ArrayList<Entry> timeSlotsWorked;
	private double totalTimeWorked;
	private final int YEAR=0,MONTH=1,DAY=2;
	protected String sternID;
	protected String[] begin,end;
	
	/**
	 * Aggregates work shifts between a given time range
	 * and computes the total number of hours worked
	 * 
	 * @param sternID  
	 * @param begin lower bound date (format: YYYY-MM-DD)
	 * @param end  upper bound date (format: YYYY-MM-DD)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserReport(String sternID, String begin, String end) throws ClassNotFoundException, SQLException{
		
		this.sternID=sternID;
		timeSlotsWorked = new ArrayList<Entry>();
		totalTimeWorked = 0;
		
		parseDateRanges(begin,end);
		searchTable(this.begin,this.end);
		computeTotalHours();
	}
	/**
	 * Return total hours worked within specified date range
	 * @return total hours worked precise to the half hour 
	 */
	public double getTotalTimeWorked() {
		return totalTimeWorked;
	}
	/**
	 * parse date ranges for SQL Query called in searchTable
	 * 
	 * @param begin unparsed start date range
	 * @param end unparsed end date range
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	private void parseDateRanges(String begin,String end) throws ClassNotFoundException, SQLException{
		final String HYPHEN="-";
		this.begin=begin.split(HYPHEN);
		this.end=end.split(HYPHEN);
	}
	/**
	 * Query database for all shifts within specified date range
	 * 
	 * @param start beginning of date range
	 * @param end end of date range
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings({ "static-access" })
	private void searchTable(String[] start,String[] end) throws ClassNotFoundException, SQLException{
	
		SternQuery sq=SternQuery.getInstance();
		final String remnant="00:00:00\' ";
		
		String searchCommand="SELECT * FROM "+sq.TTABLE+" WHERE " + sq.TCOL3 + " >= \'"+
		start[YEAR]+ "-"+ start[MONTH]+ "-" + start[DAY] +" " + remnant +
		
		" AND "+
		
		sq.TCOL3 + " <= \'"+
		end[YEAR]+ "-"+ end[MONTH] + "-" + end[DAY] + " " +  remnant

		+" AND "+
			
		sq.TCOL2+ "="+ "\""+ sternID+"\";";
		
		java.sql.Statement statement=sq.connect();
		ResultSet rs=statement.executeQuery(searchCommand);
		
		while(rs.next())
			if(rs.getTimestamp(sq.TCOL3)!= null && rs.getTimestamp(sq.TCOL4) != null)
			timeSlotsWorked.add(new Entry(rs.getTimestamp(sq.TCOL3),rs.getTimestamp(sq.TCOL4)));
	}
	/**
	 * Returns start and end time stamps within specified range
	 * @return Entry Object representing individual work shift information
	 */
	public ArrayList<Entry> getTimeSlotsWorked() {
		return timeSlotsWorked;
	}
	/**
	 * compute total hours within specified range
	 */
	private void computeTotalHours() {
		if (getTotalTimeWorked() != 0)
			return;

		for (Entry timeSlot : timeSlotsWorked)
			totalTimeWorked += timeSlot.hoursWorked;
	}
	/**
	 * Convert java String Representation time stamps to Javascript String array for client side display
	 * function provided by StackOverflow User Uooo 
	 * @link http://stackoverflow.com/questions/17440164/converting-a-java-arraylist-of-strings-to-a-javascript-array
	 * @param arr
	 * @return Javascript array of time stamps
	 */
	public String toJavascriptArray(String[] arr){
	    StringBuffer sb = new StringBuffer();
	    sb.append("[");
	    for(int i=0; i<arr.length; i++){
	        sb.append("\"").append(arr[i]).append("\"");
	        if(i+1 < arr.length){
	            sb.append(",");
	        }
	    }
	    sb.append("]");
	    return sb.toString();
	}
}