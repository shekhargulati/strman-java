package strman;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * A String manipulation library without any dependencies
 */
public abstract class Strman {

    private static final Predicate<String> NULL_STRING_PREDICATE = str -> str == null;
    private static final Supplier<String> NULL_STRING_MSG_SUPPLIER = () -> "'value' should be not null.";

    /**
     * Appends Strings to value
     *
     * @param value   initial String
     * @param appends an array of strings to append
     * @return full String
     */
    public static String append(final String value, final String... appends) {
        return appendArray(value, appends);
    }

    /**
     * Append an array of String to value
     *
     * @param value   initial String
     * @param appends an array of strings to append
     * @return full String
     */
    public static String appendArray(final String value, final String[] appends) {
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
    public static Optional<String> at(final String value, int index) {
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

    public static String[] between(final String value, final String start, final String end) {
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
    public static String[] chars(final String value) {
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
    public static String collapseWhitespace(final String value) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return value.trim().replaceAll("\\s\\s+", " ");
    }


    public static void validate(String value, Predicate<String> predicate, final Supplier<String> supplier) {
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
    public static boolean contains(final String value, final String needle) {
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
    public static boolean contains(final String value, final String needle, final boolean caseSensitive) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        if (caseSensitive) {
            return value.contains(needle);
        }
        return value.toLowerCase().contains(needle.toLowerCase());
    }

    /**
     * Verifies that all needles are contained in value. The search is case insensitive
     *
     * @param value   input String to search
     * @param needles needles to find
     * @return true if all needles are found else false.
     */
    public static boolean containsAll(final String value, final String[] needles) {
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
    public static boolean containsAll(final String value, final String[] needles, final boolean caseSensitive) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return Arrays.stream(needles).allMatch(needle -> contains(value, needle, caseSensitive));
    }

    /**
     * Verifies that one or more of needles are contained in value. This is case insensitive
     *
     * @param value   input
     * @param needles needles to search
     * @return boolean true if any needle is found else false
     */
    public static boolean containsAny(final String value, final String[] needles) {
        return containsAny(value, needles, false);
    }

    /**
     * Verifies that one or more of needles are contained in value.
     *
     * @param value         input
     * @param needles       needles to search
     * @param caseSensitive true or false
     * @return boolean true if any needle is found else false
     */
    public static boolean containsAny(final String value, final String[] needles, final boolean caseSensitive) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return Arrays.stream(needles).anyMatch(needle -> contains(value, needle, caseSensitive));
    }

    /**
     * Count the number of times substr appears in value
     *
     * @param value  input
     * @param subStr to search
     * @return count of times substring exists
     */
    public static long countSubstr(final String value, final String subStr) {
        return countSubstr(value, subStr, false, false);
    }

    /**
     * Count the number of times substr appears in value
     *
     * @param value            input
     * @param subStr           search string
     * @param caseSensitive    whether search should be case sensitive
     * @param allowOverlapping boolean to take into account overlapping
     * @return count of times substring exists
     */
    public static long countSubstr(final String value, final String subStr, final boolean caseSensitive, boolean allowOverlapping) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return countSubstr(caseSensitive ? value : value.toLowerCase(), caseSensitive ? subStr : subStr.toLowerCase(), allowOverlapping, 0L);
    }

    /**
     * Test if value ends with search. The search is case sensitive.
     *
     * @param value  input string
     * @param search string to search
     * @return true or false
     */
    public static boolean endsWith(final String value, final String search) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return endsWith(value, search, value.length(), true);
    }

    /**
     * Test if value ends with search.
     *
     * @param value         input string
     * @param search        string to search
     * @param caseSensitive true or false
     * @return true or false
     */
    public static boolean endsWith(final String value, final String search, final boolean caseSensitive) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return endsWith(value, search, value.length(), caseSensitive);
    }

    /**
     * Test if value ends with search.
     *
     * @param value         input string
     * @param search        string to search
     * @param position      position till which you want to search.
     * @param caseSensitive true or false
     * @return true or false
     */
    public static boolean endsWith(final String value, final String search, final int position, final boolean caseSensitive) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        int remainingLength = position - search.length();
        if (caseSensitive) {
            return value.indexOf(search, remainingLength) > -1;
        }
        return value.toLowerCase().indexOf(search.toLowerCase(), remainingLength) > -1;
    }

    /**
     * Ensures that the value begins with prefix. If it doesn't exist, it's prepended. It is case sensitive.
     *
     * @param value  input
     * @param prefix prefix
     * @return string with prefix if it was not present.
     */
    public static String ensureLeft(final String value, final String prefix) {
        return ensureLeft(value, prefix, true);
    }

    /**
     * Ensures that the value begins with prefix. If it doesn't exist, it's prepended.
     *
     * @param value         input
     * @param prefix        prefix
     * @param caseSensitive true or false
     * @return string with prefix if it was not present.
     */
    public static String ensureLeft(final String value, final String prefix, final boolean caseSensitive) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        if (caseSensitive) {
            return value.startsWith(prefix) ? value : prefix + value;
        }
        String _value = value.toLowerCase();
        String _prefix = prefix.toLowerCase();
        return _value.startsWith(_prefix) ? value : prefix + value;
    }

    /**
     * Decodes data encoded with MIME base64
     *
     * @param value The data to decode
     * @return decoded data
     */
    public static String base64Decode(final String value) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return new String(Base64.getDecoder().decode(value));
    }

    /**
     * Encodes data with MIME base64.
     *
     * @param value The data to encode
     * @return The encoded String
     */
    public static String base64Encode(final String value) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    /**
     * Convert binary unicode (16 digits) string to string chars
     *
     * @param value The value to decode
     * @return The decoded String
     */
    public static String binDecode(final String value) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return Arrays
                .stream(value.split("(?<=\\G.{16})"))
                .map(data -> String.valueOf(Character.toChars(Integer.parseInt(data, 2))))
                .collect(joining());
    }

    /**
     * Convert string chars to binary unicode (16 digits)
     *
     * @param value The value to encode
     * @return String in binary format
     */
    public static String binEncode(final String value) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return value.chars().mapToObj(ch -> leftPad(Integer.toBinaryString(ch), "0", 16)).collect(joining());
    }

    /**
     * Convert decimal unicode (5 digits) string to string chars
     *
     * @param value The value to decode
     * @return decoded String
     */
    public static String decDecode(final String value) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return Arrays
                .stream(value.split("(?<=\\G.{5})"))
                .map(data -> String.valueOf(Character.toChars(Integer.parseInt(data))))
                .collect(joining());
    }

    /**
     * Convert string chars to decimal unicode (5 digits)
     *
     * @param value The value to encode
     * @return Encoded value
     */
    public static String decEncode(final String value) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return value.chars().mapToObj(ch -> leftPad(Integer.toString(ch), "0", 5)).collect(joining());
    }

    /**
     * Ensures that the value ends with suffix. If it doesn't, it's appended. This operation is case sensitive.
     *
     * @param value  The input String
     * @param suffix The substr to be ensured to be right
     * @return The string which is guarenteed to start with substr
     */
    public static String ensureRight(final String value, final String suffix) {
        return ensureRight(value, suffix, true);
    }

    /**
     * Ensures that the value ends with suffix. If it doesn't, it's appended.
     *
     * @param value         The input String
     * @param suffix        The substr to be ensured to be right
     * @param caseSensitive Use case (in-)sensitive matching for determining if value already ends with suffix
     * @return The string which is guarenteed to start with substr
     */
    public static String ensureRight(final String value, final String suffix, boolean caseSensitive) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return endsWith(value, suffix, caseSensitive) ? value : append(value, suffix);
    }

    /**
     * Returns the first n chars of String
     *
     * @param value The input String
     * @param n     Number of chars to return
     * @return The first n chars
     */
    public static String first(final String value, final int n) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        return value.substring(0, n);
    }

    /**
     * Return the first char of String
     *
     * @param value The input String
     * @return The first char
     */
    public static String head(final String value) {
        return first(value, 1);
    }

    /**
     * Formats a string using parameters
     *
     * @param value  The value to be formatted
     * @param params Parameters to be described in the string
     * @return The formatted string
     */
    public static String format(final String value, String... params) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        Pattern p = Pattern.compile("\\{(\\w+)\\}");
        Matcher m = p.matcher(value);
        String result = value;
        while (m.find()) {
            int paramNumber = Integer.parseInt(m.group(1));
            if (params == null || paramNumber >= params.length) {
                throw new IllegalArgumentException("params does not have value for " + m.group());
            }
            result = result.replace(m.group(), params[paramNumber]);
        }
        return result;
    }

    public static String leftPad(final String value, final String pad, final int length) {
        validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
        if (value.length() >= length) {
            return value;
        }
        return append(Stream.generate(() -> pad).limit(length - value.length()).collect(joining()), value);
    }

    private static long countSubstr(String value, String subStr, boolean allowOverlapping, long count) {
        int position = value.indexOf(subStr);
        if (position == -1) {
            return count;
        }
        int offset;
        if (!allowOverlapping) {
            offset = position + subStr.length();
        } else {
            offset = position + 1;
        }
        return countSubstr(value.substring(offset), subStr, allowOverlapping, ++count);
    }


}
