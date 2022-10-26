package design_and_implementation.h8_9.a2_paddle_game.engine.components;

import design_and_implementation.h8_9.a2_paddle_game.engine.math.Vector2D;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Transform {

    //TODO: Add pivot/anchor points

    public final GameObject gameObject;

    public final Vector2D position;
    public final Vector2D velocity;

    public final Rectangle size;

    public Transform parent;
    private final List<Transform> children;

    public Transform(GameObject gameObject) {
        this.gameObject = gameObject;
        this.position = Vector2D.zero();
        this.velocity = Vector2D.zero();
        this.size = new Rectangle(50, 50);
        this.children = new ArrayList<>();
    }

    public void update() {

    }

    public Transform[] getChildren() {
        return children.toArray(Transform[]::new);
    }

    public void getAllChildren(List<Transform> out) {
        if (out == null) {
            out = new ArrayList<>();
        }
        out.add(this);

        Transform[] children = getChildren();
        for (Transform child : children) {
            child.getAllChildren(out);
        }
    }

    public void addChild(Transform transform) {
        if (transform.parent != null) {
            transform.parent.removeChild(transform);
        }
        children.add(transform);
        transform.parent = this;

        //Translate transform's position to its local position
        transform.position.subtract(this.position);
    }

    public void removeChild(Transform transform) {
        if (transform.parent != this) return;
        children.remove(transform);
        Vector2D worldPos = getWorldPosition();
        transform.parent = null;

        //Translate transform's position to its world position
        transform.position.set(worldPos);
    }

    public void applyForce(Vector2D force) {
        this.velocity.add(force);
    }

    public Vector2D getWorldPosition() {
        if (parent == null) {
            return position.clone();
        }
        return parent.getWorldPosition().add(position);
    }
}
