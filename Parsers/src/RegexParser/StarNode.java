package RegexParser;

import java.io.PrintWriter;

public class StarNode extends RegexNode {
	public StarNode(RegexNode r1) {
		myRegex1 = r1;

	}

	public RegexNode getMyRegex1() {
		return myRegex1;
	}

	@Override
	public void unparse(PrintWriter p) {
		p.print("(");
		myRegex1.unparse(p);
		p.print(")*");
	}

	@Override
	public void toString(StringBuilder s) {
		s.append("(");
		myRegex1.toString(s);
		s.append(")*");
	}

	// one kid
	protected RegexNode myRegex1;

}
