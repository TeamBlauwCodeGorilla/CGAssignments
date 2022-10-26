package design_and_implementation.h8_9.a2_paddle_game.engine.system;

import design_and_implementation.h8_9.a2_paddle_game.engine.ui.TextureType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Sprite extends Rectangle {

    final URL resourceURL;
    private BufferedImage cachedImg;

    public @NotNull TextureType textureType;
    //public @NotNull SpriteMode spriteMode; (Single, Multiple)
    //public @NotNull FilterMode filterMode; (Bilinear)

    Sprite(URL resourceURL) {
        this.resourceURL = resourceURL;
        this.textureType = TextureType.SPRITE;
        refreshImg();
    }

    public @Nullable BufferedImage getImage() {
        if (cachedImg == null) refreshImg();
        return cachedImg;
    }

    void refreshImg() {
        try {
            cachedImg = ImageIO.read(resourceURL);
            width = cachedImg.getWidth();
        } catch (IOException exception) {
            cachedImg = null;
            Console.error(exception.getMessage());
        }
    }

    @Override
    public double getWidth() {
        return cachedImg == null? super.getWidth() : cachedImg.getWidth();
    }

    @Override
    public double getHeight() {
        return cachedImg == null? super.getWidth() : cachedImg.getWidth();
    }
}
