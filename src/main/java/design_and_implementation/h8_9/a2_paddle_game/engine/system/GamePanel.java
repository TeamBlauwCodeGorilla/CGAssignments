package design_and_implementation.h8_9.a2_paddle_game.engine.system;

import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;
import design_and_implementation.h8_9.a2_paddle_game.engine.components.SpriteRenderer;
import design_and_implementation.h8_9.a2_paddle_game.engine.components.Transform;
import design_and_implementation.h8_9.a2_paddle_game.engine.controls.Input;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class GamePanel extends JPanel implements Runnable {

    final int unitSize;
    final int scale;
    final int tileSize;
    final int maxScreenCols, maxScreenRows;

    final Time time;

    final Input input;
    Thread gameThread;

    private int frameRate;

    private static boolean running = false;
    public static boolean isRunning() {
        return running;
    }

    public final List<String> filterLayers = new ArrayList<>(List.of(""));

    public final List<GameObject> gameObjects = new ArrayList<>();

    public GamePanel(int cols, int rows) {
        this(cols, rows, 16, 3);
    }

    public GamePanel(int cols, int rows, int unitSize, int scale) {
        this.unitSize = unitSize;
        this.scale = scale;
        this.tileSize = unitSize * scale;
        this.maxScreenCols = cols;
        this.maxScreenRows = rows;

        Dimension screenResolution = new Dimension(tileSize * maxScreenCols, tileSize * maxScreenRows);
        this.setPreferredSize(screenResolution);
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);

        this.time = new Time();
        this.input = new Input();
        this.addKeyListener(input);
        this.setFocusable(true);

        this.frameRate = -1;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        running = true;
        time.reset();
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isEnabled()) {
                for (Component component : gameObject.getComponents()) {
                    component.onEnable();
                }
            }
        }

        while (gameThread != null) {

            if (frameRate != -1 && time.framesElapsed >= frameRate) {
                if (ChronoUnit.MILLIS.between(time.lastFixedFrame, Instant.now()) < 1000) {
                    continue;
                }
            }

            if (ChronoUnit.MILLIS.between(time.lastFixedFrame, Instant.now()) >= 1000) {
                fixedUpdate();
            } else {
                update(false);
            }

            super.repaint();
        }
    }

    private void fixedUpdate() {
        time.lastFixedFrame = Instant.now();
        time.calcFixedDeltaTime();

        if (frameRate == 0) {
            frameRate = getMonitorRefreshRate();
        }

        update(true);
    }

    private void update(boolean fixed) {
        time.lastFrame = Instant.now();
        time.calcDeltaTime();

        List<GameObject> hierarchyRoot = getHierarchyRoot();
        hierarchyRoot.sort(Collections.reverseOrder());

        for (GameObject gameObject : hierarchyRoot) {
            for (Component scriptComponent : gameObject.getComponents()) {
                if (fixed) {
                    scriptComponent.onFixedUpdate();
                }
                scriptComponent.onUpdate();
            }
            gameObject.transform.update();
        }
    }

    public List<GameObject> getHierarchyRoot() {
        List<GameObject> hierarchyRoot = new ArrayList<>();

        for (GameObject gameObject : gameObjects) {
            if (gameObject.transform.parent != null) continue;
            hierarchyRoot.add(gameObject);
        }

        return hierarchyRoot;
    }

    public List<GameObject> getOrderedHierarchy() {
        List<Transform> hierarchy = new ArrayList<>();
        for (GameObject root : getHierarchyRoot()) {
            hierarchy.add(root.transform);
            root.transform.getAllChildren(hierarchy);
        }
        return hierarchy.stream().map(transform -> transform.gameObject).collect(Collectors.toList());
    }

    public int getMonitorRefreshRate() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        int lowest = Integer.MAX_VALUE;

        for (GraphicsDevice g : gs) {
            DisplayMode dm = g.getDisplayMode();

            int refreshRate = dm.getRefreshRate();
            if (refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
                System.out.println("Unknown rate");
            }

            if (refreshRate < lowest) {
                lowest = refreshRate;
            }
        }

        return lowest;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = Math.max(frameRate, -1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!(g instanceof Graphics2D g2D)) return;

        Map<String, BufferedImage> layers = new HashMap<>();
        Rectangle bounds = g.getClipBounds();
        for (String filterLayer : filterLayers) {
            layers.put(filterLayer, new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_ARGB));
        }

        List<GameObject> hierarchy = getOrderedHierarchy();
        hierarchy.sort(Collections.reverseOrder());
        hierarchy.stream()
                .map(gameObject -> gameObject.getComponentOfType(SpriteRenderer.class))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(spriteRenderer -> spriteRenderer.orderInLayer))
                .forEachOrdered(renderer -> {
                    BufferedImage layer = layers.getOrDefault(renderer.sortingLayer, layers.get(""));
                    if (layer == null) {
                        Console.warn("Couldn't draw object '"+renderer.gameObject+"' on layer '"+renderer.sortingLayer+"'");
                        return;
                    }
                    renderer.render(layer.createGraphics());
                });

        //Draw all layers
        for (Map.Entry<String, BufferedImage> layer : layers.entrySet()) {
            g2D.drawImage(layer.getValue(), 0, 0, bounds.width, bounds.height, null);
        }

        g2D.dispose();
    }
}
