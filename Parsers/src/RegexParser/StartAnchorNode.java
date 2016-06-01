package RegexParser;

import java.io.PrintWriter;

public class StartAnchorNode extends CharNode{
	
	public StartAnchorNode(char val) {
		super(val);
	}	
	@Override
	public void unparse(PrintWriter p) {
		//Start of String
		p.print("SOS");
		//p.print(myChar);
	}

	@Override
	public void toString(StringBuilder s) {
		//Start of String
		s.append("SOS");
		//s.append(myChar);
	}
}
