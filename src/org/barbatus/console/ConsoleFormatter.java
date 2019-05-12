package org.barbatus.console;

import org.barbatus.console.enums.ConsoleLevel;

public interface ConsoleFormatter {

    String format(ConsoleLevel level, String tag, String message);

}
