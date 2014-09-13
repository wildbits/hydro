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

org = {};

org.wildbits = {};

org.wildbits.hydro = (function () {

    'use strict';

    return {

        /**
         * Represent a composite solid.
         *
         * @param volume in {@code m^-3}
         * @param mass in {@code kg}
         */
        Solid: function(volume, mass) {

            return {

                /**
                 * @return {number} the volume of the solid in m^-3
                 */
                volume: function() {
                    return volume;
                },

                /**
                 * @return {number} the mass of the solid in kg
                 */
                mass: function() {
                    return mass;
                },

                /**
                 * @return {number} density of the solid in kg•m^-3
                 */
                density: function() {
                    return this.mass() / this.volume();
                },

                /**
                 * Return the buoyancy of the solid fully immersed in a liquid with the given density.
                 *
                 * @param density {number} the density of the liquid containing the solid in {@code kg•m^-3}
                 * @return {number} the resulting buoyancy in {@code kg} or {@code null} if undefined. A positive resulting buoyancy indicates the solid is positively buoyant.
                 */
                buoyancy: function(density) {
                    return density * this.volume() - this.mass();
                },

                /**
                 * Extend the solid with another solid.
                 *
                 * @param solid {Solid} the solid extending the current solid
                 * @return {Solid} this
                 */
                extend: function(solid) {
                    mass += solid.mass();
                    volume += solid.volume();
                    return this;
                }

            };
        },

        /**
         * Salt Water Density model as defined in paper:
         * The thermophysical properties of seawater: A review of existing correlations and data, Eq. 8.
         * The paper is available on <a href="http://hdl.handle.net/1721.1/69157">MIT Open Access</a>.
         */
        Seawater: function() {

            var A1 = big("999.9");
            var A2 = big("0.02034");
            var A3 = big("-0.006162");
            var A4 = big("0.00002261");
            var A5 = big("-0.00000004657");
            var B1 = big("802.0");
            var B2 = big("-2.001");
            var B3 = big("0.01677");
            var B4 = big("-0.00003060");
            var B5 = big("-0.00001613");

            var MIN_TEMP = 0.0;
            var MAX_TEMP = 180.0;
            var MIN_SAL = 0.0;
            var MAX_SAL = 0.160;

            return {

                /**
                 * Return the density of a substance at a given temperature.
                 * The model covers the following range for the input parameters:
                 *
                 * validTemp:     0 >= temperature < 180
                 * validSalinity: 0 >= salinity < 0.160
                 *
                 * The method return a negative number if it fails to compute the salinity.
                 *
                 * @param temperature {number} the temperature in {@code °C}
                 * @param salinity {number} the water salinity in {@code kg/kg}. Fresh water has salinity of {@code 0 kg/kg}
                 * @return {number} the density in {@code kg•m^-3} or {@code null} if undefined
                 */
                density: function (temperature, salinity) {
                    if (validSalinity(salinity) && validTemperature(temperature)) {
                        var t2 = Math.pow(temperature,2);
                        var t3 = Math.pow(temperature,3);
                        var t4 = Math.pow(temperature,4);
                        var s2 = Math.pow(salinity,2);
                        return A1 +
                            A2 * temperature +
                            A3 * t2 +
                            A4 * t3 +
                            A5 * t4 +
                            B1 * salinity +
                            B2 * salinity * temperature +
                            B3 * salinity * t2 +
                            B4 * salinity * t3 +
                            B5 * s2 * t2;
                    } else {
                        return -1;
                    }
                }
            };

            function big(s) {
                return parseFloat(s);
            }

            function validSalinity(salinity) {
                return salinity >= MIN_SAL && salinity < MAX_SAL;
            }

            function validTemperature(temperature) {
                return temperature >= MIN_TEMP && temperature < MAX_TEMP;
            }

        }

    };

}());
