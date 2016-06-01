package RegexParser;

import java.io.PrintWriter;

public class EndAnchorNode extends CharNode{
	
	public EndAnchorNode(char val) {
		super(val);
	}	
	@Override
	public void unparse(PrintWriter p) {
		//End of String
		p.print("EOS");
		//p.print(myChar);
	}

	@Override
	public void toString(StringBuilder s) {
		//End of String
		s.append("EOS");
		//s.append(myChar);
	}
}
