package io.cdap.wrangler.api.parser;

public class TimeDuration implements Token {
    private final double value;
    private final long milliseconds;

    public TimeDuration(String value) {
        super(value);
        this.value = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
        if (value.endsWith("ms")) {
            milliseconds = (long) (this.value);
        } else if (value.contains("s")) {
            milliseconds = (long) (this.value * 1000);
        } else if (value.contains("m")) {
            milliseconds = (long) (this.value * 60 * 1000);
        } else {
            milliseconds = (long) this.value;
        }
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}
