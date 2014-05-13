package ranger.time;

public enum TimeOfDay {
	DAWN(5, false, "The Sun is slowly rising."),
	DAY(9, false, "The Sun shines overhead."),
	DUSK(19, false, "The Sun westers."),
	NIGHT(22, true, "Night has fallen.");
	
	private static final long DAY_LENGTH = 24*60;
	
	public static TimeOfDay getTimeOfDay(long time) {
		time %= DAY_LENGTH;
		for (TimeOfDay t : values()) {
			if (time > t.time)
				return t;
		}
		return NIGHT;
	}
	
	protected final String description;
	protected final int time;
	protected boolean isDark;

	TimeOfDay(int hour, boolean isDark, String description) {
		this.description = description;
		this.isDark = isDark;
		time = hour * 60;
	}
}
