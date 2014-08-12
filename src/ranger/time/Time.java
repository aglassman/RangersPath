package ranger.time;

import ranger.Game;

public class Time {
	
	public boolean isDark() {
		return timeOfDay.isDark;
	}
	
	public void showDayTime() {
		System.out.print("Day " + (time/DAY_LENGTH + 1) + " of your wanderings. ");
		
		System.out.println(describeTimeOfDay());
	}
	
	public long getCurrentTime() {
		return time;
	}
	
	public String describeTimeOfDay() {
		return timeOfDay.description;
	}
	
	public long timeTillNextEvent() {
		return head.event.getTime() - time;
	}
	
	public boolean hasNextEvent() {
		return head != null;
	}
	
	public GameEvent getNextEvent() {
		return head.event;
	}
	
	public void waitMinutes(long minutes) {
		time += minutes;
		timeOfDay = TimeOfDay.getTimeOfDay(time);
	}
	
	public void doNextEvent(Game game) {
		time = head.event.getTime();
		timeOfDay = TimeOfDay.getTimeOfDay(time);
		head.event.execute(game);
		head = head.next;
	}
	
	public void scheduleEvent(GameEvent e) {
		if (head == null) {
			head = new EventNode(e);
		} else {
			EventNode current = head;
			while (current.next != null && current.next.event.getTime() < e.getTime())
				current = current.next;
			current.next = new EventNode(e, current.next);
		}
	}
	
	public Time() {
		timeOfDay = TimeOfDay.DAWN;
		time = timeOfDay.time;
	}
	
	private class EventNode {
		GameEvent event;
		EventNode next;

		EventNode(GameEvent event) {
			this.event = event;
		}
		EventNode(GameEvent event, EventNode next) {
			this.event = event;
			this.next = next;
		}
	}
	
	private final long DAY_LENGTH = 60 * 24;
	
	private EventNode head;
	private long time;
	private TimeOfDay timeOfDay;
}
