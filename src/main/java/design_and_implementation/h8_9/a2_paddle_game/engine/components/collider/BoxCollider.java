package design_and_implementation.h8_9.a2_paddle_game.engine.components.collider;

import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;
import design_and_implementation.h8_9.a2_paddle_game.engine.math.BoundingBox;
import design_and_implementation.h8_9.a2_paddle_game.engine.math.Math;
import design_and_implementation.h8_9.a2_paddle_game.engine.math.Vector2D;

public final class BoxCollider extends Component implements Collider {

    public final BoundingBox bounds;

    public BoxCollider() {
        this.bounds = new BoundingBox(Vector2D.one(), Vector2D.one());
    }

    public Vector2D getMinPos() {
        return bounds.minPos.clone().add(gameObject.transform.position);
    }

    public Vector2D getMaxPos() {
        return bounds.maxPos.clone().add(gameObject.transform.position);
    }

    @Override
    public boolean intersects(Collider other) {
        if (other instanceof BoxCollider otherBox) {
            Vector2D myMinPos = getMinPos();
            Vector2D myMaxPos = getMaxPos();
            Vector2D otherMinPos = otherBox.getMinPos();
            Vector2D otherMaxPos = otherBox.getMaxPos();

            return Math.isAABBInsideAABB(bounds, otherBox.bounds);

        } else if (other instanceof CircleCollider) {

        }
        return false;
    }
}
