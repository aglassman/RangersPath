package ranger.ui;

import ranger.item.Item;
import ranger.item.weapon.Ammo;
import ranger.item.weapon.Weapon;
import ranger.tilegame.TiledGame;

import java.awt.*;

public class ItemIcon {

    private static final Font ICON_FONT = new Font("Consolas", Font.BOLD, 16);
    private static int FONT_HEIGHT = -1;

    public final Item item;

    public void render(Graphics2D g, int x, int y) {
        if (FONT_HEIGHT == -1) {
            FontMetrics metrics = g.getFontMetrics(ICON_FONT);
            FONT_HEIGHT = metrics.getHeight();
        }

        g.drawImage(image, x, y, null);

        if (item.isStackable()) {
            g.setFont(ICON_FONT);
            g.setColor(Color.black);
            g.drawString(Integer.toString(item.getQuantity()), x+2, y+2+FONT_HEIGHT);
        }
    }

    public ItemIcon(TiledGame game, Item item) {
        this.item = item;
        if (item instanceof Weapon) {
            image = game.SPRITE_LOADER.readImage(
                    ((Weapon)item).isRanged() ? "icon_bow.gif" : "icon_sword.gif");
        } else if (item instanceof Ammo) {
            image = game.SPRITE_LOADER.readImage("icon_arrows.gif");
        } else {
        }
    }

    private Image image;
}
