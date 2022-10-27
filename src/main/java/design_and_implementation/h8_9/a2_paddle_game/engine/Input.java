package design_and_implementation.h8_9.a2_paddle_game.engine;

import org.jetbrains.annotations.NotNull;

import javax.swing.event.MouseInputListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.*;

public class Input implements KeyListener, MouseInputListener {

    private static final Set<String> pressedThisFrame = new HashSet<>();
    private static final Map<String, Float> active = new HashMap<>();
    private static final Set<String> releasedThisFrame = new HashSet<>();

    public static final List<Axis> AXES = new ArrayList<>();

    static {
        //region Horizontal Axis
        Axis horizontalK = new Axis("Horizontal");
        AXES.add(horizontalK);
        horizontalK.negativeButton = getKeyName(KeyEvent.VK_LEFT);
        horizontalK.positiveButton = getKeyName(KeyEvent.VK_RIGHT);
        horizontalK.altNegativeButton = getKeyName(KeyEvent.VK_A);
        horizontalK.altPositiveButton = getKeyName(KeyEvent.VK_D);
        horizontalK.type = AxisType.KEY_OR_MOUSE_BUTTON;
        horizontalK.axis = AxisDirection.X_AXIS;
        //endregion

        //region Vertical Axis
        Axis verticalK = new Axis("Vertical");
        AXES.add(verticalK);
        verticalK.negativeButton = getKeyName(KeyEvent.VK_UP);
        verticalK.positiveButton = getKeyName(KeyEvent.VK_DOWN);
        verticalK.altNegativeButton = getKeyName(KeyEvent.VK_W);
        verticalK.altPositiveButton = getKeyName(KeyEvent.VK_S);
        verticalK.type = AxisType.KEY_OR_MOUSE_BUTTON;
        verticalK.axis = AxisDirection.Y_AXIS;
        //endregion

        //region Jump
        Axis jumpK = new Axis("Jump");
        AXES.add(jumpK);
        jumpK.positiveButton = getKeyName(KeyEvent.VK_SPACE);
        jumpK.type = AxisType.KEY_OR_MOUSE_BUTTON;
        //endregion
    }

    void onFrameFinish() {
        pressedThisFrame.clear();
        releasedThisFrame.clear();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        String btn = KeyEvent.getKeyText(e.getKeyCode());
        if (getKey(btn)) return;
        synchronized (this) {
            pressedThisFrame.add(btn);
            active.put(btn, 1.0f);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String btn = KeyEvent.getKeyText(e.getKeyCode());
        if (!getKey(btn)) return;
        synchronized (this) {
            active.remove(btn);
            releasedThisFrame.add(btn);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.NOBUTTON) return;
        String btn = getMouseButtonName(e.getButton());
        synchronized (this) {
            pressedThisFrame.add(btn);
            releasedThisFrame.add(btn);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.NOBUTTON) return;
        String btn = getMouseButtonName(e.getButton());
        if (getButton(btn)) return;
        synchronized (this) {
            pressedThisFrame.add(btn);
            active.put(btn, 1.0f);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.NOBUTTON) return;
        String btn = getMouseButtonName(e.getButton());
        if (!getButton(btn)) return;
        synchronized (this) {
            active.remove(btn);
            releasedThisFrame.add(btn);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { /* Unused */ }

    @Override
    public void mouseExited(MouseEvent e) { /* Unused */ }

    @Override
    public void mouseDragged(MouseEvent e) { /* Unused */ }

    @Override
    public void mouseMoved(MouseEvent e) { /* Unused */ }

    //region Virtual Button

    /**
     * Returns true during the frame the user pressed down the virtual button identified by buttonName.
     * @param buttonName the name of the virtual button
     * @return true during the frame the user pressed down the virtual button identified by buttonName
     */
    public static boolean getButtonDown(String buttonName) {
        return Arrays.stream(getAxes(buttonName)).anyMatch(axis -> {
            if (pressedThisFrame.contains(axis.negativeButton)) return true;
            if (pressedThisFrame.contains(axis.positiveButton)) return true;
            if (pressedThisFrame.contains(axis.altNegativeButton)) return true;
            return pressedThisFrame.contains(axis.altPositiveButton);
        });
    }

    /**
     * Returns true while the virtual button identified by buttonName is held down.
     * @param buttonName the name of the virtual button
     * @return true while the virtual button identified by buttonName is held down
     */
    public static boolean getButton(String buttonName) {
        return Arrays.stream(getAxes(buttonName)).anyMatch(axis -> {
            if (active.containsKey(axis.negativeButton)) return true;
            if (active.containsKey(axis.positiveButton)) return true;
            if (active.containsKey(axis.altNegativeButton)) return true;
            return active.containsKey(axis.altPositiveButton);
        });
    }

    /**
     * Returns true the first frame the user releases the virtual button identified by buttonName.
     * @param buttonName the name of the virtual button
     * @return true the first frame the user releases the virtual button identified by buttonName
     */
    public static boolean getButtonUp(String buttonName) {
        return Arrays.stream(getAxes(buttonName)).anyMatch(axis -> {
            if (releasedThisFrame.contains(axis.negativeButton)) return true;
            if (releasedThisFrame.contains(axis.positiveButton)) return true;
            if (releasedThisFrame.contains(axis.altNegativeButton)) return true;
            return releasedThisFrame.contains(axis.altPositiveButton);
        });
    }
    //endregion

    //region Key Button

    /**
     * Returns true during the frame the user starts pressing down the key identified by keyName.
     * @param keyName the name of the key
     * @return true during the frame the user starts pressing down the key identified by keyName
     */
    public static boolean getKeyDown(String keyName) {
        return pressedThisFrame.contains(keyName);
    }

    /**
     * Returns true while the user holds down the key identified by keyName.
     * @param keyName the name of the key
     * @return true while the user holds down the key identified by keyName
     */
    public static boolean getKey(String keyName) {
        return active.containsKey(keyName);
    }

    /**
     * Returns true during the frame the user releases the key identified by keyName.
     * @param keyName the name of the key
     * @return true during the frame the user releases the key identified by keyName
     */
    public static boolean getKeyUp(String keyName) {
        return releasedThisFrame.contains(keyName);
    }
    //endregion

    //region Mouse Button

    /**
     * Returns true during the frame the user pressed the given mouse button.
     * @param button the number of the mouse button
     * @return true during the frame the user pressed the given mouse button
     */
    public static boolean getMouseButtonDown(int button) {
        return pressedThisFrame.contains(getMouseButtonName(button));
    }

    /**
     * Returns whether the given mouse button is held down.
     * @param button the number of the mouse button
     * @return whether the given mouse button is held down
     */
    public static boolean getMouseButton(int button) {
        return active.containsKey(getMouseButtonName(button));
    }

    /**
     * Returns true during the frame the user releases the given mouse button.
     * @param button the number of the mouse button
     * @return true during the frame the user releases the given mouse button
     */
    public static boolean getMouseButtonUp(int button) {
        return releasedThisFrame.contains(getMouseButtonName(button));
    }
    //endregion

    /**
     * Returns the value of the virtual axis identified by axisName.
     * @param axisName the name of the virtual axis
     * @return the value of the virtual axis identified by axisName
     */
    public static float getAxis(@NotNull String axisName) {
        Axis[] axes = getAxes(axisName);
        if (axes.length == 0) return 0;
        Float[] values = new Float[axes.length];

        for (int i = 0; i < axes.length; i++) {
            Axis axis = axes[i];
            if (!axis.negativeButton.isBlank() && !axis.negativeButton.isBlank()) {
                values[i] = -active.getOrDefault(axis.negativeButton, 0f) + active.getOrDefault(axis.positiveButton, 0f);
                if (values[i] == 0 && !axis.altNegativeButton.isBlank() && !axis.altPositiveButton.isBlank()) {
                    values[i] = -active.getOrDefault(axis.altNegativeButton, 0f) + active.getOrDefault(axis.altPositiveButton, 0f);
                }
            } else {
                values[i] = -active.getOrDefault(axis.altNegativeButton, 0f) + active.getOrDefault(axis.altPositiveButton, 0f);
            }
        }

        int strongestIndex = 0;
        float strongestValue = 0;
        for (int i = 0; i < values.length; i++) {
            float absVal = Math.abs(values[i]);
            if (absVal > strongestValue) {
                strongestIndex = i;
                strongestValue = absVal;
            }
        }

        return Math.max(Math.min(values[strongestIndex], 1), -1);
    }

    /**
     * Returns the value of the virtual axis identified by axisName with no smoothing filtering applied.
     * @param axisName the name of the virtual axis
     * @return the value of the virtual axis identified by axisName with no smoothing filtering applied
     */
    public static float getAxisRaw(@NotNull String axisName) {
        float val = getAxis(axisName);
        if (val == 0) return 0;
        return val > 0? 1 : -1;
    }

    //region Helper Methods
    public static String getMouseButtonName(int button) {
        return "Button "+(button+1);
    }

    public static String getKeyName(char keyChar) {
        return KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar(keyChar));
    }
    public static String getKeyName(int keyCode) {
        return KeyEvent.getKeyText(keyCode);
    }
    private static Axis[] getAxes(@NotNull String axisName) {
        return AXES.stream().filter(axis -> axis.name.equalsIgnoreCase(axisName)).toArray(Axis[]::new);
    }
    //endregion
}
