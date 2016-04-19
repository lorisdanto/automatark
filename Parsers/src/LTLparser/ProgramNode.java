package LTLparser;

import java.io.*;
import java.util.*;

public class ProgramNode extends FormulaNode {
    public ProgramNode(LTLListNode L) {
    	myLTLList = L;
    }

    
    public void unparse(PrintWriter p, int indent) {
    	myLTLList.unparse(p, indent);
    	p.print(System.getProperty("line.separator"));
    }
    
    public Set<String> returnLeafNodes(Set<String> set, boolean returnExp) {
    	return myLTLList.returnLeafNodes(set, returnExp);
    }

    
    private LTLListNode myLTLList;


	@Override
	public void toString(StringBuilder s, int indent) {
		myLTLList.toString(s, indent);
    	s.append(System.getProperty("line.separator"));
	}


}