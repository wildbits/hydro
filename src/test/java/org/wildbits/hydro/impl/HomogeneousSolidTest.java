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

import junit.framework.Assert;
import org.junit.Test;
import org.wildbits.hydro.Solid;

import static org.wildbits.data.Utils.big;

public class HomogeneousSolidTest {

    private final BigDecimal volume = big("100.25");

    private final BigDecimal mass = big("200.5");

    private final Solid<BigDecimal> solid = new HomogeneousSolid(volume, mass);

    @Test
    public void testGetVolume() throws Exception {
        Assert.assertEquals(volume, solid.volume());
    }

    @Test
    public void testGetMass() throws Exception {
        Assert.assertEquals(mass, solid.mass());
    }

    @Test
    public void testDensity() throws Exception {
        BigDecimal density = mass.divide(volume);
        Assert.assertEquals(big("2"), density);
        Assert.assertEquals(density, solid.density(big("0.0")));
        Assert.assertEquals(density, solid.density(big("100.0")));
        Assert.assertEquals(density, solid.density(big("10000.0")));
    }

    @Test
    public void testBuoyancy() throws Exception {
        Assert.assertEquals(BigDecimal.ZERO, new HomogeneousSolid(
                big("1"), big("1000")).buoyancy(big("1000")));
        Assert.assertEquals(big("500"), new HomogeneousSolid(
                big("1"), big("500")).buoyancy(big("1000")));
        Assert.assertEquals(big("-1000"), new HomogeneousSolid(
                big("1"), big("2000")).buoyancy(big("1000")));

    }
}
