all:
	java -jar jlex-1.2.6.jar MuPy.jlex
	java java_cup.Main MuPy.cup
	javac MuPy.jlex.java
	javac parser.java
	javac sym.java
	javac OurSymbol.java
	javac SymbolTable.java

standalone:
	java -jar jlex-1.2.6.jar MuPy_standalone.jlex
	javac MuPy_standalone.jlex.java -d .
	jar cfe standalone.jar standalone.Main standalone/
	rm MuPy_standalone.jlex.java
	rm -rf standalone/

clean:
	rm *.class
	rm -f MuPu_standalone.jlex.java
	rm MuPy.jlex.java
	rm parser.java
	rm sym.java
