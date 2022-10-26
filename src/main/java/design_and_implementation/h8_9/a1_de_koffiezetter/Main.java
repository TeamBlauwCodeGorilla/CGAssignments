package design_and_implementation.h8_9.a1_de_koffiezetter;

import design_and_implementation.h8_9.a1_de_koffiezetter.machines.CoffeeMachine;
import design_and_implementation.h8_9.a1_de_koffiezetter.machines.DouweEgbertsMachine;
import design_and_implementation.h8_9.a1_de_koffiezetter.machines.NescafeMachine;
import util.cmd.Command;
import util.cmd.CommandScanner;

public class Main {

    private static final String input = """
            status
            select machine nescafe
            zet aan
            status
            toon prijzen
            select keuze cappucino
            toon selectie
            select koffie
            toon selectie
            """;

    private static CoffeeMachine coffeeMachine;

    public static void main(String[] args) {
        //TODO: Implement the assignment: 'De koffiezetter' using inheritance and abstraction of classes and interfaces.
        // Tip: Do this on your own branch containing your first name.

        CommandScanner scanner = new CommandScanner(input);
        while (scanner.hasNext()) {
            Command next = scanner.nextCommand();
            System.out.println(next);

            if (next.getLabel().equals(Locale.GENERIC_CMD_STATUS.msg())) {
                //TODO: Toon of de machine aan of uit staat.
                if (coffeeMachine == null) {
                    Locale.MACHINE_MISSING.print();
                } else {
                    Locale.MACHINE_INFO_POWER.print(coffeeMachine.isPowered()? Locale.GENERIC_ON : Locale.GENERIC_OFF);
                }
            } else if (next.getLabel().equals(Locale.GENERIC_CMD_SET.msg())) {
                if (next.getArguments().length > 0) {
                    if (coffeeMachine == null) {
                        Locale.MACHINE_MISSING.print();
                    } else {
                        if (next.getArguments()[0].equals(Locale.GENERIC_ON.msg())) {
                            coffeeMachine.turnOn();
                            Locale.MACHINE_UPDATE_POWER.print(Locale.GENERIC_ON.msg());
                        } else if (next.getArguments()[0].equals(Locale.GENERIC_OFF.msg())) {
                            coffeeMachine.turnOff();
                            Locale.MACHINE_UPDATE_POWER.print(Locale.GENERIC_OFF.msg());
                        }
                    }
                }
            } else if (next.getLabel().equals(Locale.GENERIC_CMD_SELECT.msg())) {
                if (next.getArguments().length > 1) {
                    if (next.getArguments()[0].equals("machine")) {
                        //TODO: Save coffee machines so a second one of the same machine doesn't get instantiated.
                        if (next.getArguments()[1].equals("nescafe")) {
                            coffeeMachine = new NescafeMachine();
                        } else if (next.getArguments()[1].equals("Douwe Egberts")) {
                            coffeeMachine = new DouweEgbertsMachine();
                        }
                    } else if (next.getArguments()[0].equals("keuze")) {
                        if (coffeeMachine == null) {
                            Locale.MACHINE_MISSING.print();
                        } else {
                            //TODO: Selecteer een optie van de machine.
                            int slot;
                            try {
                                slot = Integer.parseInt(next.getArguments()[1]);
                            } catch (Exception ignored) {
                                coffeeMachine.selectSlot(next.getArguments()[1]);
                                Locale.MACHINE_MISSING_ITEM.print(next.getArguments()[1]);
                                continue;
                            }

                            coffeeMachine.selectSlot(slot);
                        }
                    }
                }
            } else if (next.getLabel().equals(Locale.GENERIC_CMD_SHOW.msg())) {
                if (next.getArguments().length > 0) {
                    if (next.getArguments()[0].equals("selectie")) {
                        //TODO: Toon de geselecteerde optie van de machine.
                    } else if (next.getArguments()[0].equals("prijzen")) {
                        //TODO: Toon de selectie aan opties met hun prijzen van de machine.
                    } else if (next.getArguments()[0].equals("machines")) {
                        //TODO: Toon een lijst met machines
                    }
                }
            }
        }
    }
}