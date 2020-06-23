/*
 * 
 */
package hw1;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// TODO: Auto-generated Javadoc
/**
 * The Class Event.
 */
public class Event implements Comparable<Event>{
	
	/** The name. */
	private String name;
	
	/** The time interval. */
	private TimeInterval timeInterval;
	
	/** The date. */
	private LocalDate date;
	
	/** The dateformatter. */
	public static DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("M/d/yy");
	
	/** The timeformatter. */
	public static DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("k:mm");
	
	/** The formaldateformatter. */
	public static DateTimeFormatter FORMALDATEFORMATTER = DateTimeFormatter.ofPattern("E, MMM d yyyy");
	
	/**
	 * Instantiates a new event.
	 *
	 * @param name the name
	 * @param st the st
	 * @param et the et
	 * @param date the date
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
	 * Compare to.
	 *
	 * @param that the that
	 * @return the int
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
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		Event event = (Event) obj;
		return this.compareTo(event) == 0;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return getName() + "\n" + getTimeInterval() + " " + getDate().format(Event.DATEFORMATTER);
	}
	
}
