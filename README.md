# Nano Hydrostatic Library

## Features

* Compute the *density* of solids made of [heterogeneous materials](https://en.wikipedia.org/wiki/Density)
* Compute the *buoyancy* of heterogeneous solids fully immersed in [seawater](https://en.wikipedia.org/wiki/Seawater)
* Packaged as OSGI bundle

The library uses the [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units).
The *buoyancy* and *mass* are expressed in `kg`, the *density* in `kg•m^-3`, the *volume* in `m^-3` and temperature in `°C`.

The *buoyancy* in seawater is computed based on the seawater density model described in:
*Sharqawy, Mostafa H., John H. Lienhard V and Syed M. Zubair. "[The thermophysical properties of seawater: A review of existing correlations and data.](http://hdl.handle.net/1721.1/69157)" Desalination and Water Treatment, 16 (April 2010) 354–380*.

The paper is available on [MIT Open Access](http://hdl.handle.net/1721.1/69157) and usable according to Creative Commons [by-nc-sa](https://creativecommons.org/licenses/by-nc-sa/3.0/) license. The Equation implemented is the *Eq. (8)* (referenced in the paper) without modification.

The model is valid for a temperature range `0 < t < 180 °C` and salinity range `0 < s < 0.16 kg/kg` with a `±0.1%` accuracy.

## Getting the library

TBD

## Using the library

The library can be loaded directly in an OSGI container or used in a standalone application.

### OSGI container

Once the bundle is activated, the service `SolidBuilderFactory` is registered and allows to create instances of `SolidBuilder`.

### Standalone

Once the library is accessible in the application classpath, an instance of `SolidBuilderFactoryImpl` must be created in order to create instances of `SolidBuilder`.

The class `org.wildbits.hydro.sample.Sample` shows how to create a solid composed of `1 m^3` with density `1000 kg•m^-3` and `0.1 m^3` with density `1500 kg•m^-3` and compute its buoyancy in seawater with salinity of `0.08 kg/kg` at `20.5 °C`.

The first step is to get a solid builder

```
SolidBuilderFactory sbf = new SolidBuilderFactoryImpl();
SolidBuilder sb = sbf.getInstance();
```

Then build the solid

```
Solid<BigDecimal> solid = sb.add(big("1"), big("1000")).add(big("0.1"), big("1500")).build();
```

and the water model

```
Liquid<BigDecimal> seaWater = SaltedLiquidFactory.build(big("0.08"));
```

finally the buoyancy and other hydrostatic properties can be computed as follow

```
BigDecimal mass = solid.mass();
BigDecimal volume = solid.volume();
BigDecimal density = solid.density(big("20.5"));
BigDecimal waterDensity = seaWater.density(big("20.5"));
BigDecimal buoyancy = (waterDensity != null) ? solid.buoyancy(waterDensity) : null;
```

## Notes

The library uses `java.math.BigDecimal` for its implementation, you may static import the method `org.wildbits.hydro.utils.Utils#big` as a syntactic sugar making building `BigDecimal` instances easy.