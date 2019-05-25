package org.barbatus.common.pair;

import org.barbatus.common.string.StringValue;

public class StringPair extends StringValue {

    private String key, value;

    public StringPair(String key, String value) {
        super(value);
        this.key = key;
        this.value = value;
    }

    public StringPair() {
        super("");
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
