/*
 * 
 */
package hw1;

import java.time.LocalTime;;


/**
 * The Class TimeInterval.
 */
public class TimeInterval {
	
	/** The start time. */
	private LocalTime startTime;
	
	/** The end time. */
	private LocalTime endTime;
	
	/**
	 * Instantiates a new time interval.
	 *
	 * @param st the st
	 * @param et the et
	 */
	public TimeInterval(LocalTime st, LocalTime et) {
		startTime = st;
		endTime = et;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the startTime
	 */
	public LocalTime getStartTime() {
		return startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the endTime
	 */
	public LocalTime getEndTime() {
		return endTime;
	}

	/**
	 * Conflicts with.
	 *
	 * @param that the that
	 * @return true, if successful
	 */
	public boolean conflictsWith(TimeInterval that) {
		return this.getStartTime().isBefore(that.getEndTime()) || this.getEndTime().isAfter(that.getStartTime());
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return startTime + " " + endTime;
	}

}
