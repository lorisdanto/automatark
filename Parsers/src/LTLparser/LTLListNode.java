package LTLparser;

import java.io.*;
import java.util.*;

public class LTLListNode extends FormulaNode {
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
    
    public Set<String> returnLeafNodes(Set<String> set, boolean returnExp) {
    	 Iterator it = myLTLs.iterator();
        try {
            while (it.hasNext()) {
                Set<String> tempSet = new HashSet<String>();
               set.addAll( ((LTLNode)it.next()).returnLeafNodes(tempSet, returnExp));
            }
        } catch (NoSuchElementException ex) {
            System.err.println("unexpected NoSuchElementException in DeclListNode.print");
            System.exit(-1);
        }
        
        return set;
    }

    private List<LTLNode> myLTLs;
}