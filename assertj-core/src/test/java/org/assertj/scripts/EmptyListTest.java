package org.assertj.scripts;

import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyListTest {

    @Test // Black Box: Test empty list boundary
    void testEmptyList() {
        assertThat(Collections.emptyList()).isEmpty();
    }
}
