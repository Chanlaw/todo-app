package todoapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import todoapp.Container.*;
import todoapp.Container;

public class TodoCLI {
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
	
}
