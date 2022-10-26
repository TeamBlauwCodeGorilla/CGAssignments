package design_and_implementation.h8_9.a2_paddle_game.engine.math;

public class BoundingBox {

    public final Vector2D minPos, maxPos;

    public BoundingBox(Vector2D pos1, Vector2D pos2) {
        int minX = java.lang.Math.min(pos1.x, pos2.x);
        int minY = java.lang.Math.min(pos1.y, pos2.y);
        minPos = new Vector2D(minX, minY);

        int maxX = java.lang.Math.max(pos1.x, pos2.x);
        int maxY = java.lang.Math.max(pos1.y, pos2.y);
        maxPos = new Vector2D(maxX, maxY);
    }

    /**
     * Grows the bounds by a factor
     * @param growFactor The amount to grow
     */
    public void expand(Vector2D growFactor) {
        minPos.subtract(growFactor);
        maxPos.add(growFactor);
    }

    public boolean isPointInsideAABB(Vector2D point) {
        return (point.x >= this.minPos.x &&
                point.x <= this.maxPos.x &&
                point.y >= this.minPos.y &&
                point.y <= this.maxPos.y
        );
    }
}
