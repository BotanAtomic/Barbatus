package org.barbatus.common.pair;

public class StringPair {

    private String key, value;

    public StringPair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public StringPair() {
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public byte getByteValue() {
        return Byte.parseByte(this.value);
    }

    public short getShortValue() {
        return Short.parseShort(this.value);
    }

    public int getIntValue() {
        return Integer.parseInt(this.value);
    }

    public long getLongValue() {
        return Long.parseLong(this.value);
    }

    public float getFloatValue() {
        return Float.parseFloat(this.value);
    }

    public double getDoubleValue() {
        return Double.parseDouble(this.value);
    }

    public byte[] getBinaryValue() {
        return this.value.getBytes();
    }

}
