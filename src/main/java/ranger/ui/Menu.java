package ranger.ui;

import ranger.entity.Entity;
import ranger.item.Item;
import ranger.tilegame.TiledGame;
import ranger.tilegame.entity.PhysicalEntity;

import java.awt.*;
import java.util.LinkedList;

public class Menu {
    public final int width;
    public final int height;

    public void render(Graphics2D g) {
        Entity player = game.getPlayer().entity;
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);

        int x = this.x + 20;
        int y = this.y + 20;
        int width = this.width - 40;

        bar(g, Color.RED, 100, player.getHealth(), x, y, width, 12);
        y += 12;

        for (ItemIcon icon : icons) {
            y += 20;

            if (icon.item == player.getEquip() || icon.item == player.getAmmo()) {
                g.setColor(Color.BLUE);
                g.fillRect(x-4, y-4, 48, 48);
            }
            icon.render(g, x, y);
            y += 40;
        }
    }

    public Menu(TiledGame game, int x, int y, int width, int height) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        icons = new LinkedList<>();
        for (Item item : game.getPlayer().entity.getInventory().getItems())
            icons.add(new ItemIcon(game, item));
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

    private LinkedList<ItemIcon> icons;
}
