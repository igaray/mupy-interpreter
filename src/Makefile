all:
	java -jar jlex-1.2.6.jar MuPy.jlex
	javac MuPy.jlex.java
	java java_cup.Main MuPy.cup
	javac parser.java
	javac sym.java
	javac SymbolTable.java

clean:
	rm *.class
	rm MuPy.jlex.java
	rm parser.java
	rm sym.java
