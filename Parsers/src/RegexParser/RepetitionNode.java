package RegexParser;

import java.io.PrintWriter;

import RegexParser.RegexNode;

public class RepetitionNode extends RegexNode {
	public RepetitionNode(int m, RegexNode reg, int minimum) {
		if (m == 1) {
			this.mode = "min";
			myRegex1 = reg;
			min = minimum;
		} else if (m == 2) {
			this.mode = "minToInfinite";
			myRegex1 = reg;
			this.min = minimum;
			this.max = Integer.MAX_VALUE;
		} else {
			System.err.println("Wrong construction of RepetitionNode");
		}
	}

	public RepetitionNode(int m, RegexNode reg, int minimum, int maximum) {
		if (m == 3) {
			this.mode = "minToMax";
			myRegex1 = reg;
			this.min = minimum;
			this.max = maximum;
		} else {
			System.err.println("Wrong construction of RepetitionNode");
		}
	}
	
	public RegexNode getMyRegex1(){
		return myRegex1;
	}

	@Override
	public void unparse(PrintWriter p) {
		p.print("( (");
		myRegex1.unparse(p);
		p.print(") ");
		if (mode.equals("min")) {
			p.print("{");
			p.print(this.min);
			p.print("}");
		} else if (mode.equals("minToInfinite")) {
			p.print("{");
			p.print(this.min);
			p.print(",");
			p.print("}");
		} else {
			p.print("{");
			p.print(this.min);
			p.print(",");
			p.print(this.max);
			p.print("}");
		}
		p.print(")");

	}

	@Override
	public void toString(StringBuilder s) {
		s.append("( (");
		myRegex1.toString(s);
		s.append(") ");
		if (mode.equals("min")) {
			s.append("{");
			s.append(this.min);
			s.append("}");
		} else if (mode.equals("minToInfinite")) {
			s.append("{");
			s.append(this.min);
			s.append(",");
			s.append("}");
		} else {
			s.append("{");
			s.append(this.min);
			s.append(",");
			s.append(this.max);
			s.append("}");
		}
		s.append(")");

	}

	public String getMode() {
		return mode;
	}
	
	public int getMin(){
		return min;
	}
	
	public int getMax(){
		return max;
	}

	protected int min;
	protected int max;
	protected String mode;
	protected RegexNode myRegex1;
}
