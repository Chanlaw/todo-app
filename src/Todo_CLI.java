import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Todo_CLI {
	
	String startMessage =  "this is where start mesg would be";
	
	public Todo_CLI() {
		// create a scanner so we can read the command-line input
	    Scanner scanner = new Scanner(System.in);
	    System.out.println(startMessage);
	    
	    while(true){
	    	// prompt for the user's name
		    System.out.print(">> ");
		    String cmd = scanner.next();
		    cmd.toUpperCase();
		    
		    switch (cmd) {
		    	case "TASK":
		    		
		    		break;
		    	case "GOAL":
		    		
		    		break;
		    	case "ACTION":
		    		
		    		break;
		    	default: 
		    		System.out.println("ERROR: Command not recognized");
		    }
		    
	    }
	    
	}
}
