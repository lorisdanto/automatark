package LTLparser;

import java.io.*;
import java.util.*;

public class ProgramNode extends FormulaNode {
    public ProgramNode(LTLListNode L) {
    	myLTLList = L;
    }

    
    public void unparse(PrintWriter p, int indent) {
    	myLTLList.unparse(p, indent);
    	p.println();
    }
    
    public Set<String> returnLeafNodes(Set<String> set, boolean returnExp) {
    	return myLTLList.returnLeafNodes(set, returnExp);
    }

    
    private LTLListNode myLTLList;
}