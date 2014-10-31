# WorldWind Geo Graph by Matt Eskridge

## Screenshot

![](https://raw.githubusercontent.com/matteskridge/worldwind-geo-graph/master/meta/images/GeoGraph1.png)

## About WWGG

WorldWind Geo Graph is a visual demonstration of the Dijkstra
shortest path algorithm written in Java.

## Running WWGG

This repository contains a project for the JetBrains IntelliJ IDEA
development enviornment. It is recommended to use that IDE for development.
The project is configured to run automatically in IDEA under Windows
and Mac. This program requires that all of the libraries within the
lib folder be configured to work with the project, however.

For running this project under Linux, or if any issues are encountered
launching the software, the full set of libraries can be downloaded
from the WorldWind website.

http://worldwind.arc.nasa.gov/java/

## License

This project is licensed under the MIT license.

~~~~~
Copyright (c) 2014, Matt Eskridge

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
~~~~~

Note that this license does not apply to the included libraries,
namely the WorldWind SDK.

## Credits

This program's main source code was developed by Matt Eskridge.
Credit goes to NASA for providing their worldwind 3D globe technology,
Edsger Dijkstra for development of the Dijkstra algorithm.

The Dijkstra algorithm was uniquely translating into Java for the
purpose of this project, using as source material the pseudocode
for Dijkstra from Wikipedia, and the lectures and notes
of Ajinkya Kulkarni. Testing of the implemented algorithm was performed
by Matt Eskridge.