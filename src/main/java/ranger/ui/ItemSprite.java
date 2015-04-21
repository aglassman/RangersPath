package ranger.ui;

import jmotion.sprite.Sprite;
import jmotion.sprite.StaticSprite;
import jmotion.tilegame.model.Physical;
import ranger.tilegame.PhysicalItem;
import ranger.tilegame.TiledGame;

import java.awt.*;

public class ItemSprite implements Sprite {
    public void render(Graphics2D g) {
        sprite.render(g);
        if (RangerTileUI.graphicalDebug) {
            g.setColor(Color.red);
            g.draw(item.getBounds());
        }
    }

    public void setLocation(int x, int y) {
    }

    public int getX() {
        return item.getX();
    }

    public int getY() {
        return item.getY();
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public ItemSprite(PhysicalItem item, TiledGame game) {
        this.item = item;

        // TODO load the correct image based on the item
        sprite = game.SPRITE_LOADER.getStaticSprite("item_sword.gif");
        sprite.setLocation(item.getX() - sprite.getWidth()/2, item.getY() - sprite.getHeight() / 2);
        item.setBounds(-5, -5, 10, 10);
    }

    private PhysicalItem item;
    private StaticSprite sprite;
}
