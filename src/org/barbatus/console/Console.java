package org.barbatus.console;

import org.barbatus.core.Barbatus;
import org.barbatus.enums.ConsoleLevel;
import org.barbatus.utils.ExceptionUtils;

import java.io.IOException;

public class Console {

    public static void trace(String tag, String message) {
        out(ConsoleLevel.TRACE, tag, message);
    }

    public static void trace(String message) {
        trace("BARBATUS", message);
    }

    public static void debug(String tag, String message) {
        out(ConsoleLevel.DEBUG, tag, message);
    }

    public static void debug(String message) {
        debug("BARBATUS", message);
    }

    public static void info(String tag, String message) {
        out(ConsoleLevel.INFO, tag, message);
    }

    public static void info(String message) {
        info("BARBATUS", message);
    }

    public static void warn(String tag, String message) {
        out(ConsoleLevel.WARN, tag, message);
    }

    public static void warn(String message) {
        warn("BARBATUS", message);
    }

    public static void error(String tag, String message) {
        out(ConsoleLevel.ERROR, tag, message);
    }

    public static void error(String message) {
        error("BARBATUS", message);
    }

    public static void error(Exception exception) {
        error("BARBATUS", ExceptionUtils.getStackTrace(exception));
    }

    public static void error(String tag, Exception exception) {
        warn(tag, ExceptionUtils.getStackTrace(exception));
    }

    private static void out(ConsoleLevel level, String tag, String message) {
        if (Barbatus.getConsoleLevel().ordinal() <= level.ordinal()) {
            try {
                (level == ConsoleLevel.ERROR ? System.err : Barbatus.getOutputStream())
                        .write(Barbatus.getConsoleFormatter().format(level, tag, message).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
