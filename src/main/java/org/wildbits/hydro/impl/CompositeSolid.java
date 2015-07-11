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
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.wildbits.data.Utils;
import org.wildbits.hydro.Solid;

/**
 * Represent an composite of physically connected solids.
 */
public class CompositeSolid implements Solid<BigDecimal> {

    private static final MathContext MC = MathContext.DECIMAL128;

    private final List<Solid<BigDecimal>> solids;

    /**
     * @param solids the list of physically connected solids.
     */
    public CompositeSolid(@Nonnull final List<Solid<BigDecimal>> solids) {
        this.solids = Collections.unmodifiableList(solids);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public BigDecimal volume() {
        BigDecimal σv = BigDecimal.ZERO;
        for (Solid<BigDecimal> solid : solids) {
            σv = σv.add(solid.volume());
        }
        return σv;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public BigDecimal mass() {
        BigDecimal σm = BigDecimal.ZERO;
        for (Solid<BigDecimal> solid : solids) {
            σm = σm.add(solid.mass());
        }
        return σm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public BigDecimal density(@Nonnull Number temperature) {
        BigDecimal σm = mass();
        BigDecimal ρ = BigDecimal.ZERO;
        for (Solid<BigDecimal> solid : solids) {
            ρ = ρ.add(solid.mass().divide(σm, MC).multiply(solid.density(temperature)));
        }
        return ρ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public BigDecimal buoyancy(@Nonnull Number ρ) {
        return Utils.big(ρ).multiply(volume()).subtract(mass(), MC);
    }
}
