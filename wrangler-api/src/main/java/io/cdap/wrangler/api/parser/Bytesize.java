package io.cdap.wrangler.api.parser;

public class ByteSize implements Token {
    private final double value;
    private final long bytes;

    public ByteSize(String value) {
        super(value);
        this.value = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
        if (value.toLowerCase().endsWith("kb")) {
            bytes = (long) (this.value * 1024);
        } else if (value.toLowerCase().endsWith("mb")) {
            bytes = (long) (this.value * 1024 * 1024);
        } else if (value.toLowerCase().endsWith("gb")) {
            bytes = (long) (this.value * 1024 * 1024 * 1024);
        } else {
            bytes = (long) this.value;
        }
    }

    public long getBytes() {
        return bytes;
    }
}
