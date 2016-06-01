package RegexParser;

import java.io.PrintWriter;

public class NormalCharNode extends CharNode{

	public NormalCharNode(char val) {
		super(val);
	}
	public void unparse(PrintWriter p) {
		p.print("Char:");
		p.print(myChar);
	}

	public void toString(StringBuilder s) {
		s.append("Char:");
		s.append(myChar);
	}

}
