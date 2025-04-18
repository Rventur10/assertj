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
package org.assertj.core.error;

import java.util.StringJoiner;

/**
 * Creates an error message indicating that a {@link Class} should have a given package.
 *
 * @author Matteo Mirk
 */
public class ShouldHavePackage extends BasicErrorMessageFactory {
  private static final String SHOULD_HAVE_PACKAGE = new StringJoiner("%n", "%n", "").add("Expecting")
                                                                                    .add("  %s")
                                                                                    .add("to have package:")
                                                                                    .add("  %s")
                                                                                    .toString();
  private static final String BUT_HAD_NONE = new StringJoiner("%n", "%n", "").add("but had none.")
                                                                             .toString();
  private static final String BUT_HAD = new StringJoiner("%n", "%n", "").add("but had:")
                                                                        .add("  %s")
                                                                        .toString();

  /**
   * Creates a new <code>ShouldHavePackage</code> with a {@link Package} instance.
   *
   * @param actual  the actual value in the failed assertion.
   * @param aPackage the expected package
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHavePackage(Class<?> actual, Package aPackage) {
    return shouldHavePackage(actual, aPackage.getName());
  }

  /**
   * Creates a new <code>ShouldHavePackage</code> with a package name.
   *
   * @param actual      the actual value in the failed assertion.
   * @param packageName the expected package name
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHavePackage(Class<?> actual, String packageName) {
    final Package actualPackage = actual.getPackage();
    return (actualPackage == null)
        ? new ShouldHavePackage(actual, packageName)
        : new ShouldHavePackage(actual, packageName, actualPackage.getName());
  }

  private ShouldHavePackage(Class<?> actual, String expectedPackage) {
    super(SHOULD_HAVE_PACKAGE + BUT_HAD_NONE, actual, expectedPackage);
  }

  private ShouldHavePackage(Class<?> actual, String expectedPackage, String actualPackage) {
    super(SHOULD_HAVE_PACKAGE + BUT_HAD, actual, expectedPackage, actualPackage);
  }

}
