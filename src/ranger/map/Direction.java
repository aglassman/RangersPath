package ranger.map;

import ranger.name.DirectionName;
import ranger.name.Name;
import ranger.name.Named;

public enum Direction implements Named {
	NORTH("North") {
		public Direction getOpposite() {
			return SOUTH;
		}
	},
	SOUTH("South") {
		public Direction getOpposite() {
			return NORTH;
		}		
	},
	EAST("East") {
		public Direction getOpposite() {
			return WEST;
		}
	},
	WEST("West") {
		public Direction getOpposite() {
			return EAST;
		}
	};

	public abstract Direction getOpposite();
	
	public Name getName() {
		return name;
	}
	
	Direction(String name) {
		this.name = new DirectionName(name);
	}

	private Name name;
}
