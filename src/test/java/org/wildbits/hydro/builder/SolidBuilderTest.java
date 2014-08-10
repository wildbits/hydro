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

import junit.framework.Assert;
import org.junit.Test;
import org.wildbits.hydro.Solid;
import org.wildbits.hydro.impl.CompositeSolid;
import org.wildbits.hydro.impl.HomogeneousSolid;

import static org.wildbits.hydro.utils.Utils.big;

public class SolidBuilderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() throws Exception {
        new SolidBuilder().build();
    }

    @Test
    public void testBuildHomogeneousSolid() throws Exception {
        Solid<BigDecimal> solid = new SolidBuilder().add(big("30"), big("40")).build();
        Assert.assertTrue(solid instanceof HomogeneousSolid);
    }

    @Test
    public void testBuildCompositeSolid() throws Exception {
        Solid<BigDecimal> solid = new SolidBuilder()
                .add(big("30"), big("40"))
                .add(big("50"), big("60")).build();
        Assert.assertTrue(solid instanceof CompositeSolid);
    }

}
