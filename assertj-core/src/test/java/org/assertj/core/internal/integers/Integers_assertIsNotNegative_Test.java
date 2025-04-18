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
package org.assertj.core.internal.integers;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.testkit.TestData.someInfo;

import org.assertj.core.internal.IntegersBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Integers#assertIsNotNegative(AssertionInfo, Integer))}</code>.
 *
 * @author Nicolas François
 */
class Integers_assertIsNotNegative_Test extends IntegersBaseTest {

  @Test
  void should_succeed_since_actual_is_not_negative() {
    integers.assertIsNotNegative(someInfo(), 6);
  }

  @Test
  void should_succeed_since_actual_is_zero() {
    integers.assertIsNotNegative(someInfo(), 0);
  }

  @Test
  void should_fail_since_actual_is_negative() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> integers.assertIsNotNegative(someInfo(), -6))
                                                   .withMessage("%nExpecting actual:%n  -6%nto be greater than or equal to:%n  0%n".formatted());
  }

  @Test
  void should_succeed_since_actual_is_not_negative_according_to_custom_comparison_strategy() {
    integersWithAbsValueComparisonStrategy.assertIsNotNegative(someInfo(), -1);
  }

  @Test
  void should_succeed_since_actual_positive_is_not_negative_according_to_custom_comparison_strategy() {
    integersWithAbsValueComparisonStrategy.assertIsNotNegative(someInfo(), 1);
  }

}
