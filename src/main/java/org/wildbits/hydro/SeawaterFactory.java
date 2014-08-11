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
package org.wildbits.hydro;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

/**
 * The {@code SeawaterFactory} allows to build seawater instances of {@code SaltedLiquid}.
 */
public interface SeawaterFactory {

    /**
     * @param salinity the seawater salinity in {@code kg/kg}.
     * @return a new {@link org.wildbits.hydro.SaltedLiquid} instance.
     * @throws IllegalArgumentException if the salinity is invalid.
     */
    @Nonnull
    SaltedLiquid<BigDecimal> getInstance(@Nonnull BigDecimal salinity)
            throws IllegalArgumentException;

}
