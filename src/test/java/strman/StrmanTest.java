package strman;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static strman.Strman.append;
import static strman.Strman.appendArray;

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


}