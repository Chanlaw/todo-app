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
	public static int addEvent(eventType t, String name, String notes) {
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
	public static void deleteTask(int id) {
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
	    System.out.println(startMessage);
	    String controls = "  [T]ASK    Create new task \n" +
	    				  "  [G]OAL    Create new goal \n" +
	    				  "  [A]CTION  Create new action \n" + 
	    				  "  [D]ELETE  Delete an event \n" + 
	    				  "  [E]DIT    Edit an event \n" +
	    				  "  [H]ELP    Display commands";
	    System.out.println(controls + "\n");
	    while(true){
	    	int id;
		    System.out.print(">> ");
		    String cmd = br.readLine();
		    cmd = cmd.toUpperCase().trim();
		    // System.out.println("Command entered: " + cmd);
		    String name;
		    String des;
		    
		    switch (cmd) {
		    	case "TASK":
		    	case "T":
		    		System.out.print("Enter task name: ");
					name = br.readLine();
		    		System.out.print("Enter task description: \n");
		    		des = br.readLine();
		    		id = addEvent(eventType.TASK, name, des);
		    		System.out.println("Task " + name + " created with id " + id + ".");
		    		break;
		    		
		    	case "GOAL":
		    	case "G":
		    		System.out.print("Enter goal name: ");
		    		name = br.readLine();
		    		System.out.print("Enter goal description: ");
		    		des = br.readLine();
		    		id = addEvent(eventType.GOAL, name, des);
		    		System.out.println("Goal" + name +  "created with id " + id + ".");
		    		break;
		    		
		    	case "ACTION":
		    	case "A":
		    		System.out.print("Enter action name: ");
		    		name = br.readLine();
		    		System.out.print("Enter action description: ");
		    		des = br.readLine();
		    		id = addEvent(eventType.ACTION, name, des);
		    		System.out.println("Goal" + name + "created with id " + id + ".");
		    		break;
		    		
		    	case "DELETE":
		    	case "D":
		    		System.out.print("Enter event id: ");
		    		String id_s1 = br.readLine().trim();
		    		int id_i1 = Integer.parseInt(id_s1);
		    		if (ids.contains(id_i1)) {
		    			deleteTask(id_i1);
		    		}
		    		System.out.println("Event deleted.");
		    		break;
		    	case "EDIT": 
		    	case "E":// Invalid event id, what field to edit
		    		System.out.print("Enter event id: ");
		    		String id_s2 = br.readLine().trim();
		    		int id_i2 = Integer.parseInt(id_s2);
		    		if (ids.contains(id_i2)) {
		    			String editMenu = "Edit";
		    			//TODO write code
		    			System.out.println("Feature not implemented.");
		    		}else{
		    			System.out.println("Invalid ID");
		    		}
		    		
		    		break;
		    		
		    	case "HELP":
		    	case "H":
		    		System.out.println(controls);
		    		break;
		    		
		    	case "PRINT": 
		    	case "P":
		    		System.out.print("Enter \"all\", \"completed\", or \"incomplete\": ");
		    		String mod = br.readLine().trim().toUpperCase();
		    		if (mod.equals("COMPLETED")) {
		    			for (int i : ids) {
		    				if (events.get(i).complete) {
		    					printEvent(i);
		    					System.out.println("----");
		    				}
		    			}
		    		} else if (mod.equals("INCOMPLETE")) {
		    			for (int i : ids) {
		    				if (!events.get(i).complete) {
		    					printEvent(i);
		    					System.out.println("----");
		    				}
		    			}
		    		} else {
		    			for (int i : ids) {
		    				printEvent(i);
	    					System.out.println("----");
		    			}
		    		}
		    		
		    		break;
		    		
		    	default: 
		    		System.out.println("ERROR: Command not recognized");
		    }   
	    }   
	    
	}
	
	private static boolean cancel(String s) {
		return s.equals("CANCEL");
	}
	
	public static void printEvent(int id) {
		for (Event e : events) {
			if (e.id == id) {
				System.out.println("Name: " + e.name + "\nDates: " + 
									"\nNotes: " + e.notes + "\nID: " + e.id);
			}
		}
	}
	
	
}
