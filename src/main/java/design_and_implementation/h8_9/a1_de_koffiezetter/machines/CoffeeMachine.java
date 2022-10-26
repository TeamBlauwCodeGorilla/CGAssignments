package design_and_implementation.h8_9.a1_de_koffiezetter.machines;

import design_and_implementation.h8_9.a1_de_koffiezetter.Locale;
import design_and_implementation.h8_9.a1_de_koffiezetter.items.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class CoffeeMachine implements Machine {

    private final String brand;
    protected final List<Item> options;
    private Item selected;

    public CoffeeMachine(String brand) {
        this.brand = brand;
        this.options = new ArrayList<>();
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String[] listSlots() {
        String[] list = new String[options.size()];
        for (int i = 0; i < list.length; i++) {
            Item item = options.get(i);
            list[i] = Locale.MACHINE_INFO_SLOT.msg(i+"", item.getName());
        }
        return list;
    }

    @Override
    public String[] listPrices() {
        String[] list = new String[options.size()];
        for (int i = 0; i < list.length; i++) {
            Item item = options.get(i);
            list[i] = Locale.ITEM_INFO.msg(item.getName(), item.getPrice()+"");
        }
        return list;
    }

    @Override
    public String selectSlot(int slot) {
        if (slot < 0 || slot > options.size()) return "";
        Item item = options.get(slot);
        if (item == null) return "";
        return Locale.ITEM_INFO.msg(item.getName(), item.getPrice()+"");
    }

    @Override
    public String selectSlot(String productName) {
        if (productName == null || productName.trim().isEmpty()) return "";
        Item item = null;
        for (Item option : options) {
            if (productName.equalsIgnoreCase(option.getName())) {
                item = option;
                break;
            }
        }
        if (item == null) return "";
        return Locale.ITEM_INFO.msg(item.getName(), item.getPrice()+"");
    }

    @Override
    public String getSelected() {
        return selected == null? "" : Locale.ITEM_INFO.msg(selected.getName(), selected.getPrice()+"");
    }
}
