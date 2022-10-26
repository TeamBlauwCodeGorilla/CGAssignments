package design_and_implementation.h8_9.a2_paddle_game.engine.math;

public final class Math {

    private Math() {

    }

    public static boolean isPointInsideAABB(Vector2D point, BoundingBox bounds) {
        return (
                point.x >= bounds.minPos.x &&
                        point.x <= bounds.maxPos.x &&
                        point.y >= bounds.minPos.y &&
                        point.y <= bounds.maxPos.x
        );
    }

    public static boolean isAABBInsideAABB(BoundingBox boundsA, BoundingBox boundsB) {
        Vector2D minPosA = boundsA.minPos;
        Vector2D maxPosA = boundsA.maxPos;
        Vector2D minPosB = boundsB.minPos;
        Vector2D maxPosB = boundsB.maxPos;

        return (minPosA.x <= maxPosB.x &&
                maxPosA.x >= minPosB.x &&
                minPosA.y <= maxPosB.y &&
                maxPosA.y >= minPosB.y
        );
    }
}
