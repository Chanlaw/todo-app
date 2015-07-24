/* I am using this class to do basic calendar functionality (minus the gui)
 * 
 */

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateInterface {
		LocalTime localTime;
	
	public static void main(String[] args) {
		
		String dob = "2002-01-15"; // used for example
		
        LocalTime localTime = new LocalTime(); // Hours:Mins:Seconds.Millisec
        LocalDate localDate = new LocalDate(); // Year-Month-Day
        DateTime dateTime = new DateTime(); 
        DateTimeZone dateTimeZone = DateTimeZone.getDefault();

        System.out.println("LocalTime : " + localTime.toString());
        System.out.println("localDate : " + localDate.toString());
        System.out.println("DateTimeZone : " + dateTimeZone.toString());
        
        System.out.println("Age in Years : "
                + Years.yearsBetween(DateTime.parse(dob), dateTime).getYears());
        System.out.println("Age in Months : "
                + Months.monthsBetween(DateTime.parse(dob), dateTime)
                        .getMonths());
    }
}