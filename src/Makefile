mupy:
	java -jar jlex-1.2.6.jar MuPy.jlex
	java java_cup.Main MuPy.cup
	javac *.java -d .

standalone:
	java -jar jlex-1.2.6.jar MuPy_standalone.jlex
	javac MuPy_standalone.jlex.java -d .
	jar cfe standalone.jar standalone.Main standalone/
	rm MuPy_standalone.jlex.java
	rm -rf standalone/

clean:
	rm -f *.class
	rm -fr MuPy/
	rm -f MuPu_standalone.jlex.java
	rm -f MuPy.jlex.java
	rm -f parser.java sym.java
