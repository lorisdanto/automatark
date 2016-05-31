package RegexParser;

import java.io.*;
import java.util.*;

public abstract class FormulaNode { 
    // every subclass must provide an unparse operation and toString method
    abstract public void unparse(PrintWriter p);
	abstract public void toString(StringBuilder s);
}

