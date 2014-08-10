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
import org.wildbits.hydro.Liquid;

import static org.wildbits.hydro.utils.Utils.big;

public class SaltedLiquidFactoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNull() throws Exception {
        SaltedLiquidFactory.build(big("-10"));
    }

    @Test
    public void testBuild() throws Exception {
        Liquid<BigDecimal> liquid = SaltedLiquidFactory.build(big("0.08"));
        Assert.assertNotNull(liquid);
    }
}
