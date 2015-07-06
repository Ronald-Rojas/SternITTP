package report;

import java.sql.Timestamp;

public class Entry {
	
	protected Timestamp startTime;
	protected Timestamp endTime;
	protected double hoursWorked;
	
	/**
	 * 
	 * computes the duration of time worked
	 * @param startTime start of shift 
	 * @param endTime end of shift
	 */
	protected Entry(Timestamp startTime, Timestamp endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		computeHoursWorked();
	}
	/**
	 * computes the difference between SQL time stamps
	 */
	private void computeHoursWorked() {
		long delta = (endTime.getTime() - startTime.getTime())/1000;
		hoursWorked=roundHalfHour(delta/60);
		System.out.println(hoursWorked);
		
	}
	/**
	 * Converts minutes to hours and rounds up to the nearest half hour.
	 * For example, if a person works for 16 minutes it is counted as 30 minute shift.
	 * Likewise if they work 46 minutes it is counted as a full hour
	 * @param duration of minutes 
	 * @return number of hours 
	 */
	private double roundHalfHour(long min){
		System.out.println(min);
		double hrs=min/60, remainder=min % 60;
		if(remainder > 15) hrs+=0.5;
		else if(remainder > 45) ++hrs;
		return hrs;
	}
	/**
	 * Returns start time of work shift
	 * @return String representation of SQL time stamp 
	 */
	public String getStartTime(){
		return startTime.toString();
	}
	/**
	 * Returns end time of work shift
	 * @return String representation of SQL time stamp 
	 */
	public String getEndTime(){
		return endTime.toString();
	}
}