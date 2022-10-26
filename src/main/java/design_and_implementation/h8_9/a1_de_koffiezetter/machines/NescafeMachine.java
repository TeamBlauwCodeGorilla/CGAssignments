package design_and_implementation.h8_9.a1_de_koffiezetter.machines;

public class NescafeMachine extends CoffeeMachine {

    private boolean powered;

    public NescafeMachine() {
        super("Nescafe");
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
