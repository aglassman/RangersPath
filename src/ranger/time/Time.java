package ranger.time;

public class Time {
	
	public void showTime() {
		long ticksToday = time % DAY_LENGTH;
		System.out.print("Day " + (time/DAY_LENGTH + 1) + " of your wanderings. ");
		
		if (ticksToday <= SUN_UP)
			System.out.println("The sun has not yet risen.");
		else if (ticksToday <= SUN_DOWN)
			System.out.println("The sun shines overhead.");
		else
			System.out.println("The sun has set");
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
