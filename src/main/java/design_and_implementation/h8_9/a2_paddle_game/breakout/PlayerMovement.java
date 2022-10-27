package design_and_implementation.h8_9.a2_paddle_game.breakout;

import design_and_implementation.h8_9.a2_paddle_game.engine.Console;
import design_and_implementation.h8_9.a2_paddle_game.engine.Engine;
import design_and_implementation.h8_9.a2_paddle_game.engine.Input;
import design_and_implementation.h8_9.a2_paddle_game.engine.Time;
import design_and_implementation.h8_9.a2_paddle_game.engine.components.Transform;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;

public class PlayerMovement extends Component {

    private final float speed = 1f;
    public Transform optionalChild;

    @Override
    public void onUpdate() {
        double horizontal = Input.getAxis("Horizontal") * speed * Time.getDeltaTime();
        double vertical = Input.getAxis("Vertical") * speed * Time.getDeltaTime();
        transform.position.add((int) horizontal, (int) vertical);

        if (optionalChild != null && Input.getButtonDown("Jump")) {
            if (transform.equals(optionalChild.getParent())) {
                transform.removeChild(optionalChild);
            } else {
                transform.addChild(optionalChild);
            }
        }
    }

    @Override
    public void onFixedUpdate() {
        Console.debug("Position: "+transform.position);
        Console.debug(String.format("FR: %s | FPS: %s", Engine.getFrameRate(), Time.getFPS()));
    }
}