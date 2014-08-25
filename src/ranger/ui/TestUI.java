package ranger.ui;

import ranger.Game;
import ranger.entity.Bear;
import ranger.entity.Entity;
import ranger.map.Location;
import ranger.map.Region;
import ranger.map.TerrainType;
import ranger.map.Tracks;

import javax.swing.*;
import java.awt.*;

public class TestUI extends JFrame {

    public void update() {
        map.repaint();
    }

    public TestUI(final Game game) {
        map = new JPanel() {

            final int width = 15;

            @Override
            public void paint(Graphics g) {
                Region region = game.getRegion();
                for (int row = 0; row<region.HEIGHT; ++row) {
                    for (int col = 0; col<region.WIDTH; ++col) {
                        Location location = region.getLocation(row, col);
                        g.setColor(getColor(location.getTerrainType()));
                        g.fillRect(col * width, row * width, width, width);

                        if (!location.getEntities().isEmpty() || location == game.getPlayerLocation()) {
                            if (location == game.getPlayerLocation())
                                g.setColor(Color.red);
                            else
                               g.setColor(getColor(location.getEntities().get(0)));

                            g.fillOval(col * width + width / 4, row * width + width / 4, width / 2, width / 2);
                        } else {
                            Tracks tracks = location.getRecentTracks();
                            if (tracks != null && tracks.getAge() < 120) {
                                int alpha = Math.max(0, 255  - ((int)(tracks.getAge()) / 30) * 64);
                                Color color = getColor(tracks.getEntity());
                                Color faded = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
                                g.setColor(faded);
                                g.drawOval(col * width + width / 4, row * width + width / 4, width / 2, width / 2);
                            }
                        }
                    }
                }
            }
        };

        add(map);

        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Color getColor(Entity entity) {
        if (entity instanceof Bear)
            return new Color(106, 66, 15);

        return Color.BLACK;
    }

    private Color getColor(TerrainType terrain) {
        switch(terrain) {
            case FOREST: return new Color(10, 42, 10);
            case HILLSIDE: return Color.LIGHT_GRAY;
            case PLAINS: return Color.GREEN;
            default: return Color.BLACK;
        }
    }

    private JPanel map;
}
