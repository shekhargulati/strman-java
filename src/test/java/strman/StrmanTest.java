/*
 *
 *  * The MIT License
 *  *
 *  * Copyright 2016 Shekhar Gulati <shekhargulati84@gmail.com>.
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in
 *  * all copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  * THE SOFTWARE.
 *
 */

package strman;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.hamcrest.collection.IsArrayWithSize.emptyArray;
import static org.junit.Assert.*;
import static strman.Strman.*;
import static strman.Strman.endsWith;
import static strman.Strman.format;

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

        Arrays.stream(fixture).forEach(el -> assertTrue(containsAll(el, new String[]{"foo", "bar"})));
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
        assertThat(countSubstr("aaaAAAaaa", "aaa", false, false), equalTo(3L));
    }

    @Test
    public void countSubstr_shouldCountSubStrCountCaseSensitiveWithoutOverlapInValue() throws Exception {
        assertThat(countSubstr("aaaAAAaaa", "aaa"), equalTo(2L));
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
        Arrays.stream(fixture).forEach(el -> assertThat(countSubstr(el, "A", false, false), equalTo(7L)));
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

    @Test
    public void decDecode_shouldDecodeDecimalStringToString() throws Exception {
        assertThat(decDecode("28450"), equalTo("漢"));
        assertThat(decDecode("00065"), equalTo("A"));
        assertThat(decDecode("00193"), equalTo("Á"));
        assertThat(decDecode("0006500065"), equalTo("AA"));
    }

    @Test
    public void decEncode_shouldEncodeStringToDecimal() throws Exception {
        assertThat(decEncode("漢"), equalTo("28450"));
        assertThat(decEncode("A"), equalTo("00065"));
        assertThat(decEncode("Á"), equalTo("00193"));
        assertThat(decEncode("AA"), equalTo("0006500065"));
    }

    @Test
    public void ensureRight_shouldEnsureStringEndsWithBar() throws Exception {
        final String[] fixture = {
                "foo", "foobar", "fooBAR"
        };
        assertThat(Arrays.stream(fixture).map(el -> ensureRight(el, "bar", false)).collect(toList()), hasItems("foobar", "foobar", "fooBAR"));
        assertThat(Arrays.stream(fixture).map(el -> ensureRight(el, "bar")).collect(toList()), hasItems("foobar", "foobar", "fooBARbar"));
    }

    @Test
    public void first_shouldReturnFirstThreeCharsOfString() throws Exception {
        final String[] fixture = {
                "foo", "foobar"
        };
        Arrays.stream(fixture).forEach(el -> assertThat(first(el, 3), equalTo("foo")));
    }

    @Test
    public void head_shouldReturnFirstCharOfString() throws Exception {
        final String[] fixture = {
                "foo", "foobar"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(head(el), equalTo("f")));
    }

    @Test
    public void format_shouldFormatStringsToFooBar() throws Exception {
        assertThat(format("{0} bar", "foo"), equalTo("foo bar"));
        assertThat(format("foo {0}", "bar"), equalTo("foo bar"));
        assertThat(format("foo {0}", "bar", "foo"), equalTo("foo bar"));
        assertThat(format("{0} {1}", "foo", "bar"), equalTo("foo bar"));
        assertThat(format("{1} {0}", "bar", "foo"), equalTo("foo bar"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void format_shouldThrowExceptionWhenValueDoesNotExist() throws Exception {
        assertThat(format("{1} {0}"), equalTo("{1} {0}"));
    }

    @Test
    public void hexDecode_shouldDecodeHexCodeToString() throws Exception {
        assertThat(hexDecode("6f22"), equalTo("漢"));
        assertThat(hexDecode("0041"), equalTo("A"));
        assertThat(hexDecode("00c1"), equalTo("Á"));
        assertThat(hexDecode("00410041"), equalTo("AA"));
    }

    @Test
    public void hexEncode_shouldEncodeStringToHexadecimalFormat() throws Exception {
        assertThat(hexEncode("漢"), equalTo("6f22"));
        assertThat(hexEncode("A"), equalTo("0041"));
        assertThat(hexEncode("Á"), equalTo("00c1"));
        assertThat(hexEncode("AA"), equalTo("00410041"));
    }

    @Test
    public void indexOf_shouldBeTrueWhenNeedleExists() throws Exception {
        final String value = "foobar";
        assertThat(indexOf(value, "f", 0, true), equalTo(0));
        assertThat(indexOf(value, "o", 0, true), equalTo(1));
        assertThat(indexOf(value, "b", 0, true), equalTo(3));
        assertThat(indexOf(value, "a", 0, true), equalTo(4));
        assertThat(indexOf(value, "r", 0, true), equalTo(5));
        assertThat(indexOf(value, "t", 0, true), equalTo(-1));
    }

    @Test
    public void indexOf_shouldBeTrueWhenNeedleExistCaseSensitive() throws Exception {
        final String value = "foobar";
        assertThat(indexOf(value, "F", 0, false), equalTo(0));
        assertThat(indexOf(value, "O", 0, false), equalTo(1));
        assertThat(indexOf(value, "B", 0, false), equalTo(3));
        assertThat(indexOf(value, "A", 0, false), equalTo(4));
        assertThat(indexOf(value, "R", 0, false), equalTo(5));
        assertThat(indexOf(value, "T", 0, false), equalTo(-1));
    }

    @Test
    public void inequal_shouldTestInequalityOfStrings() throws Exception {
        assertThat(inequal("a", "b"), equalTo(true));
        assertThat(inequal("a", "a"), equalTo(false));
        assertThat(inequal("0", "1"), equalTo(true));
    }

    @Test
    public void insert_shouldInsertStringAtIndex() throws Exception {
        assertThat(insert("fbar", "oo", 1), equalTo("foobar"));
        assertThat(insert("foo", "bar", 3), equalTo("foobar"));
        assertThat(insert("foobar", "x", 5), equalTo("foobaxr"));
        assertThat(insert("foobar", "x", 6), equalTo("foobarx"));
        assertThat(insert("foo bar", "asadasd", 100), equalTo("foo bar"));
    }

    @Test
    public void isLowerCase_shouldBeTrueWhenStringIsLowerCase() throws Exception {
        assertThat(isLowerCase(""), equalTo(true));
        assertThat(isLowerCase("foo"), equalTo(true));
        assertThat(isLowerCase("foobarfoo"), equalTo(true));
    }

    @Test
    public void isLowerCase_shouldBeFalseWhenStringIsNotLowerCase() throws Exception {
        assertThat(isLowerCase("Foo"), equalTo(false));
        assertThat(isLowerCase("foobarfooA"), equalTo(false));
    }

    @Test
    public void isUpperCase_shouldBeTrueWhenStringIsUpperCase() throws Exception {
        assertThat(isUpperCase(""), equalTo(true));
        assertThat(isUpperCase("FOO"), equalTo(true));
        assertThat(isUpperCase("FOOBARFOO"), equalTo(true));
    }

    @Test
    public void isUpperCase_shouldBeFalseWhenStringIsNotUpperCase() throws Exception {
        assertThat(isUpperCase("Foo"), equalTo(false));
        assertThat(isUpperCase("foobarfooA"), equalTo(false));
    }

    @Test
    public void last_shouldReturnLastNChars() throws Exception {
        assertThat(last("foo", 3), equalTo("foo"));
        assertThat(last("foobarfoo", 3), equalTo("foo"));
        assertThat(last("", 3), equalTo(""));
        assertThat(last("f", 3), equalTo("f"));
    }

    @Test
    public void leftPad_shouldAddPaddingOnTheLeft() throws Exception {
        assertThat(leftPad("1", "0", 5), equalTo("00001"));
        assertThat(leftPad("01", "0", 5), equalTo("00001"));
        assertThat(leftPad("001", "0", 5), equalTo("00001"));
        assertThat(leftPad("0001", "0", 5), equalTo("00001"));
        assertThat(leftPad("00001", "0", 5), equalTo("00001"));
    }

    @Test
    public void isString_shouldBeFalseWhenValueIsNotString() throws Exception {
        assertFalse(isString(1));
        assertFalse(isString(false));
        assertFalse(isString(1.2));
        assertFalse(isString(new String[]{}));
    }

    @Test
    public void isString_shouldBeTrueWhenValueIsString() throws Exception {
        assertTrue(isString("string"));
        assertTrue(isString(""));
    }

    @Test
    public void lastIndexOf_shouldFindIndexOfNeedle() throws Exception {
        final String value = "foobarfoobar";
        assertThat(lastIndexOf(value, "f"), equalTo(6));
        assertThat(lastIndexOf(value, "o"), equalTo(8));
        assertThat(lastIndexOf(value, "b"), equalTo(9));
        assertThat(lastIndexOf(value, "a"), equalTo(10));
        assertThat(lastIndexOf(value, "r"), equalTo(11));
        assertThat(lastIndexOf(value, "t"), equalTo(-1));
    }

    @Test
    public void lastIndexOf_shouldFindIndexOfNeedleCaseInsensitive() throws Exception {
        final String value = "foobarfoobar";
        assertThat(lastIndexOf(value, "F", false), equalTo(6));
        assertThat(lastIndexOf(value, "O", false), equalTo(8));
        assertThat(lastIndexOf(value, "B", false), equalTo(9));
        assertThat(lastIndexOf(value, "A", false), equalTo(10));
        assertThat(lastIndexOf(value, "R", false), equalTo(11));
        assertThat(lastIndexOf(value, "T", false), equalTo(-1));
    }

    @Test
    public void leftTrim_shouldRemoveSpacesOnLeft() throws Exception {
        assertThat(leftTrim("     strman"), equalTo("strman"));
        assertThat(leftTrim("     strman  "), equalTo("strman  "));
    }

    @Test
    public void prepend_shouldPrependStrings() throws Exception {
        assertThat(prepend("r", "f", "o", "o", "b", "a"), equalTo("foobar"));
        assertThat(prepend("foobar"), equalTo("foobar"));
        assertThat(prepend("", "foobar"), equalTo("foobar"));
        assertThat(prepend("bar", "foo"), equalTo("foobar"));
    }

    @Test
    public void prependArray_shouldPrependStrings() throws Exception {
        assertThat(prependArray("r", new String[]{"f", "o", "o", "b", "a"}), equalTo("foobar"));
        assertThat(prependArray("foobar", new String[0]), equalTo("foobar"));
        assertThat(prependArray("", new String[]{"foobar"}), equalTo("foobar"));
        assertThat(prependArray("bar", new String[]{"foo"}), equalTo("foobar"));
    }

    @Test
    public void removeEmptyStrings_shouldRemoveEmptyStrings() throws Exception {
        assertThat(removeEmptyStrings(new String[]{"aa", "", "   ", "bb", "cc", null}), arrayContaining("aa", "bb", "cc"));
        assertThat(removeEmptyStrings(new String[0]), emptyArray());
    }

    @Test
    public void removeLeft_shouldRemoveStringFromLeft() throws Exception {
        final String[] fixture = {
                "foobar",
                "bar"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(removeLeft(el, "foo"), equalTo("bar")));
        assertThat(removeLeft("barfoo", "foo"), equalTo("barfoo"));
        assertThat(removeLeft("foofoo", "foo"), equalTo("foo"));
    }

    @Test
    public void removeLeft_shouldRemoveStringFromLeftCaseInSensitive() throws Exception {
        final String[] fixture = {
                "foobar",
                "bar"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(removeLeft(el, "FOO", false), equalTo("bar")));
    }

    @Test
    public void removeNonWords_shouldRemoveAllNonWordsFromInputString() throws Exception {

        final String[] fixture = {
                "foo bar",
                "foo&bar-",
                "foobar"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(removeNonWords(el), equalTo("foobar")));
    }

    @Test
    public void removeRight_shouldRemoveStringFromRight() throws Exception {
        final String[] fixture = {
                "foobar",
                "foo"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(removeRight(el, "bar"), equalTo("foo")));
        assertThat(removeRight("barfoo", "bar"), equalTo("barfoo"));
        assertThat(removeRight("barbar", "bar"), equalTo("bar"));
    }

    @Test
    public void removeRight_shouldRemoveStringFromRightCaseInSensitive() throws Exception {
        final String[] fixture = {
                "foobar",
                "foo"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(removeRight(el, "BAR", false), equalTo("foo")));
    }

    @Test
    public void removeSpaces_shouldRemoveSpacesInTheString() throws Exception {
        final String[] fixture = {
                "foo bar",
                "foo bar ",
                " foo bar",
                " foo bar "
        };
        Arrays.stream(fixture).forEach(el -> assertThat(removeSpaces(el), equalTo("foobar")));
    }

    @Test
    public void repeat_shouldRepeatAStringNTimes() throws Exception {
        assertThat(repeat("1", 1), equalTo("1"));
        assertThat(repeat("1", 2), equalTo("11"));
        assertThat(repeat("1", 3), equalTo("111"));
        assertThat(repeat("1", 4), equalTo("1111"));
        assertThat(repeat("1", 5), equalTo("11111"));
    }

    @Test
    public void replace_shouldReplaceAllOccurrencesOfString() throws Exception {
        assertThat(replace("foo bar", "foo", "bar", true), equalTo("bar bar"));
        assertThat(replace("foo bar foo", "foo", "bar", true), equalTo("bar bar bar"));
    }

    @Test
    public void replace_shouldReplaceAllOccurrencesOfStringCaseSensitive() throws Exception {
        assertThat(replace("FOO bar", "foo", "bar", false), equalTo("bar bar"));
        assertThat(replace("FOO bar foo", "foo", "bar", false), equalTo("bar bar bar"));
    }

    @Test
    public void reverse_shouldReverseInputString() throws Exception {
        assertThat(reverse(""), equalTo(""));
        assertThat(reverse("foo"), equalTo("oof"));
        assertThat(reverse("shekhar"), equalTo("rahkehs"));
        assertThat(reverse("bar"), equalTo("rab"));
        assertThat(reverse("foo_"), equalTo("_oof"));
        assertThat(reverse("f"), equalTo("f"));
    }

    @Test
    public void rightPad_shouldRightPadAString() throws Exception {
        assertThat(rightPad("1", "0", 5), equalTo("10000"));
        assertThat(rightPad("10", "0", 5), equalTo("10000"));
        assertThat(rightPad("100", "0", 5), equalTo("10000"));
        assertThat(rightPad("1000", "0", 5), equalTo("10000"));
        assertThat(rightPad("10000", "0", 5), equalTo("10000"));
        assertThat(rightPad("10000000", "0", 5), equalTo("10000000"));
    }

    @Test
    public void rightTrim_shouldRemoveSpacesFromTheRight() throws Exception {
        assertThat(rightTrim("strman   "), equalTo("strman"));
        assertThat(rightTrim("   strman"), equalTo("   strman"));
        assertThat(rightTrim("strman"), equalTo("strman"));
    }

    @Test
    public void safeTruncate_shouldSafelyTruncateStrings() throws Exception {
        assertThat(safeTruncate("foo bar", 0, "."), equalTo(""));
        assertThat(safeTruncate("foo bar", 4, "."), equalTo("foo."));
        assertThat(safeTruncate("foo bar", 3, "."), equalTo("."));
        assertThat(safeTruncate("foo bar", 2, "."), equalTo("."));
        assertThat(safeTruncate("foo bar", 7, "."), equalTo("foo bar"));
        assertThat(safeTruncate("foo bar", 8, "."), equalTo("foo bar"));
        assertThat(safeTruncate("A Javascript string manipulation library.", 16, "..."), equalTo("A Javascript..."));
        assertThat(safeTruncate("A Javascript string manipulation library.", 15, "..."), equalTo("A Javascript..."));
        assertThat(safeTruncate("A Javascript string manipulation library.", 14, "..."), equalTo("A..."));
        assertThat(safeTruncate("A Javascript string manipulation library.", 13, "..."), equalTo("A..."));
    }

    @Test
    public void truncate_shouldTruncateString() throws Exception {
        assertThat(truncate("foo bar", 0, "."), equalTo(""));
        assertThat(truncate("foo bar", 3, "."), equalTo("fo."));
        assertThat(truncate("foo bar", 2, "."), equalTo("f."));
        assertThat(truncate("foo bar", 4, "."), equalTo("foo."));
        assertThat(truncate("foo bar", 7, "."), equalTo("foo bar"));
        assertThat(truncate("foo bar", 8, "."), equalTo("foo bar"));
        assertThat(truncate("A Javascript string manipulation library.", 16, "..."), equalTo("A Javascript ..."));
        assertThat(truncate("A Javascript string manipulation library.", 15, "..."), equalTo("A Javascript..."));
        assertThat(truncate("A Javascript string manipulation library.", 14, "..."), equalTo("A Javascrip..."));
    }

    @Test
    public void htmlDecode_shouldDecodeToHtml() throws Exception {
        assertThat(htmlDecode("&aacute;"), equalTo("\u00E1"));
        assertThat(htmlDecode("&SHcy;"), equalTo("Ш"));
        assertThat(htmlDecode("&ZHcy;"), equalTo("Ж"));
        assertThat(htmlDecode("&boxdl;"), equalTo("┐"));
    }

    @Test
    public void htmlEncode_shouldBeEncodedToHtmlEntities() throws Exception {
        assertThat(htmlEncode("á"), equalTo("&aacute;"));
        assertThat(htmlEncode("áéíóú"), equalTo("&aacute;&eacute;&iacute;&oacute;&uacute;"));
        assertThat(htmlEncode("Ш"), equalTo("&SHcy;"));
        assertThat(htmlEncode("Ж"), equalTo("&ZHcy;"));
        assertThat(htmlEncode("┐"), equalTo("&boxdl;"));
    }

    @Test
    public void shuffle_shouldShuffleAString() throws Exception {
        assertThat(shuffle("shekhar"), not(equalTo("shekhar")));
        assertThat(shuffle("strman"), not(equalTo("strman")));
        assertThat(shuffle(""), equalTo(""));
        assertThat(shuffle("s"), equalTo("s"));
    }

    @Test
    public void slugify_shouldBeFooBar() throws Exception {
        String[] fixture = {
                "foo bar",
                "foo bar.",
                "foo bar ",
                " foo bar",
                " foo bar ",
                "foo------bar",
                "fóõ bár",
                "foo ! bar",
                "foo ~~ bar",
                "foo     bar",
                "FOO     bar"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(String.format("slugify(%s) should be foo-bar ", el), slugify(el), equalTo("foo-bar")));
    }

    @Test
    public void slugify_shouldBeFooAndBar() throws Exception {
        String[] fixture = {
                "foo&bar",
                "foo&bar.",
                "foo&bar ",
                " foo&bar",
                " foo&bar ",
                "foo&bar",
                "fóõ-and---bár",
                "foo  &    bar",
                "FOO  &   bar"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(String.format("slugify(%s) should be foo-and-bar ", el), slugify(el), equalTo("foo-and-bar")));
    }

    @Test
    public void transliterate_shouldTransliterateTheText() throws Exception {
        assertThat(transliterate("fóõ bár"), equalTo("foo bar"));
    }

    @Test
    public void surround_shouldSurroundStringWithPrefixAndSuffix() throws Exception {
        assertThat(surround("foo", "bar", null), equalTo("barfoobar"));
        assertThat(surround("shekhar", "***", null), equalTo("***shekhar***"));
        assertThat(surround("", ">", null), equalTo(">>"));
        assertThat(surround("bar", "", null), equalTo("bar"));
        assertThat(surround("f", null, null), equalTo("f"));
        assertThat(surround("div", "<", ">"), equalTo("<div>"));
    }

    @Test
    public void toCamelCase_shouldConvertStringToCamelCase() throws Exception {
        String[] fixture = {
                "CamelCase",
                "camelCase",
                "Camel case",
                "Camel  case",
                "camel Case",
                "camel-case",
                "-camel--case",
                "camel_case",
                "     camel_case",
        };
        Arrays.stream(fixture).forEach(el -> assertThat(String.format("toCameCase(%s) should be camelCase", el), toCamelCase(el), equalTo("camelCase")));

        assertThat(toCamelCase("c"), equalTo("c"));
    }

    @Test
    public void toDeCamelCase_shouldDeCamelCaseAString() throws Exception {
        String[] fixture = {
                "deCamelize",
                "de-Camelize",
                "de camelize",
                "de  camelize",
                "de Camelize",
                "de-camelize",
                "-de--camelize",
                "de_camelize",
                "     de_camelize"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(String.format("toDecamelize(%s) should be de-camelize", el), toDecamelize(el, null), equalTo("de camelize")));

        assertThat(toDecamelize("camelCase", "_"), equalTo("camel_case"));
    }

    @Test
    public void toKebabCase_shouldKebabCaseAString() throws Exception {
        String[] fixture = {
                "deCamelize",
                "de-Camelize",
                "de camelize",
                "de  camelize",
                "de Camelize",
                "de-camelize",
                "-de--camelize",
                "de_camelize",
                "     de_camelize"
        };

        Arrays.stream(fixture).forEach(el ->
                assertThat(String.format("toKebabCase(%s) should be de-camelize", el), toKebabCase(el), equalTo("de-camelize")));
    }

    @Test
    public void toSnakeCase_shouldSnakeCaseAString() throws Exception {
        String[] fixture = {
                "deCamelize",
                "de-Camelize",
                "de camelize",
                "de  camelize",
                "de Camelize",
                "de-camelize",
                "-de--camelize",
                "de_camelize",
                "     de_camelize"
        };

        Arrays.stream(fixture).forEach(el -> assertThat(String.format("toSnakeCase(%s) should be de_camelize", el), toSnakeCase(el), equalTo("de_camelize")));
    }

    @Test
    public void unequal_shouldTestInequalityOfStrings() throws Exception {
        assertThat(unequal("a", "b"), equalTo(true));
        assertThat(unequal("a", "a"), equalTo(false));
        assertThat(unequal("0", "1"), equalTo(true));
    }


    @Test
    public void removeLeft_shouldNotLowercaseWhenCaseInsensitive() throws Exception {
        String result = removeLeft("This HAS A THIS IN FRONT", "THIS ", false);
        assertThat(result, is("HAS A THIS IN FRONT"));
    }

    @Test
    public void replace_shouldNotLowercaseWhenCaseInsensitive() throws Exception {
        String result = replace("One and two and THREE and Four", "and", "&", false);
        assertThat(result, is("One & two & THREE & Four"));
    }

    @Test
    public void removeRight_shouldNotLowercaseWhenCaseInsensitive() throws Exception {
        String result = removeRight("Remove the END at the end", " END", false);
        assertThat(result, is("Remove the END at the"));
    }

    @Test
    public void transliterate_shouldDeburrTheString() throws Exception {
        String result = transliterate("déjà vu");
        assertThat(result, is(equalTo("deja vu")));
    }

    @Ignore
    public void htmlEncode_shouldConvertCharactersToTheirHtmlEntities() throws Exception {
        String result = htmlEncode("fred, barney, & pebbles");
        assertThat(result, is(equalTo("fred, barney, &amp; pebbles")));
    }

    @Ignore
    public void kebabCase_shouldConvertAStringToKebabCase() throws Exception {
        String[] input = {
                "Foo Bar",
                "fooBar",
                "__FOO_BAR__"
        };

        Arrays.stream(input).forEach(el ->
                assertThat(String.format("%s should be foo-bar", el), toKebabCase(el), is(equalTo("foo-bar"))));

    }

    @Ignore
    public void snakeCase_shouldConvertAStringToSnakecase() throws Exception {
        String[] input = {
                "Foo Bar",
                "fooBar",
                "--FOO-BAR--"
        };

        Arrays.stream(input).forEach(el ->
                assertThat(String.format("%s should be foo_bar", el), toSnakeCase(el), is(equalTo("foo_bar"))));

    }
}