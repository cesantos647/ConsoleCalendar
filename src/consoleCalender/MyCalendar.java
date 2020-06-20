package consoleCalender;
import java.util.HashMap;
import java.util.TreeSet;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Scanner;

public class MyCalendar {
	private Scanner input = new Scanner(System.in);
	private HashMap<String, TreeSet<Event>> eventsList;
	
	public void InitialCalendar(String date) {
		
	}
	public void loadEvents() {
		System.out.println("Loading is done!");
	}
	public void displayMainMenu() {
		System.out.println("Select one of the following main menu options:");
		System.out.println("[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		String output = input.next().toLowerCase();
		System.out.println(output);
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
		String output = input.next().toLowerCase();
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
		
	}
	
	public void eventList() {
		
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
		
	}
	
	public void deleteEvent() {
		
	}
	
	public void quit() {
		System.out.println("Good Bye");
		saveEvents();
		input.close();
	}
	public void dayView(LocalDate date) {
		
		//find all events on this day and print it out. If there's no events, print "no events" or something
		
		System.out.println("[P]revious or [N]ext or [G]o back to the main menu ?");
		String output = input.next().toLowerCase();
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
		
		System.out.println("[P]revious or [N]ext or [G]o back to the main menu ?");
		String output = input.next().toLowerCase();
		if(output.equals("p")) {
			dayView(date.minusMonths(1));
		}
		else if(output.equals("n")) {
			dayView(date.plusMonths(1));
		}
		else if(output.equals("g")) {
			displayMainMenu();
		}	
	}
	public boolean addEvent() {
		return false;
		
	}
	public void deleteSingleEvent() {
		
	}
	public void deleteAllOnDate() {
		
	}
	public void deleteRecurring() {
		
	}
	public void saveEvents() {
		
	}
	public LocalDate askDate() {
		return null;
		
	}
	public String askName() {
		System.out.println("Please enter the name of the event");
		return input.nextLine();
	}
	public LocalTime askStartTime() {
		return null;
	}
	public LocalTime askEndTime(LocalTime startTime) {
		return null;
	}
	public boolean checkEventConflicts(Event newEvent) {
		return false;
	}
	public void run() {
		displayMainMenu();
	}
}
