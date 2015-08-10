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
		try {
			todoCLI();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// Creates an event when prompted
	public static void addEvent(eventType t, String name, String notes) {
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
	}
	
	// Finds an ID currently unused
	public static int generateId() {
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
	
	
	public static void todoCLI() throws IOException {
		// create a scanner so we can read the command-line input
		String startMessage =  "Welcome to the Todo App.";
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    while(true){
	    	
		    System.out.print(">> ");
		    String cmd = br.readLine();
		    cmd = cmd.toUpperCase().trim();
		    // System.out.println("Command entered: " + cmd);
		    
		    switch (cmd) {
		    	case "TASK":
		    		System.out.print("Enter task name: ");
					String name1 = br.readLine();
		    		System.out.print("Enter task description: \n");
		    		String des1 = br.readLine();
		    		addEvent(eventType.TASK, name1, des1);
		    		System.out.println("Task created.");
		    		break;
		    	case "GOAL":
		    		System.out.print("Enter goal name: ");
		    		String name2 = br.readLine();
		    		System.out.print("Enter goal description: ");
		    		String des2 = br.readLine();
		    		addEvent(eventType.GOAL, name2, des2);
		    		System.out.println("Goal created.");
		    		break;
		    	case "ACTION":
		    		System.out.print("Enter action name: ");
		    		String name3 = br.readLine();
		    		System.out.print("Enter action description: ");
		    		String des3 = br.readLine();
		    		addEvent(eventType.ACTION, name3, des3);
		    		break;
		    	case "HELP": 
		    		try (BufferedReader br2 = new BufferedReader(new FileReader("help.txt"))) {
		    			   String line = null;
		    			   while ((line = br2.readLine()) != null) {
		    			       System.out.println(line);
		    			   }
		    			} 
		    		break;
		    	case "PRINT": 
		    		printAllEvents();
		    		break;
		    	default: 
		    		System.out.println("ERROR: Command not recognized");
		    }   
	    }   
	}
	
	public static void printAllEvents() {
		for (int i : ids) {
			printEvent(i);
		}
	}
	
	public static void printEvent(int id) {
		for (Event e : events) {
			if (e.id == id) {
				System.out.println("Name: " + e.name + "\nDates: " + 
									"\nNotes: " + e.notes);
			}
		}
	}
	
	
	
}
