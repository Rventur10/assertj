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
package org.assertj.tests.core.api;

import static org.assertj.core.api.Assertions.assertThatTemporal;
import static org.assertj.core.api.BDDAssertions.then;

import java.time.Year;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import org.assertj.core.api.TemporalAssert;
import org.junit.jupiter.api.Test;

class Assertions_assertThatTemporal_Test {

  @Test
  void should_create_Assert_with_Temporal() {
    // GIVEN
    Temporal actual = ZonedDateTime.now();
    // WHEN
    TemporalAssert result = assertThatTemporal(actual);
    // THEN
    then(result).isNotNull();
  }

  @Test
  void should_create_Assert_with_Year() {
    // GIVEN
    Year actual = Year.now();
    // WHEN
    TemporalAssert result = assertThatTemporal(actual);
    // THEN
    then(result).isNotNull();
  }

}
