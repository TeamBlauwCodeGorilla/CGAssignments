package design_and_implementation.h8_9.a2_paddle_game.engine.components.collider;

import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;
import design_and_implementation.h8_9.a2_paddle_game.engine.math.Vector2D;

public final class CircleCollider extends Component implements Collider {

    public Vector2D position;
    public float radius;

    public CircleCollider() {
        this.position = Vector2D.zero();
        this.radius = 1;
    }

    public float getRadius() {
        return this.radius;
    }

    public float getDiameter() {
        return this.radius * 2;
    }

    @Override
    public boolean intersects(Collider other) {
        return false;
    }
}
