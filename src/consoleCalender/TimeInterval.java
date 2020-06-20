package consoleCalender;

import java.time.LocalTime;;

public class TimeInterval {
	private LocalTime startTime;
	private LocalTime endTime;
	
	public TimeInterval(LocalTime st, LocalTime et) {
		startTime = st;
		endTime = et;
	}

	/**
	 * @return the startTime
	 */
	public LocalTime getStartTime() {
		return startTime;
	}

	/**
	 * @return the endTime
	 */
	public LocalTime getEndTime() {
		return endTime;
	}
	
	//Not sure if this is right, please check again.
	public boolean conflictsWith(TimeInterval that) {
		return startTime.compareTo(that.getEndTime()) > 0 && endTime.compareTo(that.getStartTime()) < 0;		
	}

}
