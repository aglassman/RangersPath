package ranger.ui;

import jmotion.sprite.Sprite;
import ranger.tilegame.Arrow;

import java.awt.*;

public class ArrowSprite implements Sprite {

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval(arrow.getX()-3, arrow.getY()-3, 6, 6);
    }

    public void setLocation(int x, int y) {
    }

    public int getX() {
        return 0;
    }

    public int getY() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public ArrowSprite(Arrow arrow) {
        this.arrow = arrow;
    }

    private Arrow arrow;
}
