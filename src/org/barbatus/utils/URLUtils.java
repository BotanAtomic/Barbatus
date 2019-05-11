package org.barbatus.utils;

import org.barbatus.common.StringPair;

import java.util.ArrayList;
import java.util.List;

public class URLUtils {

    public static List<StringPair> parseQuery(String raw) {
        if (raw == null || raw.isEmpty()) {
            return null;
        }

        final List<StringPair> data = new ArrayList<>();
        String[] parameters = raw.split("&");

        for (String parameter : parameters) {
            String[] rawKeyValue = parameter.split("=");

            if (rawKeyValue.length == 2)
                data.add(new StringPair(rawKeyValue[0], rawKeyValue[1]));
        }

        return data;
    }

}
