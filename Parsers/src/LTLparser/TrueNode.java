package LTLparser;

import java.io.*;
import java.util.*;

public class TrueNode extends LTLNode {
    public TrueNode(int lineNum, int charNum) {
        myLineNum = lineNum;
        myCharNum = charNum;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("true");
    }
    
    public Set<String> returnLeafNodes(Set<String> set, boolean returnExp) {
        set.add("true");
    	return set;
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

    private int myLineNum;
    private int myCharNum;
	@Override
	public void toString(StringBuilder s, int indent) {
		s.append("true");
	}
}