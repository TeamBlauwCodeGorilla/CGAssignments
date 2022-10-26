package design_and_implementation.h8_9.a2_paddle_game.engine.models;

import design_and_implementation.h8_9.a2_paddle_game.engine.components.Transform;
import org.jetbrains.annotations.NotNull;

/**
 * Its recommended to not declare any constructor definitions other than the default non-arg public constructor as this may cause problems.
 * Instead, use the onEnable for constructing, and onDisable for deconstructing functionality.
 */
public abstract class Component {

    public @NotNull GameObject gameObject = GameObject.EMPTY;
    public @NotNull Transform transform = gameObject.transform;

    public Component() { }

    /**
     * Executes upon enabling of this GameObject.
     */
    public void onEnable() { }

    /**
     * Executes upon disabling of this GameObject.
     */
    public void onDisable() { }

    /**
     * Executes every frame.
     */
    public void onUpdate() { }

    /**
     * Executes every fixed frame (Once a second).
     */
    public void onFixedUpdate() { }

}