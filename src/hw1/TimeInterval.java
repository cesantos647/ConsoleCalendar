/**
 * TimeInterval Class
 *  @author Christian Santos
 *  
 *  @version 1.0.0 06/22/20
 *  
 *  Copyright Christian Santos to Present
 *  All rights reserved
 */
package hw1;

import java.time.LocalTime;;


/**
 * The Class TimeInterval contains the start and end time
 * of a time interval and finds time conflicts between other
 * TimeInterval objects.
 * 
 * @author Christian Santos
 */
public class TimeInterval {
	
	/** The start time. */
	private LocalTime startTime;
	
	/** The end time. */
	private LocalTime endTime;
	
	/**
	 * Instantiates a new time interval.
	 *
	 * @param st the start time
	 * @param et the end time
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
	 * Checks to see if the passed TimeInterval conflicts
	 * with this TimeInterval.
	 *
	 * @param that the passed TimeInterval
	 * @return true, if there is a time conflict between the TimeIntervals
	 */
	public boolean conflictsWith(TimeInterval that) {
		return this.getStartTime().isBefore(that.getEndTime()) || this.getEndTime().isAfter(that.getStartTime());
	}
	
	/**
	 * Covnerts the TimeInterval object to a readable string
	 *
	 * @return the readable string
	 */
	@Override
	public String toString() {
		return startTime + " " + endTime;
	}

}
