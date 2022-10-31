package design_and_implementation.h8_9.a2_paddle_game.engine.models;

import design_and_implementation.h8_9.a2_paddle_game.engine.components.RectTransform;
import org.jetbrains.annotations.NotNull;

/**
 * It's recommended to not declare any constructor definitions other than the default non-arg public constructor as this may cause problems.
 * Instead, use the onEnable for constructing, and onDisable for deconstructing functionality.
 */
public abstract class Component {

    public @NotNull GameObject gameObject = GameObject.EMPTY;
    public @NotNull RectTransform transform = gameObject.transform;

    public Component() { }

    /**
     * Executes upon creation of this Component.
     */
    protected void onInitialize() { }

    /**
     * Executes upon enabling of this GameObject.
     */
    protected void onEnable() { }

    /**
     * Executes upon disabling of this GameObject.
     */
    protected void onDisable() { }

    /**
     * Executes every frame.
     */
    protected void onUpdate() { }

    /**
     * Executes every fixed frame (Once a second).
     */
    protected void onFixedUpdate() { }

}