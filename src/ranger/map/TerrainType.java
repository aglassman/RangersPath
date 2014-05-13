package ranger.map;

public enum TerrainType {
	FOREST(0.5),
	HILLSIDE(0.0),
	PLAINS(0.0);
	
	public final double stealthMod;
	
	TerrainType(double stealthMod) {
		this.stealthMod = stealthMod;
	}
}
