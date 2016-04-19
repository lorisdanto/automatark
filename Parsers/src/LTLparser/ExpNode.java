package LTLparser;

import java.io.*;
import java.util.*;

public class ExpNode extends LTLNode {
    public ExpNode(int lineNum, int charNum, String strVal) {
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
        p.print(" ");
        p.print(myStrVal);
        p.print(" ");
    }
    
    public Set<String> returnLeafNodes(Set<String> set, boolean returnExp) {
        if (returnExp){
            set.add(myStrVal);
        }
    	return set;
    }

    private int myLineNum;
    private int myCharNum;
    private String myStrVal;
	@Override
	public void toString(StringBuilder s, int indent) {
		s.append(" ");
        s.append(myStrVal);
        s.append(" ");
	}
}
