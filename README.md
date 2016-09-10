# PokemonCatcher

### Instructions
- Dependencies: Maven (see https://maven.apache.org/install.html)
- Compile program
```sh
mvn compile
```	
- Test program giving a sequence in standard input
```sh
mvn exec:java -Dexec.mainClass="com.pm.pokemoncatcher.PokemonCatcher
```
- Test program with JUnit tests, using the input file "src/test/resources/test.csv"
```sh
mvn test
```
