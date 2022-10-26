package design_and_implementation.h8_9.a2_paddle_game.engine.controls;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class Input implements KeyListener {

    //region KEY MAPPINGS
    private static final Map<InputKey, Integer> keyMapping = new HashMap<>();
    //endregion

    private static final Map<Integer, Float> active = new HashMap<>();

    static {
        keyMapping.put(InputKey.KEY_UP, KeyEvent.VK_W);
        keyMapping.put(InputKey.KEY_DOWN, KeyEvent.VK_S);
        keyMapping.put(InputKey.KEY_LEFT, KeyEvent.VK_A);
        keyMapping.put(InputKey.KEY_RIGHT, KeyEvent.VK_D);
        keyMapping.put(InputKey.KEY_JUMP, KeyEvent.VK_SPACE);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        active.put(e.getKeyCode(), 1.0f);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        active.remove(e.getKeyCode());
    }

    public static float getHorizontal() {
        return -active.getOrDefault(getKeyMapping(InputKey.KEY_LEFT), 0.0f) + active.getOrDefault(getKeyMapping(InputKey.KEY_RIGHT), 0.0f);
    }

    public static float getVertical() {
        return -active.getOrDefault(getKeyMapping(InputKey.KEY_UP), 0.0f) + active.getOrDefault(getKeyMapping(InputKey.KEY_DOWN), 0.0f);
    }

    public static boolean isPressed(int keyCode) {
        return active.containsKey(keyCode);
    }

    public static boolean isPressed(@NotNull InputKey key) {
        return active.containsKey(getKeyMapping(key, -1));
    }

    public static @Nullable Integer mapKey(@NotNull InputKey key, int keyCode) {
        return keyMapping.put(key, keyCode);
    }

    public static @Nullable Integer getKeyMapping(@NotNull InputKey key) {
        return keyMapping.get(key);
    }

    public static @Nullable Integer getKeyMapping(@NotNull InputKey key, int defaultValue) {
        return keyMapping.getOrDefault(key, defaultValue);
    }
}
