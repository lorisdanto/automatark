LTLparser: 

Main Class: LTLparser/LTLParserProvider.java
Unit Test:  testLTL/testLTL.java

Grammar, precedence and associativity of LTL can be found in src/LTLparser/LTLparser.grammar
How to use the program (command-line arguments and methods) can be found in the comment section of LTLParserProvider.java

Structure:
/*
 * 	Subclass              Kids
 * 	------------------------------
 *
 * 	LTLListNode		 	 LTLNode
 * 
 * 	LTLNode:
 * 	BinaryLTLNode         LTLNode LTLNode
 * 		AndNode
 * 		OrNode
 * 		XorNode
 * 		ImplicationNode
 * 		EquivalenceNode
 * 		UntilNode
 * 		WeakUntilNode
 * 		ReleaseNode
 * 		StrongReleaseNode
 * 
 * 	UnaryLTLNode     	 LTLNode
 * 		NextNode
 * 		AlwaysNode
 * 		EventuallyNode
 * 		NegationNode
 * 		
 * 	TypeNode (these are leaf nodes):
 * 		TrueNode		--none--
 * 		FalseNode		--none--
 * 		IdNode			--none--
 * 		ExpNode         --none--
 * 
 */


