package design_and_implementation.h8_9.a2_paddle_game.engine.components;

import design_and_implementation.h8_9.a2_paddle_game.engine.math.Vector2D;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class RectTransform {

    //TODO: Add pivot/anchor points

    public final GameObject gameObject;

    public final Vector2D position;

    public final Vector2D pivot = Vector2D.half();
    public final Vector2D anchor = Vector2D.zero();
    public final Rectangle size;

    private RectTransform parent;
    private final List<RectTransform> children;

    public RectTransform(GameObject gameObject) {
        this.gameObject = gameObject;
        this.position = Vector2D.zero();
        this.size = new Rectangle(50, 50);
        this.children = new ArrayList<>();
    }

    public void update() {

    }

    public RectTransform[] getChildren() {
        return children.toArray(RectTransform[]::new);
    }

    public void getAllChildren(List<RectTransform> out) {
        if (out == null) {
            out = new ArrayList<>();
        }
        out.add(this);

        RectTransform[] children = getChildren();
        for (RectTransform child : children) {
            child.getAllChildren(out);
        }
    }

    public RectTransform getParent() {
        return parent;
    }

    public void addChild(RectTransform transform) {
        if (transform.parent != null) {
            transform.parent.removeChild(transform);
        }
        children.add(transform);
        transform.parent = this;

        //Translate transform's position to its local position
        transform.position.subtract(this.position);
    }

    public void removeChild(RectTransform transform) {
        if (transform.parent != this) return;
        Vector2D worldPos = transform.getWorldPosition();

        children.remove(transform);
        transform.parent = null;

        //Translate transform's position to its world position
        transform.position.set(worldPos);
    }

    public Vector2D getWorldPosition() {
        if (parent == null) {
            return position.clone();
        }
        return parent.getWorldPosition().add(position);
    }
}
