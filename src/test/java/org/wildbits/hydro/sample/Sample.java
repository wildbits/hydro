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
package org.wildbits.hydro.sample;

import java.math.BigDecimal;

import org.wildbits.hydro.Liquid;
import org.wildbits.hydro.Solid;
import org.wildbits.hydro.impl.SeaWaterImpl;
import org.wildbits.hydro.impl.SolidBuilderImpl;

import static org.wildbits.hydro.HydroUtils.big;

public class Sample {

    public static void main(String[] args) {

        // build solid composed of 1m^3 with density 1000 kg•m^-3 and 0.1 m^3 with density 1500 kg•m^-3
        Solid<BigDecimal> solid = new SolidBuilderImpl().add(big("1"), big("1000")).add(big("0.1"), big("1500")).build();

        // build water model with salinity 0.08 kg/kg
        Liquid<BigDecimal> seaWater = new SeaWaterImpl(big("0.08"));

        // compute solid mass in kg
        BigDecimal mass = solid.mass();

        // compute solid volume in m^3
        BigDecimal volume = solid.volume();

        // compute solid density in kg•m^-3
        BigDecimal density = solid.density(big("20.5"));

        // compute solid buoyancy immersed in water in kg
        BigDecimal waterDensity = seaWater.density(big("20.5"));
        BigDecimal buoyancy = (waterDensity != null) ? solid.buoyancy(waterDensity) : null;

    }

}
