package ranger.map;

import ranger.name.DirectionName;
import ranger.name.Name;
import ranger.name.Named;

import java.awt.*;

public enum Direction implements Named {
    NORTH("North", 0, -1) {
		public Direction getOpposite() {
			return SOUTH;
		}
	},
	SOUTH("South", 0, 1) {
		public Direction getOpposite() {
			return NORTH;
		}		
	},
	EAST("East", 1, 0) {
		public Direction getOpposite() {
			return WEST;
		}
	},
	WEST("West", -1, 0) {
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

    public Point project(int magnitude) {
        return new Point(vectorX * magnitude, vectorY * magnitude);
    }
	
	public Name getName() {
		return name;
	}
	
	Direction(String name, int vectorX, int vectorY) {
		this.name = new DirectionName(name);
        this.vectorX = vectorX;
        this.vectorY = vectorY;
	}

	private Name name;
    private int vectorX;
    private int vectorY;
}
