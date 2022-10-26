package design_and_implementation.h8_9.a2_paddle_game.engine.math;

import org.jetbrains.annotations.NotNull;

public class Vector2D implements Cloneable {

    public static Vector2D zero() {
        return new Vector2D(0, 0);
    }

    public static Vector2D one() {
        return new Vector2D(1, 1);
    }

    public int x, y;

    public Vector2D() {
        this(0, 0);
    }

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected Vector2D(@NotNull Vector2D other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector2D set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2D set(Vector2D vec) {
        return set(vec.x, vec.y);
    }

    public Vector2D add(int x, int y) {
        int newX = this.x + x;
        int newY = this.y + y;
        this.set(newX, newY);
        return this;
    }

    public Vector2D add(@NotNull Vector2D vec) {
        return add(vec.x, vec.y);
    }

    public Vector2D subtract(int x, int y) {
        int newX = this.x - x;
        int newY = this.y - y;
        this.set(newX, newY);
        return this;
    }

    public Vector2D subtract(@NotNull Vector2D vec) {
        return subtract(vec.x, vec.y);
    }

    public Vector2D multiply(int x, int y) {
        int newX = this.x * x;
        int newY = this.y * y;
        this.set(newX, newY);
        return this;
    }

    public Vector2D multiply(@NotNull Vector2D vec) {
        return multiply(vec.x, vec.y);
    }

    public Vector2D divide(int x, int y) {
        int newX = this.x / x;
        int newY = this.y / y;
        this.set(newX, newY);
        return this;
    }

    public Vector2D divide(@NotNull Vector2D vec) {
        return divide(vec.x, vec.y);
    }

    public Vector2D clone() {
        return new Vector2D(this);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
