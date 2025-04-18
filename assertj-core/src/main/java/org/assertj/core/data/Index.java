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
package org.assertj.core.data;

import static org.assertj.core.util.Preconditions.checkArgument;

/**
 * A positive index.
 *
 * @author Alex Ruiz
 */
public final class Index {

  public final int value;

  /**
   * Creates a new {@link Index}.
   *
   * @param value the value of the index.
   * @return the created {@code Index}.
   * @throws IllegalArgumentException if the given value is negative.
   */
  public static Index atIndex(int value) {
    checkArgument(value >= 0, "The value of the index should not be negative");
    return new Index(value);
  }

  private Index(int value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Index)) return false;
    Index other = (Index) obj;
    return value == other.value;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(value);
  }

  @Override
  public String toString() {
    return "%s[value=%d]".formatted(getClass().getSimpleName(), value);
  }

}
