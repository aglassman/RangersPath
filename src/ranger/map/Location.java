package ranger.map;

import java.util.LinkedList;
import java.util.List;

import ranger.item.Item;
import ranger.name.Name;


public class Location {
	
	public String describe() {
		return description;
	}
	
	public List<Feature> getFeatures() {
		return features;
	}
	
	/**
	 * Searches the location hierarchically for an item with this name
	 * and removes it.
	 * 
	 * All features within the location are searched until the item is found.
	 */
	public Item getItem(String name) {
		for (Feature f : features) {
			Item featureItem = f.getInventory().getItem(name);
			if (featureItem != null) {
				f.getInventory().removeItem(featureItem);
				return featureItem;
			}
		}

		return null;
	}
	
	public Feature getFeature(String name) {
		return Name.getByName(features, name);
	}
	
	public void addFeature(Feature f) {
		features.add(f);
	}
	
	public Location(String description) {
		this.description = description;
		features = new LinkedList<>();
	}
	
	private String description;
	private List<Feature> features;
}
