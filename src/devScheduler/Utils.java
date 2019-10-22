package devScheduler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Iterator;

public class Utils {

	public static List<LocalDate> generateWorkingDays(LocalDate date, long dayCount) {
		return Stream.iterate(date, d -> d.plusDays(1))
				.filter(Utils::isWorkingDay)
				.limit(dayCount)
				.collect(Collectors.toList());
	}
	
	public static List<WorkPeriod> generateWorkPeriods(
			LocalDate date, 
			long dayCount, 
			LocalTime amStart, 
			double amLength,
			LocalTime pmStart,
			double pmLength
			){
		List<LocalDate> workingDays=generateWorkingDays(date, dayCount);
		List<WorkPeriod> workPeriods= new ArrayList<WorkPeriod>();
		Iterator<LocalDate> workingDaysIterator = workingDays.iterator();
		LocalDate workingDay;
		while(workingDaysIterator.hasNext()) {
			try{
				workingDay = workingDaysIterator.next();
				workPeriods.add(WorkPeriod.create(amStart, amLength, workingDay, ZoneId.systemDefault()));
				workPeriods.add(WorkPeriod.create(pmStart, pmLength, workingDay, ZoneId.systemDefault()));
			} catch(WeekendException e) {
				System.out.println("The workPeriods generated contains a weekend date, which means working days haven't been properly generated" + e.getMessage());
			}
		}
		return workPeriods;
	}
	
	public static List<WorkPeriod> generateWorkPeriods(
			LocalDate date,
			long dayCount) {
		return generateWorkPeriods(date, dayCount, LocalTime.of(9, 00), 3.5, LocalTime.of(13, 30), 3.5);
	}
	

	
	public static boolean isWorkingDay(LocalDate date) {
		return date.getDayOfWeek().getValue()<6;
	}
}
