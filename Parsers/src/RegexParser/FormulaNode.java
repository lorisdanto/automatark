package RegexParser;

import java.io.*;
import java.util.*;

public abstract class FormulaNode { 
    // every subclass must provide an unparse operation, currently the default indent is 0
    abstract public void unparse(PrintWriter p);
	abstract public void toString(StringBuilder s);
}

