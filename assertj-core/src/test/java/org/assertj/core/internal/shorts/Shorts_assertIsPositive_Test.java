/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2025 the original author or authors.
 */
package org.assertj.core.internal.shorts;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.testkit.TestData.someInfo;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Shorts;
import org.assertj.core.internal.ShortsBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Shorts#assertIsPositive(AssertionInfo, Short)}</code>.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
class Shorts_assertIsPositive_Test extends ShortsBaseTest {

  @Test
  void should_succeed_since_actual_is_positive() {
    shorts.assertIsPositive(someInfo(), (short) 6);
  }

  @Test
  void should_fail_since_actual_is_not_positive() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> shorts.assertIsPositive(someInfo(), (short) -6))
                                                   .withMessage("%nExpecting actual:%n  -6%nto be greater than:%n  0%n".formatted());
  }

  @Test
  void should_succeed_since_actual_is_positive_according_to_custom_comparison_strategy() {
    shortsWithAbsValueComparisonStrategy.assertIsPositive(someInfo(), (short) -1);
  }

  @Test
  void should_fail_since_actual_is_not_positive_according_to_custom_comparison_strategy() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> shortsWithAbsValueComparisonStrategy.assertIsPositive(someInfo(),
                                                                                                                           (short) 0))
                                                   .withMessage("%nExpecting actual:%n  0%nto be greater than:%n  0%nwhen comparing values using AbsValueComparator".formatted());
  }
}
