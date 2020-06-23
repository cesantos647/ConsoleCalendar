/**
 *  MyCalendar Class
 *  @author Christian Santos
 *  
 *  @version 1.0.0 06/22/20
 *  
 *  Copyright Christian Santos to Present
 *  All rights reserved
 */
package consoleCalendar;

import java.util.TreeSet;
import java.time.LocalTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Class MyCalendar manages the event list,
 * takes input from the user, and carries out
 * user commands
 * 
 * @author Christian Santos
 */
public class MyCalendar {
	
	/** The user input. */
	private Scanner input = new Scanner(System.in);
	
	/** The one time event list. */
	private TreeSet<Event> oneTimeEventList = new TreeSet<>();
	
	/** The recurring event list. */
	private TreeSet<RecurringEvent> recurringEventList =  new TreeSet<>();
	
	/**
	 * Prints the initial calendar at the beginning of the application.
	 */
	public void printInitialCalendar() {
		LocalDate current = LocalDate.now();
		String monthYearHeader = "      " + current.getMonth().toString() + " " + current.getYear();
		String weekHeader = "Mo Tu We Th Fr Sa Su";
		LocalDate firstDay = LocalDate.of(current.getYear(), current.getMonth(), 1);
		System.out.println(monthYearHeader + "\n" + weekHeader);
		
		LocalDate reference = firstDay;
		for(int i = 1; i < reference.getDayOfWeek().getValue(); i++) {
			System.out.print("   ");
		}
		while(reference.getMonth().equals(current.getMonth())) {
			if(reference.getDayOfMonth() == current.getDayOfMonth()) {
				System.out.print("{");
			}
			if(reference.getDayOfMonth() < 10) {
				System.out.print(" ");
			}
			System.out.print(reference.getDayOfMonth());
			if(reference.getDayOfMonth() == current.getDayOfMonth()) {
				System.out.print("}");
			}
			System.out.print(" ");
			if(reference.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				System.out.print("\n");
			}
			
			reference = reference.plusDays(1);
		}
		System.out.println();
		
	}
	
	/**
	 * Populate calendar with the events from events.txt
	 */
	public void populateEvents() {
		try {
			
			Scanner eventLoader = new Scanner(new File("events.txt"));
			while(eventLoader.hasNextLine()) {
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
				else if(params.length == 5) {
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
	
	/**
	 * Display main menu.
	 */
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
	
	/**
	 * Redirects user to current day view or current month view
	 */
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
	
	/**
	 * Redirects user to day view of date inputed
	 */
	public void goTo() {
		LocalDate newDate = askDate();
		dayView(newDate);
	}
	
	/**
	 * Prints out all events on the calendar
	 */
	public void eventList() {
		System.out.println("One Time Events");
		for(Event e: oneTimeEventList) {
			System.out.println(e);
		}
		System.out.println("\n Recurring Events");
		for(RecurringEvent re: recurringEventList) {
			System.out.println(re);
		}
		System.out.println();
		displayMainMenu();
	}
	
	/**
	 * Creates an event from user input
	 */
	public void createEvent() {
		String eventName = askName();
		LocalDate eventDate = askDate();
		LocalTime eventStartTime = askStartTime();
		LocalTime eventEndTime = askEndTime(eventStartTime);
		Event newEvent = new Event(eventName, eventStartTime, eventEndTime, eventDate);
		if(!checkEventConflicts(newEvent)) {
			oneTimeEventList.add(newEvent);
			System.out.println("'" + newEvent.getName() + "' has been added to the calendar");
		}
		else {
			System.out.println("There are conflicts creating this event. Sending you back to the main menu");
		}
		displayMainMenu();
		
	}
	
	/**
	 * Redirects user to delete a one time event, all events on one day,
	 * or a recurring event
	 */
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
	
	/**
	 * Quits the application
	 */
	public void quit() {
		System.out.println("Good Bye");
		saveEvents();
		input.close();
		System.exit(0);
	}
	
	/**
	 * Displays day view of the date inputed
	 *
	 * @param date the date inputed
	 */
	public void dayView(LocalDate date) {
		
		TreeSet<Event> dayEvents = new TreeSet<>();
		for(Event e: oneTimeEventList) {
			if(e.getDate().equals(date)) {
				dayEvents.add(e);
			}
		}
		for(RecurringEvent re: recurringEventList) {
			if(re.isWithin(date) && re.getDaysOfWeek().contains(date.getDayOfWeek())) {
				dayEvents.add(re);
			}
		}
		System.out.println(date.format(Event.FORMALDATEFORMATTER));
		if(dayEvents.isEmpty()) {
			System.out.println("There are no events scheduled for today");
		}
		else {
			//Display events
			for(Event e: dayEvents) {
				System.out.println(e.getName() + " : " + e.getTimeInterval().getStartTime() + "-" +e.getTimeInterval().getEndTime());
			}
		}
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
		else {
			System.out.println("Unknown command. Sending you back to the main menu");
			displayMainMenu();
		}
	}
	
	/**
	 * Displays month view of the date inputed
	 *
	 * @param date the date inputed
	 */
	public void monthView(LocalDate date) {
		//print out month view
		String monthYearHeader = "      " + date.getMonth().toString() + " " + date.getYear();
		String weekHeader = "Mo Tu We Th Fr Sa Su";
		LocalDate firstDay = LocalDate.of(date.getYear(), date.getMonth(), 1);
		System.out.println(monthYearHeader + "\n" + weekHeader);
		
		LocalDate reference = firstDay;
		for(int i = 1; i < reference.getDayOfWeek().getValue(); i++) {
			System.out.print("   ");
		}
		while(reference.getMonth().equals(date.getMonth())) {
			
			
			if(hasEvents(reference)) {
				System.out.print("{");
			}
			else if(reference.getDayOfMonth() < 10) {
				System.out.print(" ");
			}
			System.out.print(reference.getDayOfMonth());
			if(hasEvents(reference)) {
				System.out.print("}");
			}
			else {
				System.out.print(" ");
			}
			if(reference.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				System.out.print("\n");
			}
			
			reference = reference.plusDays(1);
		}
		System.out.println();
		
		
		System.out.println("[P]revious or [N]ext or [G]o back to the main menu ?");
		String output = input.nextLine().toLowerCase();
		if(output.equals("p")) {
			monthView(date.minusMonths(1));
		}
		else if(output.equals("n")) {
			monthView(date.plusMonths(1));
		}
		else if(output.equals("g")) {
			displayMainMenu();
		} 
		else {
			System.out.println("Unknown hotkey. Returning to main menu");
			displayMainMenu();
		}
	}
	
	/**
	 * Checks for events on a given date
	 *
	 * @param date the date inputed
	 * @return true if there are events on the given date
	 */
	public boolean hasEvents(LocalDate date) {
		TreeSet<Event> dayEvents = new TreeSet<>();
		for(Event e: oneTimeEventList) {
			if(e.getDate().equals(date)) {
				dayEvents.add(e);
			}
		}
		for(RecurringEvent re: recurringEventList) {
			if(re.isWithin(date) && re.getDaysOfWeek().contains(date.getDayOfWeek())) {
				dayEvents.add(re);
			}
		}
		return !dayEvents.isEmpty();
	}

	/**
	 * Delete single event given the date and name
	 */
	public void deleteSingleEvent() {
		LocalDate date = askDate();
		TreeSet<Event> dayEvents = new TreeSet<>();
		for(Event e: oneTimeEventList) {
			if(e.getDate().equals(date)) {
				dayEvents.add(e);
			}
		}
		for(RecurringEvent re: recurringEventList) {
			if(re.isWithin(date) && re.getDaysOfWeek().contains(date.getDayOfWeek())) {
				dayEvents.add(re);
			}
		}
		System.out.println(date.format(Event.FORMALDATEFORMATTER));
		if(dayEvents.isEmpty()) {
			System.out.println("There are no events scheduled for today");
			System.out.println("Because there is no events today, you cannot remove any events. Sending you back to the main menu.");
			displayMainMenu();
		}
		else {
			//display events on that day
			for(Event e: dayEvents) {
				System.out.println(e.getName() + " : " + e.getTimeInterval().getStartTime() + "-" +e.getTimeInterval().getEndTime());
			}
			String name = askName();
			//If can be found, delete. If not, deleteSingleEvent to start over
			Event deletedSingleEvent = null;
			for(Event e: dayEvents) {
				if(e.getName().equalsIgnoreCase(name)) {
					deletedSingleEvent = e;
				}
			}
			if(deletedSingleEvent == null) {
				System.out.println("The event cannot be found. Please try again.");
				deleteSingleEvent();
			}
			else {
				oneTimeEventList.remove(deletedSingleEvent);
				System.out.println(deletedSingleEvent.getName() + " has been deleted");
			}
			displayMainMenu();
		}
	}
	
	/**
	 * Delete all events on given date
	 */
	public void deleteAllOnDate() {
		TreeSet<Event> deletedEvents = new TreeSet<>();
		LocalDate date = askDate();
		for(Event e: oneTimeEventList) {
			if(e.getDate().equals(date)) {
				deletedEvents.add(e);
			}
		}
		//delete all events
		oneTimeEventList.removeAll(deletedEvents);
		System.out.println("All events on " + date.format(Event.FORMALDATEFORMATTER) + " have been deleted");
		displayMainMenu();
	}
	
	/**
	 * Delete a recurring event based on name
	 */
	public void deleteRecurring() {
		String name = askName();
		TreeSet<RecurringEvent> deletedEvent = new TreeSet<>();
		for(RecurringEvent re: recurringEventList) {
			if(re.getName().equalsIgnoreCase(name)) {
				deletedEvent.add(re);
			}
 		}
		if(deletedEvent.isEmpty()) {
			System.out.println("There is no recurring event by that name. You will be sent back to the main menu");
		}
		else {
			recurringEventList.removeAll(deletedEvent);
			System.out.println("The" + name + " recurring event has been deleted");
		}
		displayMainMenu();
	}
	
	/**
	 * Save events onto output.txt
	 */
	public void saveEvents() {
		try {
			File output = new File("output.txt");
			output.createNewFile();
			FileWriter writer = new FileWriter(output);
			for(Event e: oneTimeEventList) {
				writer.write(e.toString() + "\n");
			}
			for(RecurringEvent re: recurringEventList) {
				writer.write(re.toString() + "\n");
			}
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Asks user to input a desired date
	 *
	 * @return the date inputed by the user
	 */
	public LocalDate askDate() {
		System.out.println("Please enter the date you want in the form MM/DD/YY");
		return LocalDate.parse(input.nextLine(), Event.DATEFORMATTER);
		
	}
	
	/**
	 * Asks user to input a desired name
	 *
	 * @return the name inputed by the user
	 */
	public String askName() {
		System.out.println("Please enter the name of the event");
		String output = input.nextLine();
		return output;
	}
	
	/**
	 * Asks user to input a desierd start time.
	 *
	 * @return the start time inputed by the user
	 */
	public LocalTime askStartTime() {
		System.out.println("Please input the start time in the 24 hour format HH:MM");
		return LocalTime.parse(input.nextLine(), Event.TIMEFORMATTER);
	}
	
	/**
	 * Asks user to input a desired end time.
	 *
	 * @param startTime the start time previously inputed
	 * @return the end time inputed by the user
	 */
	public LocalTime askEndTime(LocalTime startTime) {
		System.out.println("Please input the end time in the 24 hour format HH:MM");
		LocalTime output = LocalTime.parse(input.nextLine(), Event.TIMEFORMATTER);
		if(output.isAfter(startTime)) {
			return output;
		}
		else {
			System.out.println("End time is not after the given start time. Please input a new end time.");
			return askEndTime(startTime);
		}
	}
	
	/**
	 * Check if there are any event conflicts between the event passed
	 * and the rest of the calendar
	 *
	 * @param newEvent the new event
	 * @return true, if there are any conflicts with an event on the calendar
	 */
	public boolean checkEventConflicts(Event newEvent) {
		for(Event e : oneTimeEventList) {
			if(e.getTimeInterval().conflictsWith(newEvent.getTimeInterval()) && e.getDate().equals(newEvent.getDate())) {
				System.out.println(e.getName() + " conflicts");
				return true;
			}
		}
		for(RecurringEvent re : recurringEventList) {
			if(re.getTimeInterval().conflictsWith(newEvent.getTimeInterval())) {
				//If the date is within the RecurringEvent and if the day is within the daysOfWeek
				if(re.isWithin(newEvent.getDate()) && re.getDaysOfWeek().contains(newEvent.getDate().getDayOfWeek())) {
					System.out.println(re.getName() + " conflicts");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Run the application
	 */
	public void run() {
		printInitialCalendar();
		populateEvents();
		displayMainMenu();
	}
}
