# bean-benchmarks

Benchmark app for several ways of implementing sort-of Java Beans in Clojure.

| Implementation    | Size on disk | Mean execution time | Standard deviation |
|-------------------|--------------|---------------------|--------------------|
| Java              | 87554 KB     | 50.5 s              | 0.410 s            |
| Java (primitives) | 70536 KB     | 26.0 s              | 0.156 s            |
| deftype           | 87554 KB     | 50.5 s              | 0.204 s            |
| gen-class         | 90067 KB     | 39.6 s              | 0.106 s            |
| clj-bean          | 85185 KB     | 36.2 s              | 0.480 s            |

Those results are for 500000 objects on a 4.2 GHz i5-2500K.

## Usage

    $ java -jar bean-benchmarks-0.1.0.jar [options]

## License

Copyright © 2016 Joel Wilsson

Distributed under the MIT license.
