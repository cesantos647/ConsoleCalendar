package consoleCalender;

import java.util.TreeSet;
import java.time.LocalTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class MyCalendar {
	private Scanner input = new Scanner(System.in);
	private TreeSet<Event> oneTimeEventList = new TreeSet<>();
	private TreeSet<RecurringEvent> recurringEventList =  new TreeSet<>();
	
	public void printInitialCalendar() {
		
	}
	public void populateEvents() {
		try {
			System.out.println(new File("event.txt").exists());
			Scanner eventLoader = new Scanner(new File("events"));
			while(eventLoader.hasNext()) {
				String name = eventLoader.nextLine();
				String[] params = eventLoader.nextLine().split(" ");
				//one time event
				if(params.length == 3) {
					LocalDate date = LocalDate.parse(params[0], Event.DATEFORMATTER);
					LocalTime startTime = LocalTime.parse(params[1], Event.TIMEFORMATTER);
					LocalTime endTime = LocalTime.parse(params[2], Event.TIMEFORMATTER);
					Event newEvent = new Event(name, startTime, endTime, date);
					oneTimeEventList.add(newEvent);
				}
				//recurring event
				if(params.length == 5) {
					String days = params[0];
					LocalTime startTime = LocalTime.parse(params[1], Event.TIMEFORMATTER);
					LocalTime endTime = LocalTime.parse(params[2], Event.TIMEFORMATTER);
					LocalDate startDate = LocalDate.parse(params[3], Event.DATEFORMATTER);
					LocalDate endDate = LocalDate.parse(params[4], Event.DATEFORMATTER);
					RecurringEvent newEvent = new RecurringEvent(name, days, startTime, endTime, startDate, endDate);
					recurringEventList.add(newEvent);
				}
			}
			eventLoader.close();
			System.out.println("Loading is done!");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
	}
	public void displayMainMenu() {
		System.out.println("Select one of the following main menu options:");
		System.out.println("[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		String output = input.nextLine().toLowerCase();
		if(output.equals("v")) {
			view();
		}
		else if(output.equals("c")) {
			createEvent();
		}
		else if(output.equals("g")) {
			goTo();
		}
		else if(output.equals("e")) {
			eventList();
		}
		else if(output.equals("d")) {
			deleteEvent();
		}
		else if(output.equals("q")) {
			quit();
		}
		else {
			System.out.println("Unknown command. Please try again.");
			displayMainMenu();
		}
		
	}
	public void view() {
		System.out.println("[D]ay view or [M]onth view ?");
		LocalDate date = LocalDate.now();
		String output = input.nextLine().toLowerCase();
		if(output.equals("d")) {
			dayView(date);
		}
		else if(output.equals("m")) {
			monthView(date);
		}
		else {
			System.out.println("Unknown command");
			view();
		}
	}
	
	public void goTo() {
		LocalDate newDate = askDate();
		dayView(newDate);
	}
	
	public void eventList() {
		//print out all of the events
		displayMainMenu();
	}
	
	public void createEvent() {
		String eventName = askName();
		LocalDate eventDate = askDate();
		LocalTime eventStartTime = askStartTime();
		LocalTime eventEndTime = askEndTime(eventStartTime);
		Event newEvent = new Event(eventName, eventStartTime, eventEndTime, eventDate);
		if(checkEventConflicts(newEvent)) {
			//add to oneTimeEvent list
		}
		displayMainMenu();
		
	}
	
	public void deleteEvent() {
		System.out.println("Delete [S]elected  [A]ll   [DR]ecurring ? You may also [G]o back");
		String output = input.nextLine().toLowerCase();
		if(output.equals("s")) {
			deleteSingleEvent();
		}
		else if(output.equals("a")) {
			deleteAllOnDate();
		}
		else if(output.equals("dr")) {
			deleteRecurring();
		}
		else if(output.equals("g")) {
			displayMainMenu();
		}
		else {
			System.out.println("Unknown hotkey. Please try again");
			deleteEvent();
		}
	}
	
	public void quit() {
		System.out.println("Good Bye");
		saveEvents();
		input.close();
		System.exit(0);
	}
	public void dayView(LocalDate date) {
		
		//find all events on this day and print it out. If there's no events, print "no events" or something
		
		System.out.println("[P]revious or [N]ext or [G]o back to the main menu ?");
		String output = input.nextLine().toLowerCase();
		if(output.equals("p")) {
			dayView(date.minusDays(1));
		}
		else if(output.equals("n")) {
			dayView(date.plusDays(1));
		}
		else if(output.equals("g")) {
			displayMainMenu();
		}
	}
	public void monthView(LocalDate date) {
		//print out month view
		System.out.println("[P]revious or [N]ext or [G]o back to the main menu ?");
		String output = input.nextLine().toLowerCase();
		if(output.equals("p")) {
			dayView(date.minusMonths(1));
		}
		else if(output.equals("n")) {
			dayView(date.plusMonths(1));
		}
		else if(output.equals("g")) {
			displayMainMenu();
		} 
		else {
			System.out.println("Unknown hotkey. Returning to main menu");
			displayMainMenu();
		}
	}
	public boolean addEvent() {
		return false;
		
	}
	public void deleteSingleEvent() {
		LocalDate date = askDate();
		//display events on that day
		String name = askName();
		//If can be found, delete. If not, deleteSingleEvent to start over
		displayMainMenu();
	}
	public void deleteAllOnDate() {
		LocalDate date = askDate();
		//delete all events
		displayMainMenu();
	}
	public void deleteRecurring() {
		String name = askName();
		//delete recurringEvent
		displayMainMenu();
	}
	public void saveEvents() {
		
	}
	public LocalDate askDate() {
		System.out.println("Please enter the date you want in the form MM/DD/YY");
		return LocalDate.parse(input.nextLine(), Event.DATEFORMATTER);
		
	}
	public String askName() {
		System.out.println("Please enter the name of the event");
		String output = input.nextLine();
		return output;
	}
	public LocalTime askStartTime() {
		System.out.println("Please input the startTime in the 24 hour format HH:MM");
		return LocalTime.parse(input.nextLine(), Event.TIMEFORMATTER);
	}
	public LocalTime askEndTime(LocalTime startTime) {
		System.out.println("Please input the endTime in the 24 hour format HH:MM");
		LocalTime output = LocalTime.parse(input.nextLine(), Event.TIMEFORMATTER);
		if(output.isAfter(startTime)) {
			return output;
		}
		else {
			System.out.println("End time is not after the given start time. Please input a new end time.");
			return askEndTime(startTime);
		}
	}
	public boolean checkEventConflicts(Event newEvent) {
		return false;
	}
	public boolean checkEventConflicts(RecurringEvent newEvent) {
		return false;
	}
	public void run() {
		printInitialCalendar();
		populateEvents();
		displayMainMenu();
	}
}
