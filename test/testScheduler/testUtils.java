package testScheduler;

import static org.junit.jupiter.api.Assertions.*;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;

import devScheduler.Utils;
import devScheduler.WorkPeriod;
import java.time.*;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



class testUtils {

	

	@Test
	void testGenerateWorkPeriods() {
		LocalDate thur24May2017 = LocalDate.of(2018, 5, 24);
		List<WorkPeriod> workPeriods = Utils.generateWorkPeriods(thur24May2017, 3);
		assertEquals(6, workPeriods.size());
		List<DayOfWeek> generatedWorkingDays = workPeriods.stream()
				.map(WorkPeriod::getDate)
				.map(LocalDate::getDayOfWeek)
				.distinct()
				.collect(Collectors.toList());
		System.out.println(generatedWorkingDays);
		assertEquals(Arrays.asList(THURSDAY, FRIDAY, MONDAY), 
				generatedWorkingDays);
		
	}

}
