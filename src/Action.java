/* Action is a small event
 * Action is the only subclass with a non-zero duration
 */
import java.sql.Time;
import java.util.Date;

public class Action extends Event{
	Time startTime;
	Time endTime;
	
	
	public Action(int id) {
		super(id);
	}
	
	@SuppressWarnings("deprecation")
	public void setStart(int hour, int  minute, Date s) {
		this.startTime = new Time(hour, minute, 0);
	}
	
	@SuppressWarnings("deprecation")
	public void setEnd(int hour, int  minute, Date d) {
		this.endTime = new Time(hour, minute, 0);
	}
}
