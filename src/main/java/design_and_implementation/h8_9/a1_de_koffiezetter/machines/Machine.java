package design_and_implementation.h8_9.a1_de_koffiezetter.machines;

public interface Machine {

    boolean powered(boolean state);
    default void turnOn() {
        powered(true);
    }
    default void turnOff() {
        powered(false);
    }
    boolean isPowered();

    String[] listSlots();
    String[] listPrices();

    String selectSlot(int slot);
    String selectSlot(String productName);

    String getSelected();
}
