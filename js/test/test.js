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

var hydro = require("../src/hydro.js");
var assert = require("assert");

var Solid = org.wildbits.hydro.Solid;
var Seawater = org.wildbits.hydro.Seawater;

var DELTA = 0.001;

describe('Seawater', function(){

    var seawater = Seawater();

    describe('#density()', function() {
        it('should return -1 when either of the parameters is out of range', function(){
            assert.equal(-1, seawater.density(-1, 0.05));
            assert.equal(-1, seawater.density(182, 0.05));
            assert.equal(-1, seawater.density(30, -0.1));
            assert.equal(-1, seawater.density(30, 0.2));
        });
    });

    describe('#density()', function() {
        it('should return the correct density', function(){
            assert.equal(true, delta(999.9, seawater.density(0, 0)));
            assert.equal(true, delta(999.5093443, seawater.density(10, 0)));
            assert.equal(true, delta(998.0154288, seawater.density(20, 0)));
            assert.equal(true, delta(995.5371483, seawater.density(30, 0)));
            assert.equal(true, delta(992.1822208, seawater.density(40, 0)));
            assert.equal(true, delta(988.0471875, seawater.density(50, 0)));
            assert.equal(true, delta(983.2174128, seawater.density(60, 0)));
            assert.equal(true, delta(977.7670843, seawater.density(70, 0)));
            assert.equal(true, delta(971.7592128, seawater.density(80, 0)));
            assert.equal(true, delta(965.2456323, seawater.density(90, 0)));
            assert.equal(true, delta(958.267, seawater.density(100, 0)));
            assert.equal(true, delta(950.8527963, seawater.density(110, 0)));
            assert.equal(true, delta(943.0213248, seawater.density(120, 0)));
            //
            assert.equal(true, delta(1064.06, seawater.density(0, 0.08)));
            assert.equal(true, delta(1062.2, seawater.density(10, 0.08)));
            assert.equal(true, delta(1059.4908435072, seawater.density(20, 0.08)));
            assert.equal(true, delta(1056.0359993912, seawater.density(30, 0.08)));
            assert.equal(true, delta(1051.9287436288, seawater.density(40, 0.08)));
            assert.equal(true, delta(1047.25092942, seawater.density(50, 0.08)));
            assert.equal(true, delta(1042.0732331648, seawater.density(60, 0.08)));
            assert.equal(true, delta(1036.4551544632, seawater.density(70, 0.08)));
            assert.equal(true, delta(1030.4450161152, seawater.density(80, 0.08)));
            assert.equal(true, delta(1024.0799641208, seawater.density(90, 0.08)));
            assert.equal(true, delta(1017.38596768, seawater.density(100, 0.08)));
            assert.equal(true, delta(1010.3778191928, seawater.density(110, 0.08)));
            assert.equal(true, delta(1003.0591342592, seawater.density(120, 0.08)));
        });
    });

});

describe('Solid', function(){

    var volume = 100.25;
    var mass = 200.5;
    var solid = Solid(volume, mass);

    describe('#volume()', function() {
        it('should return the volume provided', function(){
            assert.equal(volume, solid.volume());
        });
    });

    describe('#mass()', function() {
        it('should return the mass provided', function(){
            assert.equal(mass, solid.mass());
        });
    });

    describe('#density()', function() {
        it('should return the density', function(){
            assert.equal(2.0, solid.density());
        });
    });

    describe('#buoyancy()', function() {
        it('should return the buoyancy', function(){
            assert.equal(0.0, Solid(1, 1000).buoyancy(1000));
            assert.equal(500, Solid(1, 500).buoyancy(1000));
            assert.equal(-1000, Solid(1, 2000).buoyancy(1000));
        });
    });

    describe('#extend()', function() {
        it('should return an extended solid', function(){
            var extended = Solid(1,2).extend(Solid(3,4));
            assert.equal(4, extended.volume());
            assert.equal(6, extended.mass());
        });
    });
});

describe('Sample', function(){
    it('execution', function() {
        var seawater = Seawater();
        var solid = Solid(1.0,1000.0).extend(Solid(0.1,1500.0));
        var density = seawater.density(20.5, 0.08);
        console.log("density: " + solid.buoyancy(density));
    });
});

function delta(expected, value) {
    return Math.abs(expected - value) < DELTA;
}
