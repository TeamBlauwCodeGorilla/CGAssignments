package design_and_implementation.h8_9.a2_paddle_game.engine;

import org.jetbrains.annotations.NotNull;

public class Axis {

    //The name of the axes
    public @NotNull String name;

    //The button used to push the axis in the negative direction.
    public @NotNull String negativeButton;
    public @NotNull String positiveButton;
    public @NotNull String altNegativeButton;
    public @NotNull String altPositiveButton;

    @NotNull AxisType type = AxisType.KEY_OR_MOUSE_BUTTON;
    @NotNull AxisDirection axis = AxisDirection.X_AXIS;

    public Axis(@NotNull String name) {
        this.name = name;
        negativeButton = "";
        positiveButton = "";
        altNegativeButton = "";
        altPositiveButton = "";
    }
}
