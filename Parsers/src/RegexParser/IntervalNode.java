package RegexParser;

import java.io.PrintWriter;

public class IntervalNode extends RegexNode {
	public IntervalNode(CharNode c1) {
		mode = "single";
		myChar1 = c1;
	}

	public IntervalNode(CharNode c1, CharNode c2) {
		mode = "range";
		myChar1 = c1;
		myChar2 = c2;

	}

	@Override
	public void unparse(PrintWriter p) {
		if (mode.equals("single")) {
			myChar1.unparse(p);
			p.print(" ");
		} else {
			myChar1.unparse(p);
			p.print("-");
			myChar2.unparse(p);
			p.print(" ");
		}
	}

	public String getMode() {
		return mode;
	}

	public CharNode getChar1() {
		return myChar1;
	}

	/**
	 * be sure to use getMode() to check whether this is range mode getChar2 is
	 * only valid in range mode
	 */
	public CharNode getChar2() {
		return myChar2;
	}

	@Override
	public void toString(StringBuilder s) {
		if (mode.equals("single")) {
			myChar1.toString(s);
			s.append(" ");
		} else {
			myChar1.toString(s);
			s.append("-");
			myChar2.toString(s);
			s.append(" ");
		}
	}

	private String mode;
	private CharNode myChar1;
	private CharNode myChar2;

}
