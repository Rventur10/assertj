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
package org.assertj.core.api.objectarray;

import static org.mockito.Mockito.verify;

import org.assertj.core.api.Condition;
import org.assertj.core.api.ObjectArrayAssert;
import org.assertj.core.api.ObjectArrayAssertBaseTest;
import org.assertj.core.api.TestCondition;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tests for <code>{@link ObjectArrayAssert#haveExactly(int, Condition)}</code>.
 * 
 * @author Nicolas François
 * @author Mikhail Mazursky
 */
class ObjectArrayAssert_haveExactly_Test extends ObjectArrayAssertBaseTest {

  private Condition<Object> condition;

  @BeforeEach
  void before() {
    condition = new TestCondition<>();
  }

  @Override
  protected ObjectArrayAssert<Object> invoke_api_method() {
    return assertions.haveExactly(2, condition);
  }

  @Override
  protected void verify_internal_effects() {
    verify(arrays).assertHaveExactly(getInfo(assertions), getActual(assertions), 2, condition);
  }
}
