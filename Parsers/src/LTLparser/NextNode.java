package LTLparser;

import java.io.*;
import java.util.*;

public class NextNode extends UnaryLTLNode {
    public NextNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(X");
        myLTL.unparse(p, 0);
        p.print(")");
    }
}