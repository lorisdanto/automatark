package LTLparser;

import java.io.*;
import java.util.*;

public abstract class LTLNode extends FormulaNode {
    abstract public Set<String> returnLeafNodes(Set<String> set, boolean returnExp);
}