/* Task is a medium event that contains Action tags
 * 
 */
import java.util.ArrayList;

import org.joda.time.*;

public class Task extends Event {
	ArrayList<Action> relatedActions;
	
	public Task(int id) {
		super(id);
		relatedActions = new ArrayList<Action>();
	}
	
	public int[] getActions() {
		int[] ids = new int[relatedActions.size()];
		int i = 0;
		for (Action t : relatedActions) {
			ids[i] = t.id;
			i++;
		}
		return ids;
	}
}
