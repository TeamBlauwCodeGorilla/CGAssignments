package util.cmd;

import java.io.InputStream;
import java.util.Scanner;

public class CommandScanner {

    Scanner scanner;

    public CommandScanner(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public CommandScanner(String input) {
        scanner = new Scanner(input);
    }

    public boolean hasNext() {
        try {
            return scanner.hasNextLine();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public Command nextCommand() {
        String line = scanner.nextLine();
        String[] arr = line.split(" ", 2);
        return new Command(arr[0], arr.length == 1? new String[0] : arr[1].split(" "));
    }
}
