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
var Composite = org.wildbits.hydro.Composite;
var Cylinder = org.wildbits.hydro.Cylinder;
var Seawater = org.wildbits.hydro.Seawater;
var DryAir = org.wildbits.hydro.DryAir;
var Gas = org.wildbits.hydro.Gas;
var Liquid = org.wildbits.hydro.Liquid;

var DELTA = 0.001;

describe('Seawater', function(){

    var seawater = new Seawater();

    describe('#density()', function() {
        it('should return -1 when either of the parameters is out of range', function(){
            assertEqualsWithDelta(-1, seawater.density(-1, 0.05));
            assertEqualsWithDelta(-1, seawater.density(182, 0.05));
            assertEqualsWithDelta(-1, seawater.density(30, -0.1));
            assertEqualsWithDelta(-1, seawater.density(30, 0.2));
        });
    });

    describe('#density()', function() {
        it('should return the correct density', function(){
            assertEqualsWithDelta(999.9, seawater.density(0, 0));
            assertEqualsWithDelta(999.5093443, seawater.density(10, 0));
            assertEqualsWithDelta(998.0154288, seawater.density(20, 0));
            assertEqualsWithDelta(995.5371483, seawater.density(30, 0));
            assertEqualsWithDelta(992.1822208, seawater.density(40, 0));
            assertEqualsWithDelta(988.0471875, seawater.density(50, 0));
            assertEqualsWithDelta(983.2174128, seawater.density(60, 0));
            assertEqualsWithDelta(977.7670843, seawater.density(70, 0));
            assertEqualsWithDelta(971.7592128, seawater.density(80, 0));
            assertEqualsWithDelta(965.2456323, seawater.density(90, 0));
            assertEqualsWithDelta(958.267, seawater.density(100, 0));
            assertEqualsWithDelta(950.8527963, seawater.density(110, 0));
            assertEqualsWithDelta(943.0213248, seawater.density(120, 0));
            //
            assertEqualsWithDelta(1064.06, seawater.density(0, 0.08));
            assertEqualsWithDelta(1062.2, seawater.density(10, 0.08));
            assertEqualsWithDelta(1059.4908435072, seawater.density(20, 0.08));
            assertEqualsWithDelta(1056.0359993912, seawater.density(30, 0.08));
            assertEqualsWithDelta(1051.9287436288, seawater.density(40, 0.08));
            assertEqualsWithDelta(1047.25092942, seawater.density(50, 0.08));
            assertEqualsWithDelta(1042.0732331648, seawater.density(60, 0.08));
            assertEqualsWithDelta(1036.4551544632, seawater.density(70, 0.08));
            assertEqualsWithDelta(1030.4450161152, seawater.density(80, 0.08));
            assertEqualsWithDelta(1024.0799641208, seawater.density(90, 0.08));
            assertEqualsWithDelta(1017.38596768, seawater.density(100, 0.08));
            assertEqualsWithDelta(1010.3778191928, seawater.density(110, 0.08));
            assertEqualsWithDelta(1003.0591342592, seawater.density(120, 0.08));
        });
    });

});

describe('Liquid', function() {

    var liquid = new Liquid(1.03, 13.92);

    describe('#density()', function() {
        it('should return the density', function() {
            assertEqualsWithDelta(1.03, liquid.density());
        });
    });

    describe('#mass()', function() {
        it('should return the mass', function() {
            assertEqualsWithDelta(14.3376, liquid.mass());
        });
    });

    describe('#volume()', function() {
        it('should return the volume', function() {
            assertEqualsWithDelta(13.92, liquid.volume());
        });
    });

});

describe('Solid', function(){

    var volume = 100.25;
    var mass = 200.5;
    var solid = new Solid(volume, mass);

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
            assertEqualsWithDelta(0.0, new Solid(1, 1000).buoyancy(1000));
            assertEqualsWithDelta(500, new Solid(1, 500).buoyancy(1000));
            assertEqualsWithDelta(-1000, new Solid(1, 2000).buoyancy(1000));
        });
    });

});

describe('Cylinder', function() {

    var capacity = 0.012;
    var density = 7850;
    var mass1 = 15;
    var emptyMass = 0;
    var fullMass = 3.38;

    var emptyCylinder = new Cylinder(capacity, density, mass1, emptyMass);
    var fullCylinder = new Cylinder(capacity, density, mass1, fullMass);

    describe('#volume()', function() {
        it('should return the volume provided', function(){
            assertEqualsWithDelta(0.01391, emptyCylinder.volume());
            assertEqualsWithDelta(0.01391, fullCylinder.volume());
        });
    });

    describe('#mass()', function() {
        it('should return the mass provided', function(){
            assertEqualsWithDelta(15, emptyCylinder.mass());
            assertEqualsWithDelta(18.38, fullCylinder.mass());
        });
    });

    describe('#density()', function() {
        it('should return the density', function(){
            assertEqualsWithDelta(1078.2967, emptyCylinder.density());
            assertEqualsWithDelta(1321.2728, fullCylinder.density());
        });
    });

    describe('#buoyancy()', function() {
        it('should return the buoyancy', function(){
            assertEqualsWithDelta(-0.6718, emptyCylinder.buoyancy(1030));
            assertEqualsWithDelta(-4.0518, fullCylinder.buoyancy(1030));
        });
    });

});

describe('Composite', function(){

    var s1 = new Solid(1,2);
    var s2 = new Solid(3,4);
    var composite = new Composite([s1, s2]);

    describe('#volume()', function() {
        it('should return the volume provided', function(){
            assertEqualsWithDelta(4, composite.volume());
        });
    });

    describe('#mass()', function() {
        it('should return the mass provided', function(){
            assertEqualsWithDelta(6, composite.mass());
        });
    });

    describe('#density()', function() {
        it('should return the density', function(){
            assertEqualsWithDelta(1.5, composite.density());
        });
    });

    describe('#buoyancy()', function() {
        it('should return the buoyancy', function(){
            assertEqualsWithDelta(3994, composite.buoyancy(1000));
        });
    });

});

describe('DryAir', function(){

    var air = new DryAir();

    describe('#density()', function() {
        it('should return the density', function(){
            assertEqualsWithDelta(1.2250, air.density(288.15, 101325));
            assertEqualsWithDelta(1.1839, air.density(298.15, 101325));
        });
    });

});

describe('Gas', function() {

    var gas = new Gas(1.2, 0.012, 230);

    describe('#density()', function() {
        it('should return the density', function() {
            assertEqualsWithDelta(1.2, gas.density());
        });
    });

    describe('#mass()', function() {
        it('should return the mass', function() {
            assertEqualsWithDelta(3.312, gas.mass());
        });
    });

    describe('#volume()', function() {
        it('should return the volume', function() {
            assertEqualsWithDelta(0.012, gas.volume());
        });
    });

});

describe('Sample', function(){
    it('execution', function() {
        var seawater = new Seawater();
        var solid = new Composite([new Solid(1.0,1000.0), new Solid(0.1,1500.0)]);
        var density = seawater.density(20.5, 0.08);
        console.log("density: " + solid.buoyancy(density));
    });
});

var assertEqualsWithDelta = function (expected, value) {
    assert.equal(true, delta(expected, value), (expected + ' == ' + value));
};

var delta = function (expected, value) {
    return Math.abs(expected - value) < DELTA;
};
