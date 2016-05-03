package strman;

import java.util.Arrays;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Supplier;

/**
 * A String manipulation library without any dependencies
 */
public interface Strman {


    /**
     * Appends Strings to value
     *
     * @param value   initial String
     * @param appends an array of strings to append
     * @return full String
     */
    static String append(final String value, final String... appends) throws IllegalArgumentException {
        return appendArray(value, appends);
    }

    /**
     * Append an array of String to value
     *
     * @param value   initial String
     * @param appends an array of strings to append
     * @return full String
     */
    static String appendArray(final String value, final String[] appends) {
        validate(value, () -> "'value' should be not null.");
        if (appends == null || appends.length == 0) {
            return value;
        }
        StringJoiner joiner = new StringJoiner("");
        for (String append : appends) {
            joiner.add(append);
        }
        return value + joiner.toString();
    }

    /**
     * Get the character at index. This method will take care of negative indexes.
     * The valid value of index is between -(length-1) to (length-1).
     * For values which don't fall under this range Optional.empty will be returned.
     *
     * @param value input value
     * @param index location
     * @return an Optional String if found else empty
     */
    static Optional<String> at(final String value, int index) {
        if (value == null || value.isEmpty()) {
            return Optional.empty();
        }
        int length = value.length();
        if (index < 0) {
            index = length + index;
        }
        return (index < length && index >= 0) ? Optional.of(String.valueOf(value.charAt(index))) : Optional.empty();
    }

    /**
     * Returns an array with strings between start and end.
     *
     * @param value
     * @param start
     * @param end
     * @return Array containing different parts between start and end.
     */

    static String[] between(final String value, final String start, final String end) {
        validate(value, () -> "'value' should be not null.");
        validate(start, () -> "'start' should be not null.");
        validate(end, () -> "'end' should be not null.");

        String[] parts = value.split(end);
        return Arrays.stream(parts).map(subPart -> subPart.substring(subPart.indexOf(start) + start.length())).toArray(String[]::new);
    }

    static void validate(String value, Supplier<String> supplier) {
        if (value == null) {
            throw new IllegalArgumentException(supplier.get());
        }
    }
}
