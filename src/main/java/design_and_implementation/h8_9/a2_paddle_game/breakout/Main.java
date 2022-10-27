package design_and_implementation.h8_9.a2_paddle_game.breakout;

import design_and_implementation.h8_9.a2_paddle_game.engine.Engine;
import design_and_implementation.h8_9.a2_paddle_game.engine.components.SpriteRenderer;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.GameObject;
import design_and_implementation.h8_9.a2_paddle_game.engine.Screen;

import java.awt.*;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        Dimension screenResolution = Screen.getMonitorResolution();
        double width = screenResolution.getWidth()*0.75; // 75% of the screen's width.
        double height = screenResolution.getHeight()*0.75; // 75% of the screen's height.
        Engine engine = new Engine("Paddle Game - Breakout", (int) width, (int) height); //Sets the title, width and height of the window.
        engine.setFrameRate(0);//Sets the frame rate to the screen's refresh rate. (VSync) (-1 would be unlimited)

        //Create a player, sets its world position to (0,0) and grows its size by 50 pixels in width & height.
        GameObject playerObject = new GameObject("Player");
        playerObject.transform.position.set(0, 0);
        playerObject.transform.size.grow(50, 50);

        //Assign a sprite renderer to the player, so we can see the player on the screen.
        SpriteRenderer spriteRenderer = playerObject.addComponent(SpriteRenderer.class);
        URL url = Main.class.getResource("/assets/player.png"); //Get the url to the correct image from the 'resources' folder.
        spriteRenderer.sprite = engine.createSprite(url); //Create and assign a sprite using the url to the sprite renderer. (No sprite/image would show a blank square)

        //Assign a custom component to the player (A class that extends Component.java)
        PlayerMovement playerMovement = playerObject.addComponent(PlayerMovement.class);
        //Add the player to the world.
        engine.getGameObjects().add(playerObject);

        //Create a npc and sets its world position to (150,150)
        GameObject npc = new GameObject("NPC");
        npc.transform.position.set(150, 150);

        //Assign a sprite renderer to the npc, so we can see the npc on the screen.
        spriteRenderer = npc.addComponent(SpriteRenderer.class);
        url = Main.class.getResource("/assets/npc.png"); //Get the url to the correct image from the 'resources' folder.
        spriteRenderer.sprite = engine.createSprite(url); //Create and assign a sprite using the url to the sprite renderer. (No sprite/image would show a blank square)
        spriteRenderer.orderInLayer = 1;

        //Add the npc to the world.
        engine.getGameObjects().add(npc);

        //Adds the npc as a child of the player. (So the npc moves along with the player's movement)
        playerObject.transform.addChild(npc.transform);
        playerMovement.optionalChild = npc.transform;

        //Start running the engine. Aka starts the game.
        engine.run();
    }
}
