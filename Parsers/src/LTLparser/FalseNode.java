package LTLparser;

import java.io.*;
import java.util.*;

public class FalseNode extends LTLNode {
    public FalseNode(int lineNum, int charNum) {
        myLineNum = lineNum;
        myCharNum = charNum;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("false");
    }
    
    public Set<String> returnLeafNodes(Set<String> set, boolean returnExp) {
        set.add("false");
    	return set;
    }

    private int myLineNum;
    private int myCharNum;
}