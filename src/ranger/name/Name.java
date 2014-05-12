package ranger.name;

import java.util.List;

public class Name {
	
	public static <N extends Named> N getByName(List<N> items, String nameSearch) {
		nameSearch = nameSearch.toLowerCase();
		for (N named : items) {
			if (named.getName().matches(nameSearch))
				return named;
		}
		return null;
	}
	
	/**
	 * Use this form when refering to something directly, as in
	 * "You search the Orc camp and you find nothing."
	 */
	public String definite() {
		return "the " + baseName;
	}
	
	/**
	 * Use this form for displaying a name in a list, as in "a dagger and a rabbit carcass."
	 * 
	 * Not all forms will look indefinite, as in "an Orc camp and the Tomb of the Kings"
	 */
	public String indefinite() {
		return (isVowel(baseName.charAt(0)) ? "an " : "a ") + baseName;
	}
	
	/**
	 * The simple, unmodified form of the word, as in "dagger".
	 */
	public String basic() {
		return baseName;
	}
	
	public boolean matches(String name) {
		return baseName.toLowerCase().equals(name);
	}
	
	public Name(String base) {
		baseName = base;
	}
	
	private boolean isVowel(char c) {
		return c == 'A' || c == 'a'
				|| c == 'E' || c == 'e'
				|| c == 'I' || c == 'i'
				|| c == 'O' || c == 'o'
				|| c == 'U' || c == 'u'
				|| c == 'Y' || c == 'y';
	}
	
	protected String baseName;
}
