package devScheduler;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;

public class WorkPeriod {
	private LocalTime start;
	private long lengthInMinutes; //hours will be stored in minutes
	private long availableTimeInMinutes;
	private List<Task> tasks;
	
	public long getAvailableTimeInMinutes() {
		return availableTimeInMinutes;
	}

	public void addAvailableTimeInMinutes(long timeToAdd) {
		this.availableTimeInMinutes += timeToAdd;
	}

	private LocalDate date;
	private ZoneId zoneId;
	
	private WorkPeriod(LocalTime start, 
			long length,  
			LocalDate date,
			ZoneId zoneId) throws WeekendException {
		if(date.getDayOfWeek().getValue() > 5) {
			throw new WeekendException();
		}
		this.date=date;
		this.start=start;
		this.lengthInMinutes=length*60;
		this.zoneId = zoneId;
		availableTimeInMinutes = this.getLengthInMinutes();
		this.tasks = new ArrayList<Task>();
		
	}
	
	private WorkPeriod(LocalTime start, 
			double length, 
			LocalDate date,
			ZoneId zoneId) throws WeekendException {
		if(date.getDayOfWeek().getValue() > 5) {
			throw new WeekendException();
		}
		this.date=date;
		this.start=start;
		this.lengthInMinutes=(long)length*60;
		this.zoneId = zoneId;
		availableTimeInMinutes = this.getLengthInMinutes();
		this.tasks = new ArrayList<Task>();
	}
		
	public LocalTime getStart() {
		return start;
	}

	public Duration getLength() {
		return Duration.ofMinutes(lengthInMinutes);
	}
	
	public long getLengthInMinutes() {
		return lengthInMinutes;
	}

	public LocalDate getDate() {
		return date;
	}

	public ZoneId getZoneId() {
		return zoneId;
	}

	public static WorkPeriod create(LocalTime start, 
			double length, 
			LocalDate date,
			ZoneId zoneId) throws WeekendException {
		return new WorkPeriod(
				start,
				length,
				date,
				zoneId);
	}
	
	public static WorkPeriod create(LocalTime start, 
			long length, 
			LocalDate date,
			ZoneId zoneId) throws WeekendException {
		return new WorkPeriod(
				start,
				length,
				date,
				zoneId);
	}
}
