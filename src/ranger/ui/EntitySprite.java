package ranger.ui;

import jmotion.animation.FrameSet;
import jmotion.sprite.Sprite;
import ranger.map.Direction;
import ranger.tilegame.PhysicalEntity;
import ranger.tilegame.TiledGame;

import java.awt.*;

public class EntitySprite implements Sprite {
    public void render(Graphics2D g) {
        int x = entity.getX() - frames.getWidth()/2;
        int y = entity.getY() - frames.getHeight() + 5;

        boolean walking = entity.xMoved != 0 || entity.yMoved != 0;
        setFrame(entity.facing, walking);
        frames.advanceFrame();

        g.drawImage(frames.currentFrame(), x, y, null);
    }

    public void setLocation(int x, int y) {
    }

    public int getX() {
        return entity.getX();
    }

    public int getY() {
        return entity.getY();
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public EntitySprite(PhysicalEntity entity, TiledGame game) {
        this.entity = entity;

        String name;
        if (entity == game.getPlayer())
            name = "archer";
        else
            name = "soldier";

        frames = game.SPRITE_LOADER.getFrames(name);
        frames.setSequence(0);
    }

    private void setFrame(Direction dir, boolean walking) {
        int row = 0;
        if (dir == Direction.NORTH)
            row = 0;
        if (dir == Direction.EAST)
            row = 4;
        if (dir == Direction.SOUTH)
            row = 8;
        if (dir == Direction.WEST)
            row = 12;
        if (walking)
            ++row;
        frames.setSequence(row);
    }

    private PhysicalEntity entity;
    private FrameSet frames;
}
