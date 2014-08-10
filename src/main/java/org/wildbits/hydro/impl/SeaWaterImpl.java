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
package org.wildbits.hydro.impl;

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.wildbits.hydro.SaltedLiquid;

import static org.wildbits.hydro.utils.Utils.big;

/**
 * Salt Water Density model as defined in paper: The thermophysical properties of seawater: A review of existing
 * correlations and data, Eq. 8. The paper is available on
 * <a href="http://hdl.handle.net/1721.1/69157">MIT Open Access</a>.
 */
public class SeaWaterImpl implements SaltedLiquid<BigDecimal> {

    /**
     * The minimum temperature in {@code °C} for which the salt water density is defined by the implementation.
     */
    private static final BigDecimal MIN_TEMPERATURE = BigDecimal.ZERO;

    /**
     * The maximum temperature in {@code °C} for which the salt water density is defined by the implementation.
     */
    private static final BigDecimal MAX_TEMPERATURE = big("180.0");

    /**
     * The minimum salinity in {@code kg/kg} for which the salt water density is defined by the implementation.
     */
    private static final BigDecimal MIN_SALINITY = BigDecimal.ZERO;

    /**
     * The maximum salinity in {@code kg/kg} for which the salt water density is defined by the implementation.
     */
    private static final BigDecimal MAX_SALINITY = big("0.160");

    /**
     * The density accuracy (±0.1%).
     */
    private static final double ACCURACY = 0.001D;

    private static final BigDecimal
            A1 = big("999.9"),
            A2 = big("0.02034"),
            A3 = big("-0.006162"),
            A4 = big("0.00002261"),
            A5 = big("-0.00000004657"),
            B1 = big("802.0"),
            B2 = big("-2.001"),
            B3 = big("0.01677"),
            B4 = big("-0.00003060"),
            B5 = big("-0.00001613");

    private final BigDecimal salinity;

    /**
     * @param salinity the water salinity in {@code kg/kg}. Fresh water has salinity of {@code 0 kg/kg}.
     */
    public SeaWaterImpl(@Nonnull final BigDecimal salinity) {
        if (salinity.signum() < 0) {
            throw new IllegalArgumentException("salinity can't be negative");
        }
        this.salinity = salinity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public BigDecimal density(@Nonnull BigDecimal temperature) {
        if (validSalinity() && validTemperature(temperature)) {
            final BigDecimal t1 = temperature, t2 = t1.pow(2), t3 = t1.pow(3), t4 = t1.pow(4);
            final BigDecimal s1 = salinity, s2 = s1.pow(2);
            return A1.add(A2.multiply(t1))
                    .add(A3.multiply(t2))
                    .add(A4.multiply(t3))
                    .add(A5.multiply(t4))
                    .add(B1.multiply(s1))
                    .add(B2.multiply(s1).multiply(t1))
                    .add(B3.multiply(s1).multiply(t2))
                    .add(B4.multiply(s1).multiply(t3))
                    .add(B5.multiply(s2).multiply(t2));
        } else {
            return null;
        }
    }

    /**
     * @return {@code true} if the provided salinity is valid for the model ; {@code false} otherwise.
     */
    private boolean validSalinity() {
        return salinity.compareTo(MIN_SALINITY) >= 0 && salinity.compareTo(MAX_SALINITY) < 0;
    }

    /**
     * @param temperature in {@code °C}
     * @return {@code true} if the provided temperature is valid for the model ; {@code false} otherwise.
     */
    private boolean validTemperature(BigDecimal temperature) {
        return temperature.compareTo(MIN_TEMPERATURE) >= 0 && temperature.compareTo(MAX_TEMPERATURE) < 0;
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal salinity() {
        return salinity;
    }
}
