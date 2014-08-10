# Nano Hydrostatic Library

## Feature

* Compute the *density* of solids made of [heterogeneous materials](https://en.wikipedia.org/wiki/Density)
* Compute the *buoyancy* of heterogeneous solids fully immersed in [seawater](https://en.wikipedia.org/wiki/Seawater)

The library uses the [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units).
The *buoyancy* and *mass* are expressed in `kg`, the *density* in `kg•m^-3`, the *volume* in `m^-3` and temperature in `°C`.

The *buoyancy* in seawater is computed based on the seawater density model described in:
*Sharqawy, Mostafa H., John H. Lienhard V and Syed M. Zubair. "[The thermophysical properties of seawater: A review of existing correlations and data.](http://hdl.handle.net/1721.1/69157)" Desalination and Water Treatment, 16 (April 2010) 354–380*.

The paper is available on [MIT Open Access](http://hdl.handle.net/1721.1/69157) and usable according to Creative Commons [by-nc-sa](https://creativecommons.org/licenses/by-nc-sa/3.0/) license. The Equation implemented is the *Eq. (8)* (referenced in the paper) without modification.

The model is valid for a temperature range `0 < t < 180 °C` and salinity range `0 < s < 0.16 kg/kg` with a `±0.1%` accuracy.

## Getting the library

TBD

## Using the library

Once the library is accessible in your classpath, you shall use the `SolidFactory` factory to build solids as required. 

The example below shows the creation of a solid composed of `1 m^3` with density `1000 kg•m^-3` and `0.1 m^3` with density `1500 kg•m^-3`.

```
Solid<BigDecimal> solid = new SolidBuilder()
    .add(big("1"), big("1000"))
    .add(big("0.1"), big("1500"))
    .build();
```

The mass and volume of this solid can be computed like the following:

```
solid.mass();
solid.volume();
```

In order to compute the buoyancy of the solid fully immersed in seawater at `20.5°C` with salinity `0.08 kg/kg`, the liquid model must be built as below:

```
Liquid<BigDecimal> seaWater = SaltedLiquidFactory.build(big("0.08"));
```

Then compute the buoyancy as below:

```
solid.buoyancy(seaWater.density(big("20.5")));
```

The library uses `java.math.BigDecimal` for its implementation, you may static import the method `org.wildbits.hydro.utils.Utils#big` as a syntactic sugar making building `BigDecimal` instances easy.