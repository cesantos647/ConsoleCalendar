/**
 *  Event Class
 *  @author Christian Santos
 *  
 *  @version 1.0.0 06/22/20
 *  
 *  Copyright Christian Santos to Present
 *  All rights reserved
 */
package hw1;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Class Event holds the event details for a one-time event
 * and the DateTimeFormatter static variable to format a the event details
 * 
 * @author Christian Santos
 */
public class Event implements Comparable<Event>{
	
	/** The name. */
	private String name;
	
	/** The time interval. */
	private TimeInterval timeInterval;
	
	/** The date. */
	private LocalDate date;
	
	/** The regular Date formatter. */
	public static DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("M/d/yy");
	
	/** The Time formatter. */
	public static DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("k:mm");
	
	/** The Formal Date formatter. */
	public static DateTimeFormatter FORMALDATEFORMATTER = DateTimeFormatter.ofPattern("E, MMM d yyyy");
	
	/**
	 * Instantiates a new event.
	 *
	 * @param name the name of the event
	 * @param st the start time of the event
	 * @param et the end time of the event
	 * @param date the date of the event
	 */
	public Event(String name, LocalTime st, LocalTime et, LocalDate date) {
		this.name = name;
		this.timeInterval = new TimeInterval(st, et);
		this.date = date;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the time interval.
	 *
	 * @return the time interval
	 */
	public TimeInterval getTimeInterval() {
		return timeInterval;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Compares this event to another event.
	 * First compare by date, then compare by time intervals
	 *
	 * @param that the other event being compared with
	 * @return the the result of the comparison
	 */
	@Override
	public int compareTo(Event that) {
		if(date.equals(that.getDate())) {
			return this.getTimeInterval().getStartTime().compareTo(that.getTimeInterval().getStartTime());
		}
		else {
			return date.compareTo(that.getDate());
		}
	}

	/**
	 * Checks if the object is equal to this event
	 *
	 * @param obj the given object
	 * @return true, if the object is equal to this event
	 */
	@Override
	public boolean equals(Object obj) {
		Event event = (Event) obj;
		return this.compareTo(event) == 0;
	}
	
	/**
	 * Converts Event object to a readable string
	 *
	 * @return the readable string
	 */
	@Override
	public String toString() {
		return getName() + "\n" + getTimeInterval() + " " + getDate().format(Event.DATEFORMATTER);
	}
	
}
