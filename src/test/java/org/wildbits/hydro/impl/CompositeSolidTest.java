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
package org.wildbits.hydro.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

import junit.framework.Assert;
import org.junit.Test;
import org.wildbits.hydro.Solid;

import static org.wildbits.data.Utils.big;

public class CompositeSolidTest {

    private final BigDecimal volume1 = big("1"), mass1 = big("2");

    private final BigDecimal volume2 = big("3"), mass2 = big("4");

    private final HomogeneousSolid solid1 = new HomogeneousSolid(volume1, mass1), solid2 = new HomogeneousSolid(volume2, mass2);

    private final Solid<BigDecimal> compositeSolid = new CompositeSolid(Arrays.<Solid<BigDecimal>>asList(solid1, solid2));

    @Test
    public void testGetVolume() throws Exception {
        Assert.assertEquals(big("4"), compositeSolid.volume());
    }

    @Test
    public void testGetMass() throws Exception {
        Assert.assertEquals(big("6"), compositeSolid.mass());
    }

    @Test
    public void testDensity() throws Exception {
        BigDecimal temperature = big("0.0");
        BigDecimal density = solid1.mass().divide(compositeSolid.mass(), MathContext.DECIMAL128).multiply(solid1.density(temperature))
                .add(solid2.mass().divide(compositeSolid.mass(), MathContext.DECIMAL128).multiply(solid2.density(temperature)));
        Assert.assertEquals(density, compositeSolid.density(big("0.0")));
        Assert.assertEquals(density, compositeSolid.density(big("100.0")));
        Assert.assertEquals(density, compositeSolid.density(big("10000.0")));
    }

    @Test
    public void testBuoyancy() throws Exception {
        Assert.assertEquals(big("3994"), compositeSolid.buoyancy(big("1000")));
    }
}
