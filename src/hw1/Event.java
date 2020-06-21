package hw1;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event implements Comparable<Event>{
	
	private String name;
	private TimeInterval timeInterval;
	private LocalDate date;
	public static DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("M/d/yy");
	public static DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("k:mm");
	
	public Event(String name, LocalTime st, LocalTime et, LocalDate date) {
		this.name = name;
		this.timeInterval = new TimeInterval(st, et);
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	
	public TimeInterval getTimeInterval() {
		return timeInterval;
	}
	
	public LocalDate getDate() {
		return date;
	}

	@Override
	public int compareTo(Event that) {
		if(date == that.getDate()) {
			return this.getTimeInterval().getStartTime().compareTo(that.getTimeInterval().getStartTime());
		}
		else {
			return date.compareTo(that.getDate());
		}
	}

	@Override
	public boolean equals(Object obj) {
		Event event = (Event) obj;
		return this.compareTo(event) == 0;
	}
	
	@Override
	public String toString() {
		return getName() + "\n" + getTimeInterval() + " " + getDate().format(Event.DATEFORMATTER);
	}
	
}
