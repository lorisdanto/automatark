package LTLparser;

import java.io.*;
import java.util.*;

public class AlwaysNode extends UnaryLTLNode {
    public AlwaysNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(G");
        myLTL.unparse(p, 0);
        p.print(")");
    }
}