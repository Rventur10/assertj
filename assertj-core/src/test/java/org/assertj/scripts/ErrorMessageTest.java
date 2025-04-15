package org.assertj.scripts;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ErrorMessageTest {

    @Test // White Box: Test error message structure
    void testErrorMessage() {
        List<String> list = Arrays.asList("a");
        AssertionError error = assertThrows(AssertionError.class, () ->
            assertThat(list).contains("b"));
        assertThat(error.getMessage()).contains("to contain", "b");
    }
}
