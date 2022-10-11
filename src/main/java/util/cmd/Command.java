package util.cmd;

import java.util.Arrays;

public class Command {

    private final String label;
    private final String[] arguments;

    public Command(String label, String... args) {
        this.label = label;
        this.arguments = args;
    }

    public String getLabel() {
        return label;
    }

    public String[] getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "Command{" +
                "label='" + label + '\'' +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }
}
