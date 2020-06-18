package consoleCalender;

import java.time.LocalTime;

public class Event implements Comparable<Event>{
	//check conflicts
	private String name;
	private LocalTime startingTime;
	private LocalTime endingTime;
	private Boolean recurring;
	
	//Make 2 constructors, one for recurring events and one for one-time events
	public Event() {
		
	}
	
	public String getName() {
		return name;
	}
	public Boolean getRecurring() {
		return recurring;
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
