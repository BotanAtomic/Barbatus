package org.barbatus.console;

import org.barbatus.core.Barbatus;
import org.barbatus.console.enums.ConsoleLevel;
import org.barbatus.utils.ExceptionUtils;

import java.io.IOException;
import java.io.OutputStream;

public class Console {

    private static ConsoleLevel level = ConsoleLevel.INFO;

    private static ConsoleFormatter formatter = (level, tag, message) ->
            String.format("[%s] %s%s - %s\n", level.name(),
                    level.name().length() < 5 ? " " : "", tag, message);

    private static OutputStream outputStream = System.out, errorStream = System.err;

    public static ConsoleLevel getLevel() {
        return level;
    }

    public static void setLevel(ConsoleLevel level) {
        Console.level = level;
    }

    public static void setOutputStream(OutputStream outputStream) {
        Console.outputStream = outputStream;
    }

    public static void setErrorStream(OutputStream outputStream) {
        Console.errorStream = outputStream;
    }

    public static void setFormatter(ConsoleFormatter formatter) {
        Console.formatter = formatter;
    }

    public static void trace(String tag, String message) {
        out(ConsoleLevel.TRACE, tag, message);
    }

    public static void trace(String message) {
        trace(Barbatus.getApplicationName(), message);
    }

    public static void debug(String tag, String message) {
        out(ConsoleLevel.DEBUG, tag, message);
    }

    public static void debug(String message) {
        debug(Barbatus.getApplicationName(), message);
    }

    public static void info(String tag, String message) {
        out(ConsoleLevel.INFO, tag, message);
    }

    public static void info(String message) {
        info(Barbatus.getApplicationName(), message);
    }

    public static void warn(String tag, String message) {
        out(ConsoleLevel.WARN, tag, message);
    }

    public static void warn(String message) {
        warn(Barbatus.getApplicationName(), message);
    }

    public static void error(String tag, String message) {
        out(ConsoleLevel.ERROR, tag, message);
    }

    public static void error(String message) {
        error(Barbatus.getApplicationName(), message);
    }

    public static void error(Exception exception) {
        error(Barbatus.getApplicationName(), ExceptionUtils.getStackTrace(exception));
    }

    public static void error(String tag, Exception exception) {
        warn(tag, ExceptionUtils.getStackTrace(exception));
    }

    private static void out(ConsoleLevel level, String tag, String message) {
        if (getLevel().ordinal() <= level.ordinal()) {
            try {
                (level == ConsoleLevel.ERROR ? errorStream : outputStream)
                        .write(formatter.format(level, tag, message).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
