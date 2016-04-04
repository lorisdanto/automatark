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
 * 
 */

abstract class FormulaNode { 
    // every subclass must provide an unparse operation
    abstract public void unparse(PrintWriter p, int indent);

    // this method can be used by the unparse methods to do indenting
    protected void doIndent(PrintWriter p, int indent) {
        for (int k=0; k<indent; k++) p.print(" ");
    }
}

class ProgramNode extends FormulaNode {
    public ProgramNode(LTLListNode L) {
    	myLTLList = L;
    }

    
    public void unparse(PrintWriter p, int indent) {
    	myLTLList.unparse(p, indent);
    }

    
    private LTLListNode myLTLList;
}

class LTLListNode extends FormulaNode {
    public LTLListNode(List<LTLNode> S) {
    	myLTLs = S;
    }
 
    public void unparse(PrintWriter p, int indent) {
        Iterator it = myLTLs.iterator();
        try {
            while (it.hasNext()) {
                ((LTLNode)it.next()).unparse(p, indent);
                p.println();
            }
        } catch (NoSuchElementException ex) {
            System.err.println("unexpected NoSuchElementException in DeclListNode.print");
            System.exit(-1);
        }
    }

    private List<LTLNode> myLTLs;
}

abstract class LTLNode extends FormulaNode {
    
}

class TrueNode extends LTLNode {
    public TrueNode(int lineNum, int charNum) {
        myLineNum = lineNum;
        myCharNum = charNum;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("true");
    }

    private int myLineNum;
    private int myCharNum;
}

class FalseNode extends LTLNode {
    public FalseNode(int lineNum, int charNum) {
        myLineNum = lineNum;
        myCharNum = charNum;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("false");
    }

    private int myLineNum;
    private int myCharNum;
}

class IdNode extends LTLNode {
    public IdNode(int lineNum, int charNum, String strVal) {
        myLineNum = lineNum;
        myCharNum = charNum;
        myStrVal = strVal;
    }
    
    /**
     * Return the name of this ID.
     */
    public String getName() {
        return myStrVal;
    }
    
    /**
     * Return the line number for this ID.
     */
    public int getLineNum() {
        return myLineNum;
    }
    
    /**
     * Return the char number for this ID.
     */
    public int getCharNum() {
        return myCharNum;
    }    

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        p.print(myStrVal);
        p.print(")");
    }

    private int myLineNum;
    private int myCharNum;
    private String myStrVal;
}

abstract class UnaryLTLNode extends LTLNode {
    public UnaryLTLNode(LTLNode l1) {
    	myLTL = l1;
    }  
    
    protected LTLNode myLTL;
}

abstract class BinaryLTLNode extends LTLNode {
    public BinaryLTLNode(LTLNode l1, LTLNode l2) {
        myLTL1 = l1;
        myLTL2 = l2;
    }
    
    // two kids
    protected LTLNode myLTL1;
    protected LTLNode myLTL2;
}

class AndNode extends BinaryLTLNode {
    public AndNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" && ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}

class OrNode extends BinaryLTLNode {
    public OrNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" || ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}

class XorNode extends BinaryLTLNode {
    public XorNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" XOR ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}

class ImplicationNode extends BinaryLTLNode {
    public ImplicationNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" -> ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}

class EquivalenceNode extends BinaryLTLNode {
    public EquivalenceNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" <-> ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}

class UntilNode extends BinaryLTLNode {
    public UntilNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" U ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}

class WeakUntilNode extends BinaryLTLNode {
    public WeakUntilNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" W ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}

class DualNode extends BinaryLTLNode {
    public DualNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" V ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}

class ReleaseNode extends BinaryLTLNode {
    public ReleaseNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" R ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}

class StrongReleaseNode extends BinaryLTLNode {
    public StrongReleaseNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" M ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}

class NextNode extends UnaryLTLNode {
    public NextNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(N");
        myLTL.unparse(p, 0);
        p.print(")");
    }
}

class AlwaysNode extends UnaryLTLNode {
    public AlwaysNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(G");
        myLTL.unparse(p, 0);
        p.print(")");
    }
}

class EventuallyNode extends UnaryLTLNode {
    public EventuallyNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(F");
        myLTL.unparse(p, 0);
        p.print(")");
    }
}

class NegationNode extends UnaryLTLNode {
    public NegationNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(!");
        myLTL.unparse(p, 0);
        p.print(")");
    }
}


