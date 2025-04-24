package org.assertj.scripts;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MultipleAssertionsTest {

    @Test // Integration: Test multiple assertions
    void testMultipleAssertions() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        assertThat(numbers).contains(2).doesNotContain(4).hasSize(3);
    }
}
