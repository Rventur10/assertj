package org.assertj.scripts;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListContainsElementTest {

    @Test // Black Box: Test list contains element
    void testListContainsElement() {
        List<String> list = Arrays.asList("a", "b", "c");
        assertThat(list).contains("b");
    }
}
