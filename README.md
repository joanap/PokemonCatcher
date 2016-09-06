# PokemonCatcher
Instructions

1. Install Maven (see https://maven.apache.org/install.html)

2. Test program giving a sequence in standard input:
	mvn compile
	mvn exec:java -Dexec.mainClass="com.pm.pokemoncatcher.PokemonCatcherTester

3. Test program with JUnit tests, using the input file "src/test/resources/test.csv"
	mvn test
