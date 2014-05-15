package ranger.map;

import ranger.name.DirectionName;
import ranger.name.Name;
import ranger.name.Named;

public enum Direction implements Named {
	NORTH("North"),
	SOUTH("South"),
	EAST("East"),
	WEST("West");

	@Override
	public Name getName() {
		return name;
	}
	
	Direction(String name) {
		this.name = new DirectionName(name);
	}

	private Name name;
}
