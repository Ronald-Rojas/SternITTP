package report;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class Entry {
	protected Date startTime;
	protected Date endTime;
	protected float hoursWorked;

	protected Entry(Date startTime, Date endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	protected float computeHoursWorked() {
		long delta = endTime.getTime() - startTime.getTime();
		long deltaSeconds = TimeUnit.SECONDS.toSeconds(delta);
		return hoursWorked = (deltaSeconds / 3600);
	}
}
