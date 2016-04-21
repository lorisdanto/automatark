package LTLparser;

import java.util.*;

public abstract class LTLNode extends FormulaNode {
    abstract public Set<String> returnLeafNodes(Set<String> set, boolean returnExp);
    abstract public LTLNode getMyLTL1();
    abstract public LTLNode getMyLTL2();
}