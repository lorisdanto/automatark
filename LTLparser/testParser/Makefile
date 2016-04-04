###
# This Makefile can be used to make a parser for the cats language
# (parser.class) and to make a program (P3.class) that tests the parser and
# the unparse methods in ast.java.
#
# make clean removes all generated files.
#
###

JC = javac
CP = ~cs536-1/public/tools/deps_src/java-cup-11b.jar:~cs536-1/public/tools/deps_src/java-cup-11b-runtime.jar:~cs536-1/public/tools/deps:.
CP2 = ~cs536-1/public/tools/deps:.

Test2.class: Test2.java parser.class Yylex.class formulae.class
	$(JC)    Test2.java

parser.class: parser.java formulae.class Yylex.class ErrMsg.class
	$(JC)      parser.java

parser.java: LTLparser.cup
	java   java_cup.Main < LTLparser.cup

Yylex.class: LTLparser.jlex.java sym.class ErrMsg.class
	$(JC)   LTLparser.jlex.java

formulae.class: formulae.java
	$(JC)  formulae.java

LTLparser.jlex.java: LTLparser.jlex sym.class
	java    JLex.Main LTLparser.jlex

sym.class: sym.java
	$(JC)    sym.java
 
sym.java: LTLparser.cup
	java    java_cup.Main < LTLparser.cup

ErrMsg.class: ErrMsg.java
	$(JC) ErrMsg.java

##test
test:
	java   Test2 allTokens.in allTokens.out
	

###
# clean
###
clean:
	rm -f *~ *.class parser.java LTLparser.jlex.java sym.java
