package design_and_implementation.h8_9.a1_de_koffiezetter;

import util.cmd.Command;
import util.cmd.CommandScanner;

public class Main {
    public static void main(String[] args) {
        //TODO: Implement the assignment: 'De koffiezetter' using inheritance and abstraction of classes and interfaces.
        // Tip: Do this on your own branch containing your first name.

        CommandScanner scanner = new CommandScanner(System.in);
        while (scanner.hasNext()) {
            Command next = scanner.nextCommand();
            System.out.println(next);
        }
    }
}