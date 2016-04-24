package LTLparser;

import java.io.*;



public class EventuallyNode extends UnaryLTLNode {
    public EventuallyNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(F");
        myLTL.unparse(p, 0);
        p.print(")");
    }

	@Override
	public void toString(StringBuilder s, int indent) {
		s.append("(F");
        myLTL.toString(s, 0);
        s.append(")");
	}
}