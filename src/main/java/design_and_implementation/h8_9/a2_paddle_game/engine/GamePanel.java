package design_and_implementation.h8_9.a2_paddle_game.engine;

import design_and_implementation.h8_9.a2_paddle_game.engine.models.Component;
import design_and_implementation.h8_9.a2_paddle_game.engine.components.SpriteRenderer;
import design_and_implementation.h8_9.a2_paddle_game.engine.components.Transform;
import design_and_implementation.h8_9.a2_paddle_game.engine.models.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class GamePanel extends JPanel implements Runnable {
    final Dimension screenResolution;

    final Time time;

    final Input input;
    Thread gameThread;

    private static int frameRate;
    private static final int MAX_FRAME_SKIP = 5;

    private static boolean running = false;
    public static boolean isRunning() {
        return running;
    }

    public final List<String> filterLayers = new ArrayList<>(List.of(""));

    public final List<GameObject> gameObjects = new ArrayList<>();

    public GamePanel(int width, int height) {
        screenResolution = new Dimension(width, height);
        this.setPreferredSize(screenResolution);
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);

        this.time = new Time();
        this.input = new Input();
        this.addKeyListener(input);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.setFocusable(true);

        setFrameRate(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        running = true;
        time.reset();

        if (frameRate == 0) {
            frameRate = Screen.getMonitorRefreshRate();
        }
        int skipTicks = 1000 / frameRate;

        for (GameObject gameObject : gameObjects) {
            if (gameObject.isEnabled()) {
                for (Component component : gameObject.getComponents()) {
                    component.onEnable();
                }
            }
        }

        float nextGameTick = Time.getTime();
        while (gameThread != null) {

            int iteration = 0;
            while( Time.getTime() > nextGameTick && iteration < MAX_FRAME_SKIP) {
                update();

                nextGameTick += skipTicks;
                iteration++;
            }

            super.repaint();
        }
    }

    private void fixedUpdate() {
        time.calcFixedDeltaTime();

        List<GameObject> orderedHierarchy = getOrderedHierarchy();

        for (GameObject gameObject : orderedHierarchy) {
            for (Component scriptComponent : gameObject.getComponents()) {
                scriptComponent.onFixedUpdate();
            }
        }
    }

    private void update() {
        synchronized (input) {
            float elapsed = Time.getTime() - Time.lastFixedFrameTime;
            if (elapsed >= 1000) {
                fixedUpdate();
            }
            time.calcDeltaTime();

            List<GameObject> orderedHierarchy = getOrderedHierarchy();

            for (GameObject gameObject : orderedHierarchy) {
                for (Component scriptComponent : gameObject.getComponents()) {
                    scriptComponent.onUpdate();
                }
                gameObject.transform.update();
            }

            input.onFrameFinish();
        }
    }

    public List<GameObject> getHierarchyRoot() {
        List<GameObject> hierarchyRoot = new ArrayList<>();

        for (GameObject gameObject : gameObjects) {
            if (gameObject.transform.getParent() != null) continue;
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

    public static int getFrameRate() {
        if (frameRate == 0) {
            frameRate = Screen.getMonitorRefreshRate();
        }
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        if (isRunning()) throw new IllegalStateException("The framerate may not be changed during runtime!");
        GamePanel.frameRate = Math.max(frameRate, -1);
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
                        Console.warn("Couldn't draw object '"+renderer.gameObject+"' on layer '"+renderer.sortingLayer+"' because the layer does not exist!");
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
