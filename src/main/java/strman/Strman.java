package strman;

import java.util.Arrays;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A String manipulation library without any dependencies
 */
public interface Strman {

    Predicate<String> NULL_STRING_PREDICATE = str -> str == null;
    Predicate<String> EMPTY_STRING_PREDICATE = str -> str.length() == 0;
    Predicate<String> NULL_AND_EMPTY_STRING_PREDICATE = NULL_STRING_PREDICATE.and(EMPTY_STRING_PREDICATE);
    String EMPTY_STRING = " ";

    /**
     * Appends Strings to value
     *
     * @param value   initial String
     * @param appends an array of strings to append
     * @return full String
     */
    static String append(final String value, final String... appends) {
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
        validate(value, NULL_STRING_PREDICATE, () -> "'value' should be not null.");
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
        validate(value, NULL_STRING_PREDICATE, () -> "'value' should be not null.");
        validate(start, NULL_STRING_PREDICATE, () -> "'start' should be not null.");
        validate(end, NULL_STRING_PREDICATE, () -> "'end' should be not null.");

        String[] parts = value.split(end);
        return Arrays.stream(parts).map(subPart -> subPart.substring(subPart.indexOf(start) + start.length())).toArray(String[]::new);
    }

    /**
     * Returns a String array consisting of the characters in the String.
     *
     * @param value input
     * @return character array
     */
    static String[] chars(final String value) {
        /**
         * The other implementation of this could be using String's split method
         * String[] chars = value.split("")
         */
        validate(value, NULL_STRING_PREDICATE, () -> "'value' should be not null.");
        int length = value.length();
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = at(value, i).get();
        }
        return result;
    }


    /**
     * Replace consecutive whitespace characters with a single space.
     *
     * @param value input String
     * @return collapsed String
     */
    static String collapseWhitespace(final String value) {
        validate(value, NULL_STRING_PREDICATE, () -> "'value' should be not null.");
        return value.trim().replaceAll("\\s\\s+", " ");
    }

    static void validate(String value, Predicate<String> predicate, final Supplier<String> supplier) {
        if (predicate.test(value)) {
            throw new IllegalArgumentException(supplier.get());
        }
    }
}
