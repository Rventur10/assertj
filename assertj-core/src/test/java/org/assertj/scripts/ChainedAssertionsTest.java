package org.assertj.scripts;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChainedAssertionsTest {

    @Test // Data-Flow: Test chained assertions
    void testChainedAssertions() {
        List<String> list = Arrays.asList("x", "y");
        assertThat(list).hasSize(2).contains("y");
    }
}
