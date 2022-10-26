package design_and_implementation.h8_9.a2_paddle_game.engine.system;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ResourceManager {

    private static ResourceManager instance;

    public static ResourceManager getInstance() {
        return instance == null? instance = new ResourceManager() : instance;
    }

    private final Map<URL, Sprite> spriteCache = new HashMap<>();

    private ResourceManager() { }

    public Sprite getSprite(URL url) {
        Sprite sprite = spriteCache.get(url);
        if (sprite != null) {
            return sprite;
        }

        sprite = new Sprite(url);
        spriteCache.put(url, sprite);
        return sprite;
    }
}
