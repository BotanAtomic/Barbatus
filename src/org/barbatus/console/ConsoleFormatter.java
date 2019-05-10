package org.barbatus.console;

import org.barbatus.enums.ConsoleLevel;

public interface ConsoleFormatter {

    String format(ConsoleLevel level, String tag, String message);

}
