package devScheduler;

import java.time.Duration;

public class Task {
	private String name;
	private Duration duration;
	
	public Duration getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	private Task(String name, Duration duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public static Task create(String name, Duration duration) {
		return new Task(name, duration);
	}
	
	public Task split(Duration remainingDuration) {
		return new Task(this.name, remainingDuration);
		
	}
	
	
	
	
}
