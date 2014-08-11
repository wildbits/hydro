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
import org.wildbits.hydro.Density;

import static org.wildbits.hydro.HydroUtils.big;

public class SeawaterTest {

    private static final double DELTA = 0.001;

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testDensity() throws Exception {
        Density<BigDecimal> densityZeroSalinity = new SeawaterImpl(big("0.0"));
        Assert.assertEquals(999.9, densityZeroSalinity.density(big("0.0")).doubleValue(), DELTA);
        Assert.assertEquals(999.5093443, densityZeroSalinity.density(big("10.0")).doubleValue(), DELTA);
        Assert.assertEquals(998.0154288, densityZeroSalinity.density(big("20.0")).doubleValue(), DELTA);
        Assert.assertEquals(995.5371483, densityZeroSalinity.density(big("30.0")).doubleValue(), DELTA);
        Assert.assertEquals(992.1822208, densityZeroSalinity.density(big("40.0")).doubleValue(), DELTA);
        Assert.assertEquals(988.0471875, densityZeroSalinity.density(big("50.0")).doubleValue(), DELTA);
        Assert.assertEquals(983.2174128, densityZeroSalinity.density(big("60.0")).doubleValue(), DELTA);
        Assert.assertEquals(977.7670843, densityZeroSalinity.density(big("70.0")).doubleValue(), DELTA);
        Assert.assertEquals(971.7592128, densityZeroSalinity.density(big("80.0")).doubleValue(), DELTA);
        Assert.assertEquals(965.2456323, densityZeroSalinity.density(big("90.0")).doubleValue(), DELTA);
        Assert.assertEquals(958.267, densityZeroSalinity.density(big("100.0")).doubleValue(), DELTA);
        Assert.assertEquals(950.8527963, densityZeroSalinity.density(big("110.0")).doubleValue(), DELTA);
        Assert.assertEquals(943.0213248, densityZeroSalinity.density(big("120.0")).doubleValue(), DELTA);

        Density<BigDecimal> densityMidSalinity = new SeawaterImpl(big("0.08"));
        Assert.assertEquals(1064.06, densityMidSalinity.density(big("0.0")).doubleValue(), DELTA);
        Assert.assertEquals(1062.2, densityMidSalinity.density(big("10.0")).doubleValue(), DELTA);
        Assert.assertEquals(1059.4908435072, densityMidSalinity.density(big("20.0")).doubleValue(), DELTA);
        Assert.assertEquals(1056.0359993912, densityMidSalinity.density(big("30.0")).doubleValue(), DELTA);
        Assert.assertEquals(1051.9287436288, densityMidSalinity.density(big("40.0")).doubleValue(), DELTA);
        Assert.assertEquals(1047.25092942, densityMidSalinity.density(big("50.0")).doubleValue(), DELTA);
        Assert.assertEquals(1042.0732331648, densityMidSalinity.density(big("60.0")).doubleValue(), DELTA);
        Assert.assertEquals(1036.4551544632, densityMidSalinity.density(big("70.0")).doubleValue(), DELTA);
        Assert.assertEquals(1030.4450161152, densityMidSalinity.density(big("80.0")).doubleValue(), DELTA);
        Assert.assertEquals(1024.0799641208, densityMidSalinity.density(big("90.0")).doubleValue(), DELTA);
        Assert.assertEquals(1017.38596768, densityMidSalinity.density(big("100.0")).doubleValue(), DELTA);
        Assert.assertEquals(1010.3778191928, densityMidSalinity.density(big("110.0")).doubleValue(), DELTA);
        Assert.assertEquals(1003.0591342592, densityMidSalinity.density(big("120.0")).doubleValue(), DELTA);
    }

    @Test
    public void testSalinityOutOfRange() {
        BigDecimal validTemperature = big("20.0");
        Density<BigDecimal> salinityTooHigh = new SeawaterImpl(big("0.2"));
        Assert.assertNull(salinityTooHigh.density(validTemperature));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSalinity() {
        new SeawaterImpl(big("-1.0"));
    }

    @Test
    public void testTemperatureOutOfRange() {
        BigDecimal validSalinity = big("0.1");
        Density<BigDecimal> density = new SeawaterImpl(validSalinity);
        Assert.assertNull(density.density(big("-0.50")));
        Assert.assertNull(density.density(big("185.0")));
    }

}
