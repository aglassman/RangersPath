package ranger.command;

import ranger.Game;
import ranger.Output;
import ranger.map.Tracks;
import ranger.name.Name;

public class TrackCommand implements Command {
    public String getKeyword() {
        return "track";
    }

    public void execute(Game game, String[] words, String directObject) {
        Tracks tracks = game.getPlayerLocation().getRecentTracks();
        if (tracks == null) {
            System.out.println("You do not see any tracks.");
        } else {
            Output.println("You see the tracks of %s.", tracks.getEntity(), Name.NameType.INDEFINITE);
        }
    }

    public String getHelpText() {
        return "Look for tracks at your location.";
    }
}
