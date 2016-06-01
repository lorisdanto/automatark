package RegexParser;

import java.io.PrintWriter;

public class DotNode extends CharNode{
	
	public DotNode(char val) {
		super(val);
	}	
	@Override
	public void unparse(PrintWriter p) {
		p.print("Dot");
		//p.print(myChar);
	}

	@Override
	public void toString(StringBuilder s) {
		s.append("Dot");
		//s.append(myChar);
	}
}
