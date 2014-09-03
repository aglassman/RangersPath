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

    public static Direction getDirection(int x, int y) {
        if (x > 0)
            return EAST;
        else if (x < 0)
            return WEST;
        else if (y < 0)
            return NORTH;
        else if (y > 0)
            return SOUTH;

        return null;
    }

	public abstract Direction getOpposite();
	
	public Name getName() {
		return name;
	}
	
	Direction(String name) {
		this.name = new DirectionName(name);
	}

	private Name name;
}
