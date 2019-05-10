package org.barbatus.core;

import org.barbatus.console.ConsoleFormatter;
import org.barbatus.enums.ConsoleLevel;

import java.io.OutputStream;

public class Barbatus {

    private static ConsoleLevel consoleLevel = ConsoleLevel.INFO;

    private static ConsoleFormatter consoleFormatter = (level, tag, message) ->
            String.format("[%s] %s - %s\n", level.name(), tag, message);

    private static OutputStream outputStream = System.out;


    public static ConsoleLevel getConsoleLevel() {
        return consoleLevel;
    }

    public static void setConsoleLevel(ConsoleLevel consoleLevel) {
        Barbatus.consoleLevel = consoleLevel;
    }

    public static OutputStream getOutputStream() {
        return outputStream;
    }

    public static void setOutputStream(OutputStream outputStream) {
        Barbatus.outputStream = outputStream;
    }

    public static ConsoleFormatter getConsoleFormatter() {
        return consoleFormatter;
    }

    public static void setConsoleFormatter(ConsoleFormatter consoleFormatter) {
        Barbatus.consoleFormatter = consoleFormatter;
    }
}
