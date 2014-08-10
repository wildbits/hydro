/**
 * Copyright T. Maret (timothee.maret@gmail.com)
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
package org.wildbits.hydro.utils;

import java.math.BigDecimal;

public class Utils {

    /**
     * Static method to offer a syntactic sugar for building {@link BigDecimal} instances.
     * @param value String representation of BigDecimal
     * @throws NumberFormatException if val is not a valid representation of a BigDecimal
     * {@see http://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html#BigDecimal(java.lang.String)}
     */
    public static BigDecimal big(final String value) {
        return new BigDecimal(value);
    }

}
