package LTLparser;

import java.io.*;
import java.util.*;

public class WeakUntilNode extends BinaryLTLNode {
    public WeakUntilNode(LTLNode l1, LTLNode l2) {
        super(l1, l2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLTL1.unparse(p, 0);
        p.print(" W ");
        myLTL2.unparse(p, 0);
        p.print(")");
    }
}