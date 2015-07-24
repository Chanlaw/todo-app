/* Goal is a large event that contains Tasks and Action tags
 * 
 */
import java.util.ArrayList;
import java.util.Date;

public class Goal extends Event {
	ArrayList<Task> relatedTasks; 
	
	
	// constructor
	public Goal(int id) {
		super(id);
		ArrayList<Task> event = new ArrayList<Task>();
	}
	
	// adds a task to the arraylist of tasks
	public void addTask(Task t) {
		relatedTasks.add(t);
	}
	
	// returns the id number of the associated tasks with this goal
	public int[] getTasks() {
		int[] ids = new int[relatedTasks.size()];
		int i = 0;
		for (Task t : relatedTasks) {
			ids[i] = t.id;
			i++;
		}
		return ids;
	}
}
