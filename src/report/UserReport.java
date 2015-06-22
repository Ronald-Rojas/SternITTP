package report;

import java.sql.Date;
import java.util.ArrayList;

public class UserReport {

	ArrayList<Entry> timeSlotsWorked;
	float totalTimeWorked;

	public UserReport(String userName, Date begin, Date end) {
		timeSlotsWorked = new ArrayList<Entry>();
		totalTimeWorked = 0;
		computeTotalHours();
	}

	public ArrayList<Entry> getTimeSlotsWorked() {
		return timeSlotsWorked;
	}

	public float getTotalTimeWorked() {
		return totalTimeWorked;
	}

	public void computeTotalHours() {
		if (getTotalTimeWorked() != 0)
			return;

		for (Entry timeSlot : timeSlotsWorked)
			totalTimeWorked += timeSlot.computeHoursWorked();
	}
}