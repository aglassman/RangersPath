package ranger.ui;

import jmotion.sprite.Sprite;
import jmotion.sprite.StaticSprite;
import jmotion.tilegame.model.Physical;
import ranger.tilegame.TiledGame;

import java.awt.*;

public class ObjectSprite implements Sprite {
    public void render(Graphics2D g) {
        sprite.render(g);

        if (RangerTileUI.graphicalDebug) {
            g.setColor(Color.red);
            g.draw(object.getBounds());
        }
    }

    public void setLocation(int x, int y) {
    }

    public int getX() {
        return object.getX();
    }

    public int getY() {
        return object.getY();
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public ObjectSprite(Physical object, TiledGame game) {
        this.object = object;

        // TODO load the correct image based on the object
        sprite = game.SPRITE_LOADER.getStaticSprite("campfire.gif");
        sprite.setLocation(object.getX() - sprite.getWidth()/2, object.getY() - sprite.getHeight() / 2);
        object.setBounds(-10, -10, 20, 20);
    }

    private Physical object;
    private StaticSprite sprite;
}
