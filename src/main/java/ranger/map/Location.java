package ranger.map;

import java.util.LinkedList;
import java.util.List;

import ranger.entity.Entity;
import ranger.item.Item;
import ranger.name.Name;
import ranger.name.Named;

public class Location implements Named {

    public long getSeed() {
        return seed;
    }

    public void age(long time) {
        if (recentTracks != null)
            recentTracks.age(time);
    }

    public Tracks getRecentTracks() {
        return recentTracks;
    }

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

    public void getItem(Item item) {
        for (Feature f : features) {
            if (f.getInventory().contains(item))
                f.getInventory().removeItem(item);
        }
    }

	public Feature getFeature(String name) {
		return Name.getByName(features, name);
	}
	
	public void addFeature(Feature f) {
		features.add(f);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);

        // TODO these tracks should have an initial age
        recentTracks = new Tracks(e);
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
	
	public Location(Name name, TerrainType terrain, String description, int x, int y, long seed) {
		this.name = name;
		this.terrain = terrain;
		this.description = description;
		this.x = x;
		this.y = y;
        this.seed = seed;
		features = new LinkedList<>();
		entities = new LinkedList<>();
	}
	
	protected final int x;
	protected final int y;
	private Name name;
	private TerrainType terrain;
	private String description;
	private List<Feature> features;
	private List<Entity> entities;
    private Tracks recentTracks;
    private long seed;
}
