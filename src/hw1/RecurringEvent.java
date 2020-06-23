/**
 * 
 */
package hw1;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.HashMap;

/**
 * The Class RecurringEvent.
 *
 * @author christiansantos
 */
public class RecurringEvent extends Event{
	
	/** The days. */
	private String days;
	
	/** The start date. */
	private LocalDate startDate;
	
	/** The end date. */
	private LocalDate endDate;
	
	/** The days of week. */
	private HashSet<DayOfWeek> daysOfWeek = new HashSet<>();
	
	/** The Constant dayConverter. */
	private static final HashMap<String, DayOfWeek> dayConverter = new HashMap<>();
	static {
		dayConverter.put("M", DayOfWeek.MONDAY);
		dayConverter.put("T", DayOfWeek.TUESDAY);
		dayConverter.put("W", DayOfWeek.WEDNESDAY);
		dayConverter.put("R", DayOfWeek.THURSDAY);
		dayConverter.put("F", DayOfWeek.FRIDAY);
		dayConverter.put("A", DayOfWeek.SATURDAY);
		dayConverter.put("S", DayOfWeek.SUNDAY);
	}
	
	/**
	 * Instantiates a new recurring event.
	 *
	 * @param name the name
	 * @param days the days
	 * @param st the st
	 * @param et the et
	 * @param startDate the start date
	 * @param endDate the end date
	 */
	public RecurringEvent(String name, String days, LocalTime st, LocalTime et, LocalDate startDate, LocalDate endDate) {
		super(name, st, et, startDate);
		this.startDate = startDate;
		this.endDate = endDate;
		this.days = days;
		String[] abbreviatedDays = days.split("");
		for(String abrvDay : abbreviatedDays) {
			daysOfWeek.add(dayConverter.get(abrvDay));
		}
	}
	
	/**
	 * Gets the days of week.
	 *
	 * @return the days of week
	 */
	public HashSet<DayOfWeek> getDaysOfWeek() {
		return daysOfWeek;
	}

	/**
	 * Gets the days.
	 *
	 * @return the days
	 */
	public String getDays() {
		return days;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}
	
	/**
	 * Checks if is within.
	 *
	 * @param date the date
	 * @return true, if is within
	 */
	public boolean isWithin(LocalDate date) {
		return date.isAfter(getStartDate()) && date.isBefore(getEndDate()) || date.isEqual(getStartDate()) || date.isEqual(getEndDate());
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.getName() + "\n" + days + " " + this.getTimeInterval() + " " + this.getStartDate().format(Event.DATEFORMATTER) + " " + this.getEndDate().format(Event.DATEFORMATTER);
	}

}
