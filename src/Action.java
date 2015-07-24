/* Action is a small event
 * Action is the only subclass with a non-zero duration
 */
import org.joda.time.*;


public class Action extends Event{
	DateTime startTime;
	DateTime endTime;
	
	public Action(int id) {
		super(id);
	}
	
	public void setStart(int hour, int  minute, DateTime s) {
		this.startTime = s;
	}
	
	public void setEnd(int hour, int  minute, DateTime e) {
		this.endTime = e;
	}
}
