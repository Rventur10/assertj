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
package org.assertj.core.internal.intarrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.testkit.IntArrays.emptyArray;
import static org.assertj.core.testkit.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.IntArrays;
import org.assertj.core.internal.IntArraysBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link IntArrays#assertEmpty(AssertionInfo, int[])}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
class IntArrays_assertEmpty_Test extends IntArraysBaseTest {

  @Test
  void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arrays.assertEmpty(someInfo(), null))
                                                   .withMessage(actualIsNull());
  }

  @Test
  void should_fail_if_actual_is_not_empty() {
    AssertionInfo info = someInfo();
    int[] actual = { 6, 8 };

    Throwable error = catchThrowable(() -> arrays.assertEmpty(info, actual));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldBeEmpty(actual));
  }

  @Test
  void should_pass_if_actual_is_empty() {
    arrays.assertEmpty(someInfo(), emptyArray());
  }
}
