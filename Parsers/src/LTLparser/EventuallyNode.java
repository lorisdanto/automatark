package LTLparser;

import java.io.*;
import java.util.*;



public class EventuallyNode extends UnaryLTLNode {
    public EventuallyNode(LTLNode l1) {
        super(l1);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(F");
        myLTL.unparse(p, 0);
        p.print(")");
    }
}