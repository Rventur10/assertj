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
package org.assertj.core.api;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class ComparatorFactory_doubleComparatorWithPrecision_Test {

  private final ComparatorFactory INSTANCE = ComparatorFactory.INSTANCE;

  @ParameterizedTest
  @CsvSource({
      "1.0, 1.1, 0.1",
      "0.111, 0.110, 0.001",
      "0.12345, 0.12346, 0.00001",
      "0.7654321, 0.7654320, 0.0000001",
      "1.2464, 1.2463, 0.0001" })
  void should_evaluate_to_be_equal(Double double1, Double double2, Double precision) {
    // GIVEN
    Comparator<Double> comparator = INSTANCE.doubleComparatorWithPrecision(precision);
    // WHEN
    int comparisonValue = comparator.compare(double1, double2);
    int inverseComparisonValue = comparator.compare(double2, double1);
    // THEN
    then(comparisonValue).isZero();
    then(inverseComparisonValue).isZero();
  }

  @ParameterizedTest
  @CsvSource({
      "1.1, 1.0, 0.05",
      "0.111, 0.110, 0.00099",
      "0.12346, 0.12345, 0.0000099",
      "0.7654321, 0.7654320, 0.000000099",
      "0.7654321, 0.7654320, 9e-8",
      "1.2464, 1.2463, 0.000099" })
  void should_evaluate_given_value_to_different(Double value, Double other, Double precision) {
    // GIVEN
    Comparator<Double> comparator = INSTANCE.doubleComparatorWithPrecision(precision);
    // WHEN
    int comparisonValue = comparator.compare(value, other);
    int inverseComparisonValue = comparator.compare(other, value);
    // THEN
    then(comparisonValue).isOne();
    then(inverseComparisonValue).isEqualTo(-1);
  }

  @ParameterizedTest
  @MethodSource
  void should_follow_java_behavior_when_dealing_with_infinity_and_NaN(Double value1, Double value2) {
    // GIVEN
    Comparator<Double> comparator = INSTANCE.doubleComparatorWithPrecision(1d);
    // WHEN
    int comparisonValue = comparator.compare(value1, value2);
    int javaComparisonValue = value1.compareTo(value2);
    // THEN
    then(comparisonValue).isEqualTo(javaComparisonValue);
  }

  static Stream<Arguments> should_follow_java_behavior_when_dealing_with_infinity_and_NaN() {
    return Stream.of(arguments(POSITIVE_INFINITY, NEGATIVE_INFINITY),
                     arguments(NEGATIVE_INFINITY, POSITIVE_INFINITY),
                     arguments(POSITIVE_INFINITY, POSITIVE_INFINITY),
                     arguments(NEGATIVE_INFINITY, NEGATIVE_INFINITY),
                     arguments(NaN, POSITIVE_INFINITY),
                     arguments(NaN, NEGATIVE_INFINITY),
                     arguments(NaN, NaN));
  }

  @ParameterizedTest
  @MethodSource
  void should_fail_for_invalid_precision(Double precision) {
    // GIVEN
    Comparator<Double> comparator = INSTANCE.doubleComparatorWithPrecision(precision);
    // WHEN/THEN
    assertThatIllegalArgumentException().isThrownBy(() -> comparator.compare(1d, 2d));
  }

  static Stream<Double> should_fail_for_invalid_precision() {
    return Stream.of(NaN, POSITIVE_INFINITY, NEGATIVE_INFINITY);
  }

}
