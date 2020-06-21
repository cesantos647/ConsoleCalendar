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
 * @author christiansantos
 *
 */
public class RecurringEvent extends Event{
	private String days;
	private LocalDate startDate;
	private LocalDate endDate;
	private HashSet<DayOfWeek> daysOfWeek = new HashSet<>();
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
	
	public HashSet<DayOfWeek> getDaysOfWeek() {
		return daysOfWeek;
	}

	/**
	 * @return the days
	 */
	public String getDays() {
		return days;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public boolean isWithin(LocalDate date) {
		return date.isAfter(getStartDate()) && date.isBefore(getEndDate()) || date.isEqual(getStartDate()) || date.isEqual(getEndDate());
	}
	@Override
	public String toString() {
		return this.getName() + "\n" + days + " " + this.getTimeInterval() + " " + this.getStartDate().format(Event.DATEFORMATTER) + " " + this.getEndDate().format(Event.DATEFORMATTER);
	}

}
