package design_and_implementation.h8_9.a2_paddle_game.engine;

import design_and_implementation.h8_9.a2_paddle_game.engine.models.GameObject;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.IllegalFormatWidthException;
import java.util.List;

public class Engine {

    private static Engine instance;
    private final JFrame window;
    private final GamePanel gamePanel;

    public static Engine getInstance() {
        return instance;
    }

    public Engine(@NotNull String title, int width, int height) {
        this(title, new Dimension(width, height));
    }
    public Engine(@NotNull String title, Dimension resolution) {
        this.instance = this;
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(title);

        gamePanel = new GamePanel(resolution);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public Dimension getWindowDimension() {
        return gamePanel.getDimension();
    }

    public void run() {
        gamePanel.startGameThread();
    }

    /**
     * Gets the list of all gameObjects. Modifying this list will reflect directly inside your game.
     * @return the list of all gameObjects.
     */
    public List<GameObject> getGameObjects() {
        return gamePanel.gameObjects;
    }

    /**
     * Gets a copy of the list of gameObjects. Ordered from top to bottom, with all the children listed after their parent.
     * @return an ordered list of gameObjects.
     */
    public List<GameObject> getOrderedHierarchy() {
        return gamePanel.getOrderedHierarchy();
    }

    public static int getFrameRate() {
        return GamePanel.getFrameRate();
    }

    /**
     * Changes the framerate at which your game runs.
     * @param frameRate the framerate at which your game runs. -1 for unlimited. 0 for VSync. 1+ for a framerate of your choice.
     * @throws IllegalStateException when this method is called during runtime
     */
    public void setFrameRate(int frameRate) {
        gamePanel.setFrameRate(frameRate);
    }

    public @NotNull Sprite getSprite(@NotNull URL url) {
        return ResourceManager.getInstance().getSprite(url);
    }

    public static boolean isRunning() {
        return GamePanel.isRunning();
    }
}
