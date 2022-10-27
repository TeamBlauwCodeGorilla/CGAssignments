package design_and_implementation.h8_9.a2_paddle_game.engine.math;

public final class Math {

    private Math() {

    }

    public static boolean isPointInsideAABB(Vector2D point, Vector2D minPos, Vector2D maxPos) {
        return (
                point.x >= minPos.x &&
                        point.x <= maxPos.x &&
                        point.y >= minPos.y &&
                        point.y <= maxPos.x
        );
    }

    public static boolean isAABBInsideAABB(Vector2D minPosA, Vector2D maxPosA, Vector2D minPosB, Vector2D maxPosB) {
        return (minPosA.x <= maxPosB.x &&
                maxPosA.x >= minPosB.x &&
                minPosA.y <= maxPosB.y &&
                maxPosA.y >= minPosB.y
        );
    }
}
