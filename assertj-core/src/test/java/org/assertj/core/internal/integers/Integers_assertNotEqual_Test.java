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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldNotBeEqual.shouldNotBeEqual;
import static org.assertj.core.testkit.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Integers;
import org.assertj.core.internal.IntegersBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Integers#assertNotEqual(AssertionInfo, Integer, int)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
class Integers_assertNotEqual_Test extends IntegersBaseTest {

  @Test
  void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> integers.assertNotEqual(someInfo(), null, 8))
                                                   .withMessage(actualIsNull());
  }

  @Test
  void should_pass_if_integers_are_not_equal() {
    integers.assertNotEqual(someInfo(), 8, 6);
  }

  @Test
  void should_fail_if_integers_are_equal() {
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> integers.assertNotEqual(info, 6, 6));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldNotBeEqual(6, 6));
  }

  @Test
  void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> integersWithAbsValueComparisonStrategy.assertNotEqual(someInfo(),
                                                                                                                           null,
                                                                                                                           8))
                                                   .withMessage(actualIsNull());
  }

  @Test
  void should_pass_if_integers_are_not_equal_according_to_custom_comparison_strategy() {
    integersWithAbsValueComparisonStrategy.assertNotEqual(someInfo(), 8, 6);
  }

  @Test
  void should_fail_if_integers_are_equal_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> integersWithAbsValueComparisonStrategy.assertNotEqual(info, 6, -6));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldNotBeEqual(6, -6, absValueComparisonStrategy));
  }
}
