package design_and_implementation.h8_9.a1_de_koffiezetter;

import util.cmd.Command;
import util.cmd.CommandScanner;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.Scanner;

public class Main {

    private static final String input = """
            status
            zet aan
            status
            select cappucino
            toon selectie
            select koffie
            toon selectie
            toon prijzen
            """;

    public static void main(String[] args) {
        //TODO: Implement the assignment: 'De koffiezetter' using inheritance and abstraction of classes and interfaces.
        // Tip: Do this on your own branch containing your first name.

        CommandScanner scanner = new CommandScanner(input);
        while (scanner.hasNext()) {
            Command next = scanner.nextCommand();
            System.out.println(next);

            if (next.getLabel().equals("status")) {
                //TODO: Toon of de machine aan of uit staat.
            } else if (next.getLabel().equals("zet")) {
                if (next.getArguments().length > 0) {
                    if (next.getArguments()[0].equals("aan")) {
                        //TODO: Zet machine aan.
                    } else if (next.getArguments()[0].equals("uit")) {
                        //TODO: Zet machine uit.
                    }
                }
            } else if (next.getLabel().equals("select")) {
                if (next.getArguments().length > 0) {
                    if (next.getArguments()[0].equals("machine")) {
                        //TODO: Selecteer een machine.
                    } else if (next.getArguments()[0].equals("keuze")) {
                        //TODO: Selecteer een optie van de machine.
                    }
                }
            } else if (next.getLabel().equals("toon")) {
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