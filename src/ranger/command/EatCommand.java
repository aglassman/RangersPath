package ranger.command;

import ranger.Game;
import ranger.item.Food;
import ranger.item.Inventory;
import ranger.item.Item;

public class EatCommand implements Command {

	public String getKeyword() {
		return "eat";
	}

	public void execute(Game game, String[] words) {
		String foodName = "";
		for (int i = 1; i<words.length; ++i) {
			foodName += words[i];
			if (i < words.length-1)
				foodName += " ";
		}
		
		Inventory invent = game.getPlayer().getInventory();
		Item item = invent.getItem(foodName);
		if (item == null) {
			System.out.println("You don't have any of that.");
		} else if (item instanceof Food) {
			Food food = (Food)item;
			invent.reduceServing(food);
			game.getPlayer().addFoodValue(food.getFoodValue());
		} else {
			System.out.println("You can't eat your " + foodName);
		}
	}

}
