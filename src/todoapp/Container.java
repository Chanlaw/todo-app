package todoapp;
/* This class contains the management of event, goal, and action creation
 * 
 */
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.joda.time.LocalDate;

public class Container {
	
	private  ArrayList<Event> events;
	private  ArrayList<Integer> ids;
	
	// For simplicity
	public static enum eventType {
	    GOAL, TASK,  ACTION
	}
	
	// Constructor
	public  void main(String[] args) {
		
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
	public  int addEvent(eventType t, String name, String notes) {
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
		e.id = id;
		events.add(e);
		ids.add(id);
		return id;
	}
	
	// Finds an ID currently unused
	public  int generateId() {
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
	public  void deleteTask(int id) {
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
	
	private  boolean cancel(String s) {
		return s.equals("CANCEL");
	}
	
	public  void printEvent(int id) {
		for (Event e : events) {
			if (e.id == id) {
				System.out.println("Name: " + e.name + "\nDates: " + 
									"\nNotes: " + e.notes + "\nID: " + e.id);
			}
		}
	}
	
	
}
