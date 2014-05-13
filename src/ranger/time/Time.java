package ranger.time;

public class Time {
	
	public boolean isDark() {
		return timeOfDay.isDark;
	}
	
	public void showDayTime() {
		System.out.print("Day " + (time/DAY_LENGTH + 1) + " of your wanderings. ");
		
		System.out.println(describeTimeOfDay());
	}
	
	public String describeTimeOfDay() {
		return timeOfDay.description;
	}
	
	public void waitMinutes(int minutes) {
		time += minutes;
		timeOfDay = TimeOfDay.getTimeOfDay(time);
	}
	
	public Time() {
		timeOfDay = TimeOfDay.DAWN;
		time = timeOfDay.time;
	}
	
	private final long DAY_LENGTH = 60 * 24;
	
	private long time;
	private TimeOfDay timeOfDay;
}
