package design_and_implementation.h8_9.a2_paddle_game.engine.system;

import java.awt.*;

public class Screen {
    public static int getMonitorRefreshRate() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        int lowest = Integer.MAX_VALUE;

        for (GraphicsDevice g : gs) {
            DisplayMode dm = g.getDisplayMode();

            int refreshRate = dm.getRefreshRate();
            if (refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
                System.out.println("Unknown rate");
            }

            if (refreshRate < lowest) {
                lowest = refreshRate;
            }
        }

        return lowest;
    }

    public static Dimension getMonitorResolution() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        int minWidth = Integer.MAX_VALUE;
        int minHeight = Integer.MAX_VALUE;

        for (GraphicsDevice g : gs) {
            DisplayMode dm = g.getDisplayMode();

            int width = dm.getWidth();
            if (width < minWidth) {
                minWidth = width;
            }

            int height = dm.getHeight();
            if (height < minHeight) {
                minHeight = height;
            }
        }

        return new Dimension(minWidth, minHeight);
    }
}
