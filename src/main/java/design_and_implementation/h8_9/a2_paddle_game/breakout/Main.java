package design_and_implementation.h8_9.a2_paddle_game.breakout;

import design_and_implementation.h8_9.a2_paddle_game.engine.system.Engine;
import design_and_implementation.h8_9.a2_paddle_game.engine.components.SpriteRenderer;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.GameObject;
import design_and_implementation.h8_9.a2_paddle_game.engine.system.Screen;

import java.awt.*;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        Dimension screenResolution = Screen.getMonitorResolution();
        Engine engine = new Engine((int) (screenResolution.getWidth()*0.75), (int) (screenResolution.getHeight()*0.75));
        engine.setFrameRate(0);

        //Player
        GameObject playerObject = new GameObject("Player");
        playerObject.transform.position.set(0, 0);

        SpriteRenderer spriteRenderer = playerObject.addComponent(SpriteRenderer.class);
        URL url = Main.class.getResource("/assets/player.png");
        spriteRenderer.sprite = engine.createSprite(url);
        engine.getGameObjects().add(playerObject);
        playerObject.addComponent(PlayerMovement.class);

        //Enemy
        GameObject enemy = new GameObject("Enemy");
        enemy.transform.position.set(150, 150);

        spriteRenderer = enemy.addComponent(SpriteRenderer.class);
        url = Main.class.getResource("/assets/enemy.png");
        spriteRenderer.sprite = engine.createSprite(url);
        spriteRenderer.orderInLayer = 1;
        engine.getGameObjects().add(enemy);

        playerObject.transform.addChild(enemy.transform);

        engine.run();
    }
}
