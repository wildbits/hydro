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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.wildbits.hydro.Solid;
import org.wildbits.hydro.impl.CompositeSolid;
import org.wildbits.hydro.impl.HomogeneousSolid;

/**
 * Builder for {@link Solid} instances.
 */
public class SolidBuilder {

    private final List<Solid<BigDecimal>> solids = new ArrayList<Solid<BigDecimal>>();

    public SolidBuilder() {}

    /**
     * Add a volume/mass pair to the solid being built.
     * @param volume the volume to add in {@code m^-3}
     * @param mass the mass to add in {@code kg}
     * @return {@code this}
     */
    public SolidBuilder add(@Nonnull final BigDecimal volume, @Nonnull final BigDecimal mass) {
        solids.add(new HomogeneousSolid(volume, mass));
        return this;
    }

    /**
     * @return a new {@link org.wildbits.hydro.Solid} instance containing the added volumes/masses.
     * @throws IllegalArgumentException if no volume/mass has been added
     */
    public Solid<BigDecimal> build()
            throws IllegalArgumentException {
        int size = solids.size();
        if (size == 0) {
            throw new IllegalArgumentException("no volume/mass pair is defined");
        }
        return solids.size() == 1 ? solids.get(0) : new CompositeSolid(solids);
    }

}
