package ranger.command;

import ranger.Game;
import ranger.item.Food;
import ranger.item.Inventory;
import ranger.item.Item;

public class EatCommand implements Command {

	public String getKeyword() {
		return "eat";
	}

	public void execute(Game game, String[] words, String directObject) {		
		Inventory invent = game.getPlayer().getInventory();
		Item item = invent.getItem(directObject);
		if (item == null) {
			System.out.println("You don't have any of that.");
		} else if (item instanceof Food) {
			Food food = (Food)item;
			invent.expend(food);
			game.getPlayer().addFoodValue(food.getFoodValue());
		} else {
			System.out.println("You can't eat your " + directObject);
		}
	}

}
