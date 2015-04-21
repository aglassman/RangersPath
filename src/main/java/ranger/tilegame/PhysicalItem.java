package ranger.tilegame;

import jmotion.tilegame.model.Physical;
import ranger.item.Item;

public class PhysicalItem extends Physical {

    public final Item item;

    public PhysicalItem(Item item) {
        this.item = item;
    }
}
