Explanation on how to use the library:

use the following command to generate yylex file:

```
java -jar jflex-1.6.1.jar automata.jlex
```

use the following command to generate sym and parser file:

```
$java -jar java-cup-11b.jar automata.cup
```

when you want some ambiguity in the grammar and still generate the parser, use the following command:

```
$java -jar java-cup-11b.jar -expect SOMENUMBER mona.cup
```

substitute SOMENUMBER with the number of warnings you need to tolerate, see the -expect argument in the manual:

```
https://www.cs.princeton.edu/~appel/modern/java/CUP/manual.html
```



