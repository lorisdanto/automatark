package LTLparser;

import java.io.*;
import java.util.*;

/*
 * 	Subclass              Kids
 * 	------------------------------
 * 	ProgramNode			 LTLListNode
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
 * 		DualNode
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

public abstract class FormulaNode { 
    // every subclass must provide an unparse operation
    abstract public void unparse(PrintWriter p, int indent);
    // set second argument to false if you wish to exclude embedded expressions
    abstract public Set<String> returnLeafNodes(Set<String> set, boolean returnExp);

    // this method can be used by the unparse methods to do indenting
    protected void doIndent(PrintWriter p, int indent) {
        for (int k=0; k<indent; k++) p.print(" ");
    }
    protected void doIndent(StringBuilder s, int indent) {
        for (int k=0; k<indent; k++) s.append(" ");
    }
	abstract public void toString(StringBuilder s, int indent);
}

