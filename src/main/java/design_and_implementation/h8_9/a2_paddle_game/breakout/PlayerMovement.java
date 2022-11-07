package design_and_implementation.h8_9.a2_paddle_game.breakout;

import design_and_implementation.h8_9.a2_paddle_game.engine.*;
import design_and_implementation.h8_9.a2_paddle_game.engine.components.RectTransform;
import design_and_implementation.h8_9.a2_paddle_game.engine.math.Vector2D;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;

import java.awt.*;

public class PlayerMovement extends Component {

    private final float speed = 1f;
    public RectTransform ball;

    @Override
    public void onEnable() {
        Dimension dimension = Engine.getInstance().getWindowDimension();
        transform.position.set((int) (dimension.getWidth()/2), (int) (dimension.getHeight() * 0.9));
    }

    @Override
    public void onUpdate() {
        double horizontal = Input.getAxis("Horizontal") * speed * Time.getDeltaTime();
        double vertical = Input.getAxis("Vertical") * speed * Time.getDeltaTime();
        transform.position.add((int) horizontal, (int) vertical);

        if (ball != null && Input.getButtonDown("Jump")) {
            Console.debug("Detected Jump");
            if (transform.equals(ball.getParent())) {
                transform.removeChild(ball);
            } else {
                transform.addChild(ball);
                ball.position.set(Vector2D.zero());
            }
        }
    }

    @Override
    public void onFixedUpdate() {
        Console.debug("Position: "+transform.position);
        Console.debug(String.format("FR: %s | FPS: %s", Engine.getFrameRate(), Time.getFPS()));
    }
}