package strman;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.junit.Assert.*;
import static strman.Strman.*;

public class StrmanTest {

    @Test
    public void append_shouldAppendStringsToEndOfValue() throws Exception {
        assertThat(append("f", "o", "o", "b", "a", "r"), equalTo("foobar"));
        assertThat(append("foobar"), equalTo("foobar"));
        assertThat(append("", "foobar"), equalTo("foobar"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void append_shouldThrowIllegalArgumentExceptionWhenValueIsNull() throws Exception {
        append(null);
    }

    @Test
    public void appendArray_shouldAppendStringArrayToEndOfValue() throws Exception {
        assertThat(appendArray("f", new String[]{"o", "o", "b", "a", "r"}), equalTo("foobar"));
        assertThat(appendArray("foobar", new String[]{}), equalTo("foobar"));
        assertThat(appendArray("", new String[]{"foobar"}), equalTo("foobar"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void appendArray_ShouldThrowIllegalArgumentExceptionWhenValueIsNull() throws Exception {
        appendArray(null, new String[]{});
    }

    @Test
    public void at_shouldFindCharacterAtIndex() throws Exception {
        assertThat(at("foobar", 0), equalTo(Optional.of("f")));
        assertThat(at("foobar", 1), equalTo(Optional.of("o")));
        assertThat(at("foobar", -1), equalTo(Optional.of("r")));
        assertThat(at("foobar", -2), equalTo(Optional.of("a")));
        assertThat(at("foobar", 10), equalTo(Optional.empty()));
        assertThat(at("foobar", -10), equalTo(Optional.empty()));
    }

    @Test
    public void between_shouldReturnArrayWithStringsBetweenStartAndEnd() throws Exception {
        assertThat(between("[abc][def]", "[", "]"), arrayContaining("abc", "def"));
        assertThat(between("<span>foo</span>", "<span>", "</span>"), arrayContaining("foo"));
        assertThat(between("<span>foo</span><span>bar</span>", "<span>", "</span>"), arrayContaining("foo", "bar"));
    }

    @Test
    public void between_shouldReturnFullStringWhenStartAndEndDoesNotExist() throws Exception {
        assertThat(between("[abc][def]", "{", "}"), arrayContaining("[abc][def]"));
        assertThat(between("", "{", "}"), arrayContaining(""));
    }

    @Test
    public void chars_shouldReturnAllCharactersInString() throws Exception {
        final String title = "title";
        assertThat(chars(title), equalTo(new String[]{"t", "i", "t", "l", "e"}));
    }

    @Test
    public void collapseWhitespace_shouldReplaceConsecutiveWhitespaceWithSingleSpace() throws Exception {
        String[] fixture = {
                "foo    bar",
                "     foo     bar    ",
                " foo     bar   ",
                "    foo     bar "
        };
        Arrays.stream(fixture).forEach(el -> assertThat(collapseWhitespace(el), equalTo("foo bar")));
    }

    @Test
    public void collapseWhitespace_shouldReplaceConsecutiveWhitespaceBetweenMultipleStrings() throws Exception {
        String input = " foo      bar      bazz     hello    world    ";
        assertThat(collapseWhitespace(input), equalTo("foo bar bazz hello world"));
    }

    @Test
    public void containsWithCaseSensitiveFalse_shouldReturnTrueWhenStringContainsNeedle() throws Exception {
        String[] fixture = {
                "foo bar",
                "bar foo",
                "foobar",
                "foo"
        };

        Arrays.stream(fixture).forEach(el -> assertTrue(contains(el, "FOO")));
    }

    @Test
    public void containsWithCaseSensitiveTrue_shouldReturnTrueWhenStringContainsNeedle() throws Exception {
        String[] fixture = {
                "foo bar",
                "bar foo",
                "foobar",
                "foo"
        };

        Arrays.stream(fixture).forEach(el -> assertFalse(contains(el, "FOO", true)));
    }

    @Test
    public void containsAll_shouldReturnTrueOnlyWhenAllNeedlesAreContainedInValue() throws Exception {
        String[] fixture = {
                "foo bar",
                "bar foo",
                "foobar",
        };

        Arrays.stream(fixture).forEach(el -> assertTrue(containsAll(el, new String[]{"FOO", "bar"})));
    }

    @Test
    public void containsAll_shouldReturnFalseOnlyWhenAllNeedlesAreNotContainedInValue() throws Exception {
        String[] fixture = {
                "foo bar",
                "bar foo",
                "foobar",
        };
        Arrays.stream(fixture).forEach(el -> assertFalse(containsAll(el, new String[]{"FOO", "bar"}, true)));
    }

    @Test
    public void containsAny_shouldReturnTrueWhenAnyOfSearchNeedleExistInInputValue() throws Exception {
        String[] fixture = {
                "foo bar",
                "bar foo",
                "foobar",
        };
        Arrays.stream(fixture).forEach(el -> assertTrue(containsAny(el, new String[]{"foo", "bar", "test"})));
    }

    @Test
    public void containsAny_shouldReturnFalseWhenNoneOfSearchNeedleExistInInputValue() throws Exception {
        String[] fixture = {
                "foo bar",
                "bar foo",
                "foobar",
        };
        Arrays.stream(fixture).forEach(el -> assertFalse(containsAny(el, new String[]{"FOO", "BAR", "Test"}, true)));
    }

    @Test
    public void countSubstr_shouldCountSubStrCountCaseInsensitiveWithoutOverlapInValue() throws Exception {
        assertThat(countSubstr("aaaAAAaaa", "aaa"), equalTo(3L));
    }

    @Test
    public void countSubstr_shouldCountSubStrCountCaseSensitiveWithoutOverlapInValue() throws Exception {
        assertThat(countSubstr("aaaAAAaaa", "aaa", true, false), equalTo(2L));
    }

    @Test
    public void countSubstr_shouldCountSubStrCountCaseInsensitiveWithOverlapInValue() throws Exception {
        assertThat(countSubstr("aaaAAAaaa", "aaa", false, true), equalTo(7L));
    }

    @Test
    public void countSubstr_shouldCountSubStrCountCaseSensitiveWithOverlapInValue() throws Exception {
        assertThat(countSubstr("aaaAAAaaa", "AAA", true, true), equalTo(1L));
    }

    @Test
    public void countSubstrTestFixture_caseSensitiveTrueAndOverlappingFalse() throws Exception {
        String[] fixture = {
                "aaaaaAaaAA",
                "faaaAAaaaaAA",
                "aaAAaaaaafA",
                "AAaaafaaaaAAAA"
        };
        Arrays.stream(fixture).forEach(el -> assertThat(countSubstr(el, "a", true, false), equalTo(7L)));
    }

    @Test
    public void countSubstrTestFixture_caseSensitiveFalseAndOverlappingFalse() throws Exception {
        String[] fixture = {
                "aaaaaaa",
                "faaaaaaa",
                "aaaaaaaf",
                "aaafaaaa"
        };
        Arrays.stream(fixture).forEach(el -> assertThat(countSubstr(el, "A"), equalTo(7L)));
    }

    @Test
    public void countSubstrTestFixture_caseSensitiveTrueAndOverlappingTrue() throws Exception {
        assertThat(countSubstr("aaa", "aa", true, true), equalTo(2L));
    }

    @Test
    public void endsWith_caseSensitive_ShouldBeTrueWhenStringEndsWithSearchStr() throws Exception {
        String[] fixture = {
                "foo bar",
                "bar"
        };
        Arrays.stream(fixture).forEach(el -> assertTrue(endsWith(el, "bar")));
    }

    @Test
    public void endsWith_notCaseSensitive_ShouldBeTrueWhenStringEndsWithSearchStr() throws Exception {
        String[] fixture = {
                "foo bar",
                "bar"
        };
        Arrays.stream(fixture).forEach(el -> assertTrue(endsWith(el, "BAR", false)));
    }

    @Test
    public void endsWith_caseSensitiveAtPosition_ShouldBeTrueWhenStringEndsWithSearchStr() throws Exception {
        String[] fixture = {
                "foo barr",
                "barr"
        };
        Arrays.stream(fixture).forEach(el -> assertTrue(endsWith(el, "bar", el.length() - 1, true)));
    }

    @Test
    public void endsWith_notCaseSensitiveAtPosition_ShouldBeTrueWhenStringEndsWithSearchStr() throws Exception {
        String[] fixture = {
                "foo barr",
                "barr"
        };
        Arrays.stream(fixture).forEach(el -> assertTrue(endsWith(el, "BAR", el.length() - 1, false)));
    }

    @Test
    public void ensureLeft_shouldEnsureValueStartsWithFoo() throws Exception {
        String[] fixture = {
                "foobar",
                "bar"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(ensureLeft(el, "foo"), equalTo("foobar")));
    }

    @Test
    public void ensureLeft_notCaseSensitive_shouldEnsureValueStartsWithFoo() throws Exception {
        assertThat(ensureLeft("foobar", "FOO", false), equalTo("foobar"));
        assertThat(ensureLeft("bar", "FOO", false), equalTo("FOObar"));
    }

    @Test
    public void base64Decode_shouldDecodeABase64DecodedValueToString() throws Exception {
        assertThat(base64Decode("c3RybWFu"), equalTo("strman"));
        assertThat(base64Decode("Zm9v"), equalTo("foo"));
        assertThat(base64Decode("YmFy"), equalTo("bar"));
        assertThat(base64Decode("YsOhciE="), equalTo("bár!"));
        assertThat(base64Decode("5ryi"), equalTo("漢"));
    }

    @Test
    public void base64Encode_shouldEncodeAString() throws Exception {
        assertThat(base64Encode("strman"), equalTo("c3RybWFu"));
        assertThat(base64Encode("foo"), equalTo("Zm9v"));
        assertThat(base64Encode("bar"), equalTo("YmFy"));
        assertThat(base64Encode("bár!"), equalTo("YsOhciE="));
        assertThat(base64Encode("漢"), equalTo("5ryi"));
    }

    @Test
    public void binDecode_shouldDecodeABinaryStringToAValue() throws Exception {
        assertThat(
                binDecode("000000000111001100000000011101000000000001110010000000000110110100000000011000010000000001101110"),
                equalTo("strman"));

        assertThat(binDecode("0110111100100010"), equalTo("漢"));
        assertThat(binDecode("0000000001000001"), equalTo("A"));
        assertThat(binDecode("0000000011000001"), equalTo("Á"));
        assertThat(binDecode("00000000010000010000000001000001"), equalTo("AA"));
    }

    @Test
    public void binEncode_shouldEncodeAStringToBinaryFormat() throws Exception {
        assertThat(binEncode("漢"), equalTo("0110111100100010"));
        assertThat(binEncode("A"), equalTo("0000000001000001"));
        assertThat(binEncode("Á"), equalTo("0000000011000001"));
        assertThat(binEncode("AA"), equalTo("00000000010000010000000001000001"));
    }
}