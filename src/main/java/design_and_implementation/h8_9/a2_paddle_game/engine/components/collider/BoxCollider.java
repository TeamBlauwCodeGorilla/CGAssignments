package design_and_implementation.h8_9.a2_paddle_game.engine.components.collider;

import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;
import design_and_implementation.h8_9.a2_paddle_game.engine.math.Vector2D;

import java.awt.geom.Rectangle2D;

public final class BoxCollider extends Component implements Collider {

    public final Rectangle2D mesh;

    public BoxCollider() {
        this.mesh = new Rectangle2D.Float(0, 0, 1, 1);
    }

    public Vector2D getMinPos() {
        return new Vector2D((int) mesh.getMinX(), (int) mesh.getMinY()).add(gameObject.transform.position);
    }

    public Vector2D getMaxPos() {
        return new Vector2D((int) mesh.getMaxX(), (int) mesh.getMaxY()).add(gameObject.transform.position);
    }

    @Override
    public boolean intersects(Collider other) {
        if (other instanceof BoxCollider otherBox) {
            return otherBox.mesh.intersects(mesh);
        } else if (other instanceof CircleCollider otherEllipse) {
            return otherEllipse.mesh.intersects(mesh);
        }
        return false;
    }
}
