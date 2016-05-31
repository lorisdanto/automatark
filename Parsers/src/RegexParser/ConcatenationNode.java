package RegexParser;

import java.io.PrintWriter;

public class ConcatenationNode extends RegexNode{
	public ConcatenationNode(RegexNode r1, RegexNode r2) {
		myRegex1 = r1;
		myRegex2 = r2;
	}

	public RegexNode getMyRegex1() {
		return myRegex1;
	}

	public RegexNode getMyRegex2() {
		return myRegex2;
	}

	@Override
	public void unparse(PrintWriter p) {
		p.print("(");
		myRegex1.unparse(p);
		myRegex2.unparse(p);
		p.print(")");

	}

	@Override
	public void toString(StringBuilder s) {
		s.append("(");
		myRegex1.toString(s);
		myRegex2.toString(s);
		s.append(")");
	}

	// two kids
	protected RegexNode myRegex1;
	protected RegexNode myRegex2;
}