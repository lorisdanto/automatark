package LTLparser;

import java.util.*;

public abstract class UnaryLTLNode extends LTLNode {
	public UnaryLTLNode(LTLNode l1) {
		myLTL = l1;
	}

	public Set<String> returnLeafNodes(Set<String> set, boolean returnExp) {
		return myLTL.returnLeafNodes(set, returnExp);
	}

	public LTLNode getMyLTL1() {
		return myLTL;
	}

	public LTLNode getMyLTL2() {
		System.out.println("This is an UnaryLTLNode");
		return null;
	}
	
	protected LTLNode myLTL;
}