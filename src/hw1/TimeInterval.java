package hw1;

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

	public boolean conflictsWith(TimeInterval that) {
		return this.getStartTime().isBefore(that.getEndTime()) || this.getEndTime().isAfter(that.getStartTime());
	}
	
	@Override
	public String toString() {
		return startTime + " " + endTime;
	}

}
