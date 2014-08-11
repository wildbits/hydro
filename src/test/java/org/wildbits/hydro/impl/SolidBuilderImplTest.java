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

import static org.wildbits.hydro.HydroUtils.big;

public class SolidBuilderImplTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() throws Exception {
        new SolidBuilderImpl().build();
    }

    @Test
    public void testBuildHomogeneousSolid() throws Exception {
        Solid<BigDecimal> solid = new SolidBuilderImpl().add(big("30"), big("40")).build();
        Assert.assertTrue(solid instanceof HomogeneousSolid);
    }

    @Test
    public void testBuildCompositeSolid() throws Exception {
        Solid<BigDecimal> solid = new SolidBuilderImpl()
                .add(big("30"), big("40"))
                .add(big("50"), big("60")).build();
        Assert.assertTrue(solid instanceof CompositeSolid);
    }

}
