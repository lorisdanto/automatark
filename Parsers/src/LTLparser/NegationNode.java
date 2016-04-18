package LTLparser;

import java.io.*;
import java.util.*;


public class NegationNode extends UnaryLTLNode {
    public NegationNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(!");
        myLTL.unparse(p, 0);
        p.print(")");
    }
}
