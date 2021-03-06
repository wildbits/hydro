/**
 * Copyright 2014 wildbits.github.io
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
import java.math.MathContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.wildbits.data.Utils;
import org.wildbits.hydro.Solid;

/**
 * Represent an homogeneous solid.
 */
public class HomogeneousSolid implements Solid<BigDecimal> {

    private static final MathContext MC = MathContext.DECIMAL128;

    private final BigDecimal volume;

    private final BigDecimal mass;

    /**
     * @param volume in {@code m^-3}
     * @param mass in {@code kg}
     */
    public HomogeneousSolid(@Nonnull final Number volume, @Nonnull final Number mass) {
        this.volume = Utils.big(volume);
        this.mass = Utils.big(mass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public BigDecimal volume() {
        return volume;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public BigDecimal mass() {
        return mass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public BigDecimal density(@Nonnull Number temperature) {
        return mass.divide(volume, MC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public BigDecimal buoyancy(@Nonnull Number ρ) {
        return Utils.big(ρ).multiply(volume).subtract(mass, MC);
    }
}
