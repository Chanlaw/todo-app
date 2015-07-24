/* This class contains the management of event, goal, and action creation
 * 
 */
import java.util.ArrayList;
import org.joda.time.LocalDate;

public class Container {
	
	private static ArrayList<Event> events;
	private static ArrayList<Integer> ids;
	
	// For simplicity
	public enum eventType {
	    GOAL, TASK,  ACTION
	}
	
	// Constructor
	public static void main(String[] args) {
		
		events  = new ArrayList<Event>();
		ids = new ArrayList<Integer>();
		
		// this sets a default, miscellaneous goal
		Event misc = new Goal(0);
		ids.add(0);
		misc.name = "Miscellaneous";
		misc.notes = "A goal of completing random tasks!";
		misc.startDate = new LocalDate(); // sets to day created
		events.add(misc);
		
	}
	
	// Creates an event when prompted
	public void addEvent(eventType t, String name, String notes) {
		int id = generateId();
		Event e;
		switch (t) {
			case GOAL:
				e = new Goal(id);
				break;
			case TASK:
				e = new Task(id);
				break;  
			case ACTION:
				e = new Action(id);
				break;
			default:
				throw new IllegalArgumentException("ERROR: Undefined event type");
		}
		e.setName(name);
		e.setNotes(notes);
		events.add(e);
		ids.add(id);
	}
	
	// Finds an ID currently unused
	public int generateId() {
		int i = 1;
		while (true) {
			if (!ids.contains(i)) {
				return i;
			} else {
				i++;
			}
		}
	}
	
	// Deletes an event (and potential subevents)
	public void deleteTask(int id) {
		for (Event e : events) {
			if (e.id == id) {
			    if (e.getClass().equals(Goal.class)) {
			        // Prompt with "Are you sure? This will delete all related Tasks and Actions"
			    	
			    	
			    } else if (e.getClass().equals(Task.class)) {
			    	// Prompt with "Are you sure? This will delete all related Actions"
			    	
			        
			    } else if (e.getClass().equals(Action.class)) {
			    	events.remove(e);
			    	break;
			    } 
			}
		}
		ids.remove(id);
	}
	
}
