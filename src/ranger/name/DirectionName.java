package ranger.name;

public class DirectionName extends Name {
	
	@Override
	public String indefinite() {
		return baseName;
	}

	public DirectionName(String base) {
		super(base);
	}

}
