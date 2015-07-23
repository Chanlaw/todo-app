/* The Event class is the superclass for Goal, Task, and Action
 * 
 */
import java.sql.Time;
import java.util.Date;

public class Event {
	// What do we want to use to store time?
	String name = new String();
	String notes = "";
	// set of tags
	Date startDate;
	Date endDate;
	
	public Event() {
		
	}
	
	public void setName(String name)  {
		this.name = name;
	}
	
	public void  setNotes(String notes) {
		this.notes = notes;
	}
	
	public void setStartDate(Date s) {
		this.startDate = s;
	}
	
	public void setEndDate(Date e) {
		this.endDate = e;
	}
	
}
