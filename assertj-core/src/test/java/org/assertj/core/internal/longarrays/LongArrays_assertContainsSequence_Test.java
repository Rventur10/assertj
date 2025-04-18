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
package org.assertj.core.internal.longarrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldContainSequence.shouldContainSequence;
import static org.assertj.core.internal.ErrorMessages.valuesToLookForIsNull;
import static org.assertj.core.testkit.LongArrays.arrayOf;
import static org.assertj.core.testkit.LongArrays.emptyArray;
import static org.assertj.core.testkit.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.LongArrays;
import org.assertj.core.internal.LongArraysBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link LongArrays#assertContainsSequence(AssertionInfo, long[], long[])}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
class LongArrays_assertContainsSequence_Test extends LongArraysBaseTest {

  @Override
  protected void initActualArray() {
    actual = arrayOf(6L, 8L, 10L, 12L);
  }

  @Test
  void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arrays.assertContainsSequence(someInfo(), null, arrayOf(8L)))
                                                   .withMessage(actualIsNull());
  }

  @Test
  void should_throw_error_if_sequence_is_null() {
    assertThatNullPointerException().isThrownBy(() -> arrays.assertContainsSequence(someInfo(), actual, null))
                                    .withMessage(valuesToLookForIsNull());
  }

  @Test
  void should_pass_if_actual_and_given_values_are_empty() {
    actual = emptyArray();
    arrays.assertContainsSequence(someInfo(), actual, emptyArray());
  }

  @Test
  void should_fail_if_array_of_values_to_look_for_is_empty_and_actual_is_not() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arrays.assertContainsSequence(someInfo(), actual,
                                                                                                   emptyArray()));
  }

  @Test
  void should_fail_if_sequence_is_bigger_than_actual() {
    AssertionInfo info = someInfo();
    long[] sequence = { 6L, 8L, 10L, 12L, 20L, 22L };

    Throwable error = catchThrowable(() -> arrays.assertContainsSequence(info, actual, sequence));

    assertThat(error).isInstanceOf(AssertionError.class);
    verifyFailureThrownWhenSequenceNotFound(info, sequence);
  }

  @Test
  void should_fail_if_actual_does_not_contain_whole_sequence() {
    AssertionInfo info = someInfo();
    long[] sequence = { 6L, 20L };

    Throwable error = catchThrowable(() -> arrays.assertContainsSequence(info, actual, sequence));

    assertThat(error).isInstanceOf(AssertionError.class);
    verifyFailureThrownWhenSequenceNotFound(info, sequence);
  }

  @Test
  void should_fail_if_actual_contains_first_elements_of_sequence() {
    AssertionInfo info = someInfo();
    long[] sequence = { 6L, 20L, 22L };

    Throwable error = catchThrowable(() -> arrays.assertContainsSequence(info, actual, sequence));

    assertThat(error).isInstanceOf(AssertionError.class);
    verifyFailureThrownWhenSequenceNotFound(info, sequence);
  }

  private void verifyFailureThrownWhenSequenceNotFound(AssertionInfo info, long[] sequence) {
    verify(failures).failure(info, shouldContainSequence(actual, sequence));
  }

  @Test
  void should_pass_if_actual_contains_sequence() {
    arrays.assertContainsSequence(someInfo(), actual, arrayOf(6L, 8L));
  }

  @Test
  void should_pass_if_actual_and_sequence_are_equal() {
    arrays.assertContainsSequence(someInfo(), actual, arrayOf(6L, 8L, 10L, 12L));
  }

  @Test
  void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arraysWithCustomComparisonStrategy.assertContainsSequence(someInfo(),
                                                                                                                               null,
                                                                                                                               arrayOf(-8L)))
                                                   .withMessage(actualIsNull());
  }

  @Test
  void should_throw_error_if_sequence_is_null_whatever_custom_comparison_strategy_is() {
    assertThatNullPointerException().isThrownBy(() -> arraysWithCustomComparisonStrategy.assertContainsSequence(someInfo(),
                                                                                                                actual,
                                                                                                                null))
                                    .withMessage(valuesToLookForIsNull());
  }

  @Test
  void should_fail_if_array_of_values_to_look_for_is_empty_and_actual_is_not_whatever_custom_comparison_strategy_is() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arraysWithCustomComparisonStrategy.assertContainsSequence(someInfo(),
                                                                                                                               actual,
                                                                                                                               emptyArray()));
  }

  @Test
  void should_fail_if_sequence_is_bigger_than_actual_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    long[] sequence = { 6L, -8L, 10L, 12L, 20L, 22L };

    Throwable error = catchThrowable(() -> arraysWithCustomComparisonStrategy.assertContainsSequence(info, actual, sequence));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldContainSequence(actual, sequence, absValueComparisonStrategy));
  }

  @Test
  void should_fail_if_actual_does_not_contain_whole_sequence_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    long[] sequence = { 6L, 20L };

    Throwable error = catchThrowable(() -> arraysWithCustomComparisonStrategy.assertContainsSequence(info, actual, sequence));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldContainSequence(actual, sequence, absValueComparisonStrategy));
  }

  @Test
  void should_fail_if_actual_contains_first_elements_of_sequence_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    long[] sequence = { 6L, 20L, 22L };

    Throwable error = catchThrowable(() -> arraysWithCustomComparisonStrategy.assertContainsSequence(info, actual, sequence));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldContainSequence(actual, sequence, absValueComparisonStrategy));
  }

  @Test
  void should_pass_if_actual_contains_sequence_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertContainsSequence(someInfo(), actual, arrayOf(6L, -8L));
  }

  @Test
  void should_pass_if_actual_and_sequence_are_equal_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertContainsSequence(someInfo(), actual, arrayOf(6L, -8L, 10L, 12L));
  }
}
