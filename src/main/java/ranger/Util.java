package ranger;

import java.util.List;

public class Util {
	public static <T> T random(List<T> items) {
		return items.get((int)(Math.random() * items.size()));
	}
}
