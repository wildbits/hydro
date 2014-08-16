/**
 * Copyright wildbits org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wildbits.data;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

/**
 * Util class for simplifying dealing with {@link BigDecimal} instances.
 */
public class Utils {

    /**
     * Convert a {@link Number} instance into an equivalent {@link BigDecimal}.
     * If the {@code Number} instance is already a {@code BigDecimal}, the instance is returned ; otherwise a new
     * {@link BigDecimal} is instantiated.
     *
     * @param number the {@link Number} to be converted to {@link BigDecimal}
     * @return the {@link BigDecimal} instance
     */
    @Nonnull
    public static BigDecimal big(@Nonnull final Number number) {
        return number instanceof BigDecimal ? (BigDecimal) number : new BigDecimal(number.toString());
    }

    /**
     * Syntactic sugar for building {@link BigDecimal} instances from a string representation.
     *
     * @param value {@link String} representation of {@link BigDecimal}
     * @throws NumberFormatException if val is not a valid representation of a {@link BigDecimal}
     * {@see http://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html#BigDecimal(java.lang.String)}
     */
    @Nonnull
    public static BigDecimal big(@Nonnull final String value) {
        return new BigDecimal(value);
    }

}
