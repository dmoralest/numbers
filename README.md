[![Build Status][travis-badge]][travis-badge-url]

Number translator
=====================================================

### Build
Execute the following command from the parent directory to build the jar file:
```
./gradlew clean build
```

### Run
From the parent directory run:
```
java -jar build/libs/numbers-all.jar
```

Once the application starts up, type `translate 15` to show the number's name. You should notice `Fifteen` in the terminal.
```
numbers:>translate 15
Fifteen
numbers:>
```

### Coverage report
Coverage report can be generated running:
```
./gradlew jacocoTestReport
```

Then the report file will be found at build/jacocoHtml/index.html

[travis-badge]: https://travis-ci.org/dmoralest/numbers.svg?branch=master
[travis-badge-url]: https://travis-ci.org/dmoralest/numbers/