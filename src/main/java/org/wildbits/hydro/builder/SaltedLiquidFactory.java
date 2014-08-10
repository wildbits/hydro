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
package org.wildbits.hydro.builder;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import org.wildbits.hydro.SaltedLiquid;
import org.wildbits.hydro.impl.SeaWaterImpl;

/**
 * Build {@link org.wildbits.hydro.SaltedLiquid} instances.
 */
public class SaltedLiquidFactory {

    private SaltedLiquidFactory() {}

    /**
     * @param salinity the liquid salinity in {@code kg/kg}.
     * @return a new {@link org.wildbits.hydro.SaltedLiquid} instance.
     * @throws IllegalArgumentException if the salinity is smaller than {@code 0}.
     */
    @Nonnull
    public static SaltedLiquid<BigDecimal> build(@Nonnull final BigDecimal salinity)
            throws IllegalArgumentException {
        return new SeaWaterImpl(salinity);
    }

}
