package org.barbatus.common.string;

public class StringValue {

    private String value;

    public StringValue(String value) {
        this.value = value;
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
