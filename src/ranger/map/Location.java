package ranger.map;

import java.util.LinkedList;
import java.util.List;

import ranger.entity.Entity;
import ranger.item.Item;
import ranger.name.Name;
import ranger.name.Named;

public class Location implements Named {
	
	public String describe() {
		return description;
	}
	
	public List<Feature> getFeatures() {
		return features;
	}
	
	public List<Entity> getEntities() {
		return entities;
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
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public Entity getEntity(String name) {
		return Name.getByName(entities, name);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public Name getName() {
		return name;
	}
	
	public TerrainType getTerrainType() {
		return terrain;
	}
	
	public Location(Name name, TerrainType terrain, String description) {
		this.name = name;
		this.terrain = terrain;
		this.description = description;
		features = new LinkedList<>();
		entities = new LinkedList<>();
	}
	
	private Name name;
	private TerrainType terrain;
	private String description;
	private List<Feature> features;
	private List<Entity> entities;
}
