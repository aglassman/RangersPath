package ranger.name;

public class DefiniteName extends Name {
	
	@Override
	public String indefinite() {
		return definite();
	}

	public DefiniteName(String base) {
		super(base);
	}

}
