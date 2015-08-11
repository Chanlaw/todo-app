/* The Event class is the superclass for Goal, Task, and Action
 * 
 */
import java.util.ArrayList;
import org.joda.time.*;

public class Event {
	public boolean complete = false;
	public int id;
	String name = new String();
	String notes = "";
	LocalDate startDate;
	LocalDate endDate;
	
	public Event(int id) {
		this.id = id;
	}
	
	public void setName(String name)  {
		this.name = name;
	}
	
	public void  setNotes(String notes) {
		this.notes = notes;
	}
	
	public void setStartDate(LocalDate s) {
		this.startDate = s;
	}
	
	public void setEndDate(LocalDate e) {
		this.endDate = e;
	}
	
	// sets the completion status of an event
	public void setComplete() {
		complete = true;
	}
	
	
	
}
