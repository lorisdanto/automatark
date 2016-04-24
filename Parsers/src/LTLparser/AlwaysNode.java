package LTLparser;

import java.io.*;

public class AlwaysNode extends UnaryLTLNode {
    public AlwaysNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(G");
        myLTL.unparse(p, 0);
        p.print(")");
    }

	@Override
	public void toString(StringBuilder s, int indent) {
		s.append("(G");
        myLTL.toString(s, 0);
        s.append(")");
	}
}