package design_and_implementation.h8_9.a2_paddle_game.engine;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class ResourceManager {

    private static ResourceManager instance;

    public static ResourceManager getInstance() {
        return instance == null? instance = new ResourceManager() : instance;
    }

    private final Map<URL, Sprite> spriteCache = new HashMap<>();

    private ResourceManager() { }

    /**
     * Loads a sprite by its url. Or reuses the old sprite of the same url that was cached.
     * @param url the url of which sprite to retrieve.
     * @return the newly loaded or cached sprite.
     */
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
