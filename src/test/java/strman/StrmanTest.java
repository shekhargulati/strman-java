package strman;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.junit.Assert.assertThat;
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
}