package design_and_implementation.h8_9.a2_paddle_game.engine.models;

import design_and_implementation.h8_9.a2_paddle_game.engine.Engine;
import design_and_implementation.h8_9.a2_paddle_game.engine.components.RectTransform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class GameObject implements Comparable<GameObject> {

    static final GameObject EMPTY = new GameObject(false, "gameObject", null);
    public static GameObject empty(@Nullable RectTransform parent) {
        return new GameObject(true, "gameObject", parent);
    }

    private String name;
    private String tag;
    private boolean enabled;

    public final RectTransform transform;

    private final List<Component> components;

    public GameObject(@NotNull String name, Component... components) {
        this(name, null, components);
    }

    public GameObject(@NotNull String name, @Nullable RectTransform parent, Component... components) {
        this(true, name, parent, components);
    }

    private GameObject(boolean add2Hierarchy, @NotNull String name, @Nullable RectTransform parent, Component... components) {
        this.name = name;
        this.enabled = true;
        this.transform = new RectTransform(this);
        if (parent != null) {
            parent.addChild(this.transform);
        }
        this.components = new ArrayList<>(List.of(components));
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @Nullable String getTag() {
        return tag;
    }

    public void setTag(@Nullable String tag) {
        this.tag = tag;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean state) {
        if (enabled == state) return;
        this.enabled = state;
        if (!Engine.isRunning()) return;
        if (state) {
            for (Component component : components) {
                component.onEnable();
            }
        } else {
            for (Component component : components) {
                component.onDisable();
            }
        }
    }

    public <C extends Component> C addComponent(Class<C> component) {
        if (getComponentOfType(component) != null) return null;
        try {
            Constructor<C> constructor = component.getDeclaredConstructor();
            C instance = constructor.newInstance();

            //Assign gameObject
            Field field = instance.getClass().getField("gameObject");
            boolean isFieldAccessible = field.canAccess(instance);
            field.setAccessible(true);

            try {
                field.set(instance, this);
            } finally {
                field.setAccessible(isFieldAccessible);
            }

            //Assign transform
            field = instance.getClass().getField("transform");
            isFieldAccessible = field.canAccess(instance);
            field.setAccessible(true);
            try {
                field.set(instance, this.transform);
            } finally {
                field.setAccessible(isFieldAccessible);
            }

            components.add(instance);
            return instance;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void removeComponent(@NotNull Component component) {
        components.remove(component);
    }

    public void removeComponentOfType(@NotNull Class<? extends Component> type) {
        Component component = getComponentOfType(type);
        if (component == null) return;
        removeComponent(component);
    }

    public Component[] getComponents() {
        return components.toArray(Component[]::new);
    }

    public <C extends Component> C getComponentOfType(@NotNull Class<C> type) {
        for (Component component : components) {
            if (component.getClass().equals(type)) {
                return type.cast(component);
            }
        }
        return null;
    }

    public <C extends Component> List<C> getComponentsOfType(@NotNull Class<C> type) {
        List<C> matches = new ArrayList<>();
        for (Component component : components) {
            if (component.getClass().equals(type)) {
                matches.add(type.cast(component));
            }
        }
        return matches;
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", enabled=" + enabled +
                ", transform=" + transform +
                ", components=" + components +
                '}';
    }

    @Override
    public int compareTo(@NotNull GameObject o) {
        return 0;
    }
}
