package design_and_implementation.h8_9.a2_paddle_game.engine.components.collider;

import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;

import java.awt.geom.Ellipse2D;

public final class CircleCollider extends Component implements Collider {
    public Ellipse2D mesh;

    public CircleCollider() {
        this.mesh = new Ellipse2D.Float(0, 0, 1, 1);
    }

    @Override
    public boolean intersects(Collider other) {
        if (other instanceof BoxCollider otherBox) {
            return mesh.intersects(otherBox.mesh);
        } else if (other instanceof CircleCollider otherCircle) {
            int x1 = transform.position.x;
            int y1 = transform.position.y;
            int x2 = otherCircle.transform.position.x;
            int y2 = otherCircle.transform.position.y;
            float distSqrt = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);

            double r1 = mesh.getWidth();
            double r2 = otherCircle.mesh.getWidth();
            return distSqrt <= (r1 + r2) * (r1 + r2);
        }
        return false;
    }
}
