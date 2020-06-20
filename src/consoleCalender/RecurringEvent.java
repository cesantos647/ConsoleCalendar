/**
 * 
 */
package consoleCalender;
import java.time.LocalTime;
import java.time.LocalDate;

/**
 * @author christiansantos
 *
 */
public class RecurringEvent extends Event{
	private String days;
	private LocalDate startDate;
	private LocalDate endDate;
	//Not actually, need to implement this basically a list of numbers that correspond to the days of the week.
	private String daysOfWeek;
	//hashmap dayConverter that converts the MTWRFSA to actual numbers I can use
	
	public RecurringEvent(String name, String days, LocalTime st, LocalTime et, LocalDate startDate, LocalDate endDate) {
		super(name, st, et, startDate);
		
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
	//Don't need compareTo since date was set to startingDate

}
