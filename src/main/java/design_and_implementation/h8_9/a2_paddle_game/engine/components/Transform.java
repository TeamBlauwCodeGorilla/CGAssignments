package design_and_implementation.h8_9.a2_paddle_game.engine.components;

import design_and_implementation.h8_9.a2_paddle_game.engine.math.Vector2D;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Transform {

    public final GameObject gameObject;

    private final Vector2D localPosition;
    public final Vector2D position;
    public final Vector2D velocity;

    public final Rectangle size;

    public Transform parent;
    private final List<Transform> children;

    public Transform(GameObject gameObject) {
        this.gameObject = gameObject;
        this.localPosition = Vector2D.zero();
        this.position = Vector2D.zero();
        this.velocity = Vector2D.zero();
        this.size = new Rectangle(50, 50);
        this.children = new ArrayList<>();
    }

    public void update() {
        calcWorldPosition();
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
    }

    public void removeChild(Transform transform) {
        if (transform.parent == this) {
            children.remove(transform);
            transform.parent = null;
        }
    }

    public void applyForce(Vector2D force) {
        this.velocity.add(force);
    }

    private void calcWorldPosition() {
        if (parent == null) return;
        position.set(localPosition);
        position.add(parent.position);
    }
}
