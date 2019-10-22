package devScheduler;

public class WeekendException extends Exception {
	
	public WeekendException(String msg){
	        super(msg);
	    }
	
	public WeekendException() {
			this("The date/day you chose is not a business day");
	}
}
