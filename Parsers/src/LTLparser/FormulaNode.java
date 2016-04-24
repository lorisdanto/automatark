package LTLparser;

import java.io.*;
import java.util.*;

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

public abstract class FormulaNode { 
    // every subclass must provide an unparse operation, currently the default indent is 0
    abstract public void unparse(PrintWriter p, int indent);
    // set second argument to false if you wish to exclude embedded expressions
    abstract public Set<String> returnLeafNodes(Set<String> set, boolean returnExp);
    
    // set second argument to false if you wish to exclude embedded expressions
    public Set<String> returnLeafNodes(){
    	Set<String> set = new HashSet<String>();
    	returnLeafNodes(set,true);
    	return set;
    }

    // this method can be used by the unparse methods to do indenting
    protected void doIndent(PrintWriter p, int indent) {
        for (int k=0; k<indent; k++) p.print(" ");
    }
    protected void doIndent(StringBuilder s, int indent) {
        for (int k=0; k<indent; k++) s.append(" ");
    }
	abstract public void toString(StringBuilder s, int indent);
}

