package design_and_implementation.h8_9.a2_paddle_game.breakout;

import design_and_implementation.h8_9.a2_paddle_game.engine.controls.Input;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;
import design_and_implementation.h8_9.a2_paddle_game.engine.system.Console;
import design_and_implementation.h8_9.a2_paddle_game.engine.system.Engine;
import design_and_implementation.h8_9.a2_paddle_game.engine.system.Time;

public class PlayerMovement extends Component {

    private final float speed = 1f;

    @Override
    public void onUpdate() {
        double horizontal = Input.getHorizontal() * speed * Time.getDeltaTime();
        double vertical = Input.getVertical() * speed * Time.getDeltaTime();
        transform.position.add((int) horizontal, (int) vertical);
    }

    @Override
    public void onFixedUpdate() {
        Console.debug("Position: "+transform.position);
        Console.debug(String.format("FR: %s | FPS: %s", Engine.getFrameRate(), Time.getFPS()));
    }
}