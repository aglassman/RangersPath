package ranger.ui;

import ranger.tilegame.TiledGame;

import java.awt.*;

public class Menu {
    public final int width;
    public final int height;

    public void render(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);

        int x = this.x + 20;
        int y = this.y + 20;
        int width = this.width - 40;

        bar(g, Color.RED, 100, game.getPlayer().entity.getHealth(), x, y, width, 12);
    }

    public Menu(TiledGame game, int x, int y, int width, int height) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private void bar(Graphics2D g, Color color, int max, int value, int x, int y, int width, int height) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(color);
        width = (width-2) * value / max;
        g.fillRect(x+1, y+1, width, height-2);
    }

    private TiledGame game;
    private int x;
    private int y;
}
