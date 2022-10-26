package design_and_implementation.h8_9.a2_paddle_game.engine.system;

import design_and_implementation.h8_9.a2_paddle_game.engine.models.GameObject;
import design_and_implementation.h8_9.a2_paddle_game.engine.system.ResourceManager;
import design_and_implementation.h8_9.a2_paddle_game.engine.system.Sprite;
import design_and_implementation.h8_9.a2_paddle_game.engine.system.GamePanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.net.URL;
import java.util.List;

public class Engine {

    private final JFrame window;
    private final GamePanel gamePanel;
    public static boolean isRunning() {
        return GamePanel.isRunning();
    }

    public Engine() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Paddle Game - Breakout");

        gamePanel = new GamePanel(16, 12);
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

    public int getFrameRate() {
        return gamePanel.getFrameRate();
    }

    public void setFrameRate(int frameRate) {
        gamePanel.setFrameRate(frameRate);
    }

    public @NotNull Sprite createSprite(@NotNull URL url) {
        return ResourceManager.getInstance().getSprite(url);
    }
}