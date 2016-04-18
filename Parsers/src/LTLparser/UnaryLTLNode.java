package LTLparser;

import java.io.*;
import java.util.*;

public abstract class UnaryLTLNode extends LTLNode {
    public UnaryLTLNode(LTLNode l1) {
    	myLTL = l1;
    }
    public Set<String> returnLeafNodes(Set<String> set, boolean returnExp) {
    	return myLTL.returnLeafNodes(set, returnExp);
    }
    
    protected LTLNode myLTL;
}