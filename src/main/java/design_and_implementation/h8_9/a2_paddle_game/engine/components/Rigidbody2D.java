package design_and_implementation.h8_9.a2_paddle_game.engine.components;

import design_and_implementation.h8_9.a2_paddle_game.engine.CollisionDetection;
import design_and_implementation.h8_9.a2_paddle_game.engine.math.Vector2D;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;

public final class Rigidbody2D extends Component {

    public Vector2D velocity = Vector2D.zero();
    public Vector2D acceleration = Vector2D.zero();

    public float mass = 1;

    public float linearDrag = 0;
    public float angularDrag = 0;
    public float gravityScale = 1;

    public boolean freezePosX = false, freezePosY = false;
    public boolean freezeRotZ = false;

    public CollisionDetection collisionDetection = CollisionDetection.DISCRETE;

    @Override
    protected void onUpdate() {
        transform.position.add(velocity);
        velocity.subtract(linearDrag, linearDrag);
    }

    public void applyForce(Vector2D force) {
        this.acceleration.add(force);
    }
}
