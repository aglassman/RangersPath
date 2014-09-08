package ranger.tilegame;

public class Arrow extends PhysicalEntity {

    @Override
    public void act(TiledLocation location) {
        location.movePhysical(this, dx, dy);

        if (!location.contains(x, y))
            location.removeLater(this);
    }

    public Arrow(int x, int y, int dx, int dy) {
        super(null); // There is no Entity for an Arrow
        setLocation(x, y);
        this.dx = dx;
        this.dy = dy;
    }

    private int dx;
    private int dy;
}
