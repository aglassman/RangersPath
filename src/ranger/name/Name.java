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
	
	public static String commaList(List<? extends Named> items) {
		String listString = items.get(0).getName().indefinite();
		
		// only two items, no oxford comma
		if (items.size() == 2)
			listString += " and " + items.get(1).getName().indefinite();
		else if (items.size() > 2) {
			for (int i = 1; i<items.size()-1; ++i)
				listString += ", " + items.get(i).getName().indefinite();
			listString += ", and " + items.get(items.size()-1).getName().indefinite();
		}
		
		return listString;
	}
	
	public static String pluralize(String word) {
		char lastLetter = word.charAt(word.length()-1);
		if (lastLetter == 's')
			return word + "es";
		if (lastLetter == 'y')
			return word.substring(0, word.length()-1) + "ies";
		return word + "s";
	}
	
	public static String indefiniteSingular(String word) {
		return (isVowel(word.charAt(0)) ? "an " : "a ") + word;
	}

	public static boolean isVowel(char c) {
		return c == 'A' || c == 'a'
				|| c == 'E' || c == 'e'
				|| c == 'I' || c == 'i'
				|| c == 'O' || c == 'o'
				|| c == 'U' || c == 'u'
				|| c == 'Y' || c == 'y';
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
		return indefiniteSingular(baseName);
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

	protected String baseName;
}
