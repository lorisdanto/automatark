package LTLparser;

import java.io.*;


public class NextNode extends UnaryLTLNode {
    public NextNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(X");
        myLTL.unparse(p, 0);
        p.print(")");
    }

	@Override
	public void toString(StringBuilder s, int indent) {
		s.append("(X");
        myLTL.toString(s, 0);
        s.append(")");
	}
}