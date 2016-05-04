package strman;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.hamcrest.collection.IsArrayWithSize.emptyArray;
import static org.junit.Assert.assertThat;
import static strman.Strman.*;

public class StrmanTest {

    @Test
    public void shouldAppendStringsToEndOfValue() throws Exception {
        assertThat(append("f", "o", "o", "b", "a", "r"), equalTo("foobar"));
        assertThat(append("foobar"), equalTo("foobar"));
        assertThat(append("", "foobar"), equalTo("foobar"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void appendShouldThrowIllegalArgumentExceptionWhenValueIsNull() throws Exception {
        append(null);
    }

    @Test
    public void shouldAppendStringArrayToEndOfValue() throws Exception {
        assertThat(appendArray("f", new String[]{"o", "o", "b", "a", "r"}), equalTo("foobar"));
        assertThat(appendArray("foobar", new String[]{}), equalTo("foobar"));
        assertThat(appendArray("", new String[]{"foobar"}), equalTo("foobar"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void appendArrayShouldThrowIllegalArgumentExceptionWhenValueIsNull() throws Exception {
        appendArray(null, new String[]{});
    }

    @Test
    public void shouldFindCharacterAtIndex() throws Exception {
        assertThat(at("foobar", 0), equalTo(Optional.of("f")));
        assertThat(at("foobar", 1), equalTo(Optional.of("o")));
        assertThat(at("foobar", -1), equalTo(Optional.of("r")));
        assertThat(at("foobar", -2), equalTo(Optional.of("a")));
        assertThat(at("foobar", 10), equalTo(Optional.empty()));
        assertThat(at("foobar", -10), equalTo(Optional.empty()));
    }

    @Test
    public void shouldReturnArrayWithStringsBetweenStartAndEnd() throws Exception {
        assertThat(between("[abc][def]", "[", "]"), arrayContaining("abc", "def"));
        assertThat(between("<span>foo</span>", "<span>", "</span>"), arrayContaining("foo"));
        assertThat(between("<span>foo</span><span>bar</span>", "<span>", "</span>"), arrayContaining("foo", "bar"));
    }

    @Test
    public void betweenShouldReturnFullStringWhenStartAndEndDoesNotExist() throws Exception {
        assertThat(between("[abc][def]", "{", "}"), arrayContaining("[abc][def]"));
        assertThat(between("", "{", "}"), arrayContaining(""));
    }
}