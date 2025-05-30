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
package org.assertj.core.condition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.util.Arrays.array;

import org.assertj.core.api.Condition;
import org.assertj.core.api.TestCondition;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Join#Join(Condition...)}</code>.
 *
 * @author Yvonne Wang
 */
class Join_constructor_with_array_Test {

  @Test
  void should_throw_error_if_Condition_array_is_null() {
    // GIVEN
    Condition<Object>[] conditions = null;
    // THEN
    assertThatNullPointerException().isThrownBy(() -> new ConcreteJoin(conditions))
                                    .withMessage("The given conditions should not be null");
  }

  @Test
  void should_throw_error_if_Condition_array_contains_nulls() {
    // GIVEN
    Condition<Object>[] conditions = array(new TestCondition<>(), null);
    // THEN
    assertThatNullPointerException().isThrownBy(() -> new ConcreteJoin(conditions))
                                    .withMessage("The given conditions should not have null entries");
  }

  @Test
  void should_create_new_Join_with_passed_Conditions() {
    // GIVEN
    Condition<Object>[] conditions = array(new TestCondition<>(), new TestCondition<>());
    // WHEN
    Join<Object> join = new ConcreteJoin(conditions);
    // THEN
    assertThat(join.conditions).containsExactly(conditions);
  }
}
