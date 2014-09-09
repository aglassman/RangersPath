package ranger.ui;

import jmotion.sprite.Sprite;
import ranger.tilegame.entity.Arrow;

import java.awt.*;

public class ArrowSprite implements Sprite {

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval(arrow.getX()-3, arrow.getY()-3, 6, 6);

        if (RangerTileUI.graphicalDebug) {
            g.setColor(Color.red);
            g.draw(arrow.getBounds());
        }
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
        arrow.setBounds(-3, -3, 6, 6);
    }

    private Arrow arrow;
}
