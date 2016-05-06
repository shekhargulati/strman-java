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
    Supplier<String> NULL_STRING_MSG_SUPPLIER = () -> "'value' should be not null.";

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
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
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
     * @param value input
     * @param start start
     * @param end   end
     * @return Array containing different parts between start and end.
     */

    static String[] between(final String value, final String start, final String end) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
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
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
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
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return value.trim().replaceAll("\\s\\s+", " ");
    }


    static void validate(String value, Predicate<String> predicate, final Supplier<String> supplier) {
        if (predicate.test(value)) {
            throw new IllegalArgumentException(supplier.get());
        }
    }

    /**
     * Verifies that the needle is contained in the value. The search is case insensitive
     *
     * @param value  to search
     * @param needle to find
     * @return true if found else false.
     */
    static boolean contains(final String value, final String needle) {
        return contains(value, needle, false);
    }

    /**
     * Verifies that the needle is contained in the value.
     *
     * @param value         to search
     * @param needle        to find
     * @param caseSensitive true or false
     * @return true if found else false.
     */
    static boolean contains(final String value, final String needle, final boolean caseSensitive) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        if (caseSensitive) {
            return value.indexOf(needle) > -1;
        }
        return value.toLowerCase().indexOf(needle.toLowerCase()) > -1;
    }

    /**
     * Verifies that all needles are contained in value. The search is case insensitive
     *
     * @param value   input String to search
     * @param needles needles to find
     * @return true if all needles are found else false.
     */
    static boolean containsAll(final String value, final String[] needles) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return Arrays.stream(needles).allMatch(needle -> contains(value, needle, false));
    }

    /**
     * Verifies that all needles are contained in value
     *
     * @param value         input String to search
     * @param needles       needles to find
     * @param caseSensitive true or false
     * @return true if all needles are found else false.
     */
    static boolean containsAll(final String value, final String[] needles, final boolean caseSensitive) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return Arrays.stream(needles).allMatch(needle -> contains(value, needle, caseSensitive));
    }

    static boolean containsAny(final String value, final String[] needles) {
        return containsAny(value, needles, false);
    }

    static boolean containsAny(final String value, final String[] needles, final boolean caseSensitive) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return Arrays.stream(needles).anyMatch(needle -> contains(value, needle, caseSensitive));
    }
}
