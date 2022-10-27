package design_and_implementation.h8_9.a2_paddle_game.engine;

import design_and_implementation.h8_9.a2_paddle_game.engine.models.GameObject;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class Engine {

    private final JFrame window;
    private final GamePanel gamePanel;

    public Engine(@NotNull String title, Dimension resolution) {
        this(title, resolution.width, resolution.height);
    }
    public Engine(@NotNull String title, int width, int height) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(title);

        gamePanel = new GamePanel(width, height);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void run() {
        gamePanel.startGameThread();
    }

    public List<GameObject> getGameObjects() {
        return gamePanel.gameObjects;
    }

    public static int getFrameRate() {
        return GamePanel.getFrameRate();
    }

    public void setFrameRate(int frameRate) {
        gamePanel.setFrameRate(frameRate);
    }

    public @NotNull Sprite createSprite(@NotNull URL url) {
        return ResourceManager.getInstance().getSprite(url);
    }

    public static boolean isRunning() {
        return GamePanel.isRunning();
    }
}
