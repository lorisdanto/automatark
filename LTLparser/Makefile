
# define the java compiler to be used and the flags
JC = javac
FLAGS = -g -cp $(CP)
CP = ~cs536-1/public/tools/deps:.

Test1.class: Test1.java Yylex.class sym.class
	$(JC) $(FLAGS) Test1.java

Yylex.class: LTLparser.jlex.java ErrMsg.class sym.class
	$(JC) $(FLAGS) LTLparser.jlex.java

LTLparser.jlex.java: LTLparser.jlex sym.class
	java -cp $(CP) JLex.Main LTLparser.jlex

sym.class: sym.java
	$(JC) $(FLAGS) sym.java

ErrMsg.class: ErrMsg.java
	$(JC) $(FLAGS) ErrMsg.java

test:
	java -cp $(CP) Test1 	


clean:
	rm -f *~ *.class cats.jlex.java

cleantest:
	rm -f allTokens.out
	
