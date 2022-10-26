package design_and_implementation.h8_9.a2_paddle_game.engine.components;

import design_and_implementation.h8_9.a2_paddle_game.engine.math.Vector2D;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;
import design_and_implementation.h8_9.a2_paddle_game.engine.system.Sprite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class SpriteRenderer extends Component {

    public @Nullable Sprite sprite;
    public @NotNull Color color = Color.WHITE;
    public @Nullable Shape maskShape;

    public @NotNull String sortingLayer = "";
    public int orderInLayer = 0;

    public void render(Graphics2D graphics) {
        Vector2D position = gameObject.transform.getWorldPosition();
        if (sprite != null) {
            BufferedImage image = sprite.getImage();
            if (image == null) {
                graphics.setColor(color);
                graphics.fillRect(position.x, position.y, transform.size.width, transform.size.height);
            } else {
                graphics.drawImage(image, position.x, position.y, (int) sprite.getWidth(), (int) sprite.getHeight(), null);
            }
        } else {
            graphics.setColor(color);
            graphics.fillRect(position.x, transform.position.y, transform.size.width, transform.size.height);
        }
    }
}
