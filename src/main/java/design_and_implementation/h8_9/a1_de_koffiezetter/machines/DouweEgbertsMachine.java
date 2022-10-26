package design_and_implementation.h8_9.a1_de_koffiezetter.machines;

public class DouweEgbertsMachine extends CoffeeMachine {

    private boolean powered;

    public DouweEgbertsMachine() {
        super("Douwe Egberts");
    }

    @Override
    public boolean powered(boolean state) {
        return powered = state;
    }

    @Override
    public boolean isPowered() {
        return powered;
    }
}
