# Reflex Entity Shifter
A command line application to shift all entities in a Reflex Arena .map file by a specified amount in each axis.\
***Author:*** *Mauro Merconchini*

## Getting Started
Everything you will need to get the application up and running.

### Prerequisite
**Java 1.6.0 or above.** If you don't have it already, you can [download it here](https://www.java.com/en/download/)

### Installation
Download the [latest release](https://github.com/mauro-merconchini/ReflexEntityShifter/releases) and place it in a directory of your choosing.

## How to Use
After placing a *Reflex Arena* **.map** file in the same directory as the executable, you can perform the shift by running the application in the command line. The syntax is as follows:

```
ReflexEntityShifter <myMap>.map shift-X shift-Y shift-Z
```

Where ```shift-X```, ```shift-Y```, and ```shift-Z``` are the values by which you wish to shift each entity.

For example, running the command

```
ReflexEntityShifter testMap.map 256 128 64
```

would shift the position of every entity in the map file **testMap.map** by 256 units on the X axis, 128 units on the Y axis, and 64 units on the Z axis.

The resultant map file will be placed in the same directory as the input **.map** file have the following nomenclature:

```
<myMap>.map ---> <myMap>_EntityShifted.map
```

## Built With
* [Launch4j](http://launch4j.sourceforge.net/) - A cross-platform Java executable wrapper

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details