package ranger.time;

public class Time {
	
	public void showDayTime() {
		System.out.print("Day " + (time/DAY_LENGTH + 1) + " of your wanderings. ");
		
		System.out.println(describeTimeOfDay());
	}
	
	public String describeTimeOfDay() {
		long ticksToday = time % DAY_LENGTH;
		
		if (ticksToday <= SUN_UP)
			return "The sun has not yet risen.";
		else if (ticksToday <= SUN_DOWN)
			return "The sun shines overhead.";
		else
			return "The sun has set";
	}
	
	public void waitMinutes(int minutes) {
		time += minutes;
	}
	
	public Time() {
		DAY_LENGTH = 60 * 24;
		SUN_UP = 6 * 60;
		SUN_DOWN = 20 * 60;
		
		time = SUN_UP;
	}
	
	private final long DAY_LENGTH;
	private final int SUN_UP;
	private final int SUN_DOWN;
	
	private long time;
}
