package consoleCalender;

import java.time.LocalTime;
import java.time.LocalDate;
public class Event implements Comparable<Event>{
	
	private String name;
	private TimeInterval timeInterval;
	private LocalDate date;
	
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
	
	
}
