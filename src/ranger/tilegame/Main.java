package ranger.tilegame;

import game.engine.io.UserInterface;
import game.engine.io.console.ConsoleInterface;
import ranger.Game;
import ranger.tilegame.entity.task.PlayerControlTask;
import ranger.ui.RangerTileUI;

import javax.swing.*;

public class Main {
    public static void main(String... args) {
    	UserInterface consoleUI = new ConsoleInterface();
        Game game = new Game(consoleUI);
        game.start();
        
        TiledGame tiledGame = new TiledGame(game);

        RangerTileUI ui = new RangerTileUI(tiledGame, new PlayerControlTask(game.ui,tiledGame.getPlayer()));

        JFrame window = new JFrame();
        window.setSize(1000, 1000);
        window.setLocationRelativeTo(null);
        window.getContentPane().add(ui);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ui.startAnimating();
        
        
    }
}
